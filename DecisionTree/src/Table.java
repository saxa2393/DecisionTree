import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Represents a list of Record's' that all have a non-null target value.
 * @param <T> The type of the output variable (class/category) of the Record's'.
 */
public class Table<T> {

    /**
     * A List with all the records of this Table.
     */
    private List<Record<T>> records;

    /**
     * A Map with keys the title of a Feature and values a Set with the FiniteRange's'
     * of that Feature column. It is used on the Features with continuous data.
     */
    private Map<String, Set<SemiRange<?>>> ranges;

    /**
     * Creates a Table, given its Record's'.
     * @param records The Record's' of this Table. All the Record's' must be
     * consistent with each other, in the sense, that all of them must contain
     * the same semantic Features. This constructor will not verify that, for
     * performance reasons. If the given Record's' have that inconsistency, this
     * instance will have undefined behavior and state.
     * @throws IllegalArgumentException If the records.isEmpty() == true.
     * @throws IllegalArgumentException If there is at least 1 Record in records
     * List with a null target value.
     */
    public Table(@NotNull List<Record<T>> records) {
        //Validates that there is at least 1 element in records List
        if (records.isEmpty()) {
            throw new IllegalArgumentException("Argument List records must " +
                    "contain at least 1 element.");
        }//end if

        //Validates that all the Record's' in records List have non-null target
        //value
        for (Record<T> r : records) {
            //Checks if Record r has no target value
            if (r.getTarget() == null) {
                throw new IllegalArgumentException("All the Record's' in " +
                        "List records must contain a non-null target value.");
            }//end if
        }//end for

        //A Map with keys the title of a Feature and values a Set with the
        //SemiRange's' of that Feature column.
        Map<String, Set<SemiRange<?>>> ranges = new HashMap<>();
        //Gets the 1st Record from Records List
        Record<T> templateRecord = records.get(0);
        //A Map with the Feature's' of the Record's' as values, and their titles
        //as keys
        Map<String, Feature<?>> features = templateRecord.getFeatures();
        //Loops through all the Feature's' of the Records, to find the ones with
        //continuous data type
        for (Map.Entry<String, Feature<?>> e : features.entrySet()) {
            //Checks if the current Feature doesn't have continuous data type
            if (!e.getValue().getType().equals(Feature.Type.CONTINUOUS)) {
                continue;
            }//end if

            //A List with the data of the current Feature, from all the
            //Record's'
            List<Comparable<?>> data = records.parallelStream()
                                              .map(r -> r.getFeatures()
                                                         .get(e.getKey())
                                                         .getData())
                                              .collect(Collectors.toList());
            //Shuffles data List, to minimize the chance for the O(n * log2(n))
            //worst case time, for finding its median with a PriorityQueue
            Collections.shuffle(data);
            //Creates a PriorityQueue with the first (records.size() + 2) / 2
            //elements from data List, to find its median in Θ(n) average time.
            //The generics are dropped here, as it is impossible for the
            //compiler to tell if the capture<?> in comparing 2 Comparable<?> is
            //the same
            PriorityQueue<Comparable> pq = new PriorityQueue<>(data.subList(
                    0, (data.size() + 2) / 2));
            //Finds the (records.size() + 2) / 2 largest elements of data List,
            //in pq PriorityQueue
            for (Comparable d : data.subList((data.size() + 2) / 2,
                    data.size())) {
                //Checks if d is larger than the smallest element in pq
                if (pq.element().compareTo(d) < 0) {
                    //Removes the head (smallest element) of pq (min heap)
                    pq.remove();
                    //Adds d in pq
                    pq.add(d);
                }//end if
            }//end for

            //Gets the median of data List
            Comparable<?> median = pq.element();
            //Partitions the continuous values of the current Feature into 2
            //SemiRange's'. The generics at instantiation are dropped, as the
            //compiler cannot infer them, because of the capture<?>
            SemiRange<?> lowRange = new SemiRange(median, false,
                    true);
            SemiRange<?> highRange = new SemiRange(median, true,
                    false);
            //Puts them in ranges Map, keyed by the title of the current Feature
            ranges.put(e.getKey(), new HashSet(Arrays.asList(lowRange,
                    highRange)));
        }//end for

        this.records = records;
        this.ranges = ranges;
    }

    /**
     * Gets the Record's' of this Table.
     * @return An unmodifiable List with the Record's' of this Table.
     */
    public @NotNull List<Record<T>> getRecords() {
        return Collections.unmodifiableList(this.records);
    }

    /**
     * Gets a Set with all the values of a Feature, in the Record's' of this
     * Table.
     * @param ftrTitle The title of the Feature to retrieve its values.
     * @return A Set with all the values of a given Feature, in the Record's' of
     * this Table.
     */
    public @NotNull Set<?> ftrValues(@NotNull String ftrTitle) {
        //FIXME Supports only discrete values
        return this.records
                   .parallelStream()
                   .map(r -> r.getFeatures().get(ftrTitle).getData())
                   .collect(Collectors.toSet());
    }

    /**
     * Calculates the frequency of a given value of a Feature, in the Record's'
     * of this Table.
     * @param ftrTitle The title of the Feature the given value belongs to.
     * @param value A value of the given Feature.
     * @return The frequency of a given value of a Feature, in the Record's' of
     * this Table. If there are no Record's' with the given value, or the value
     * does not belong in the value set of the given Feature, returns 0.
     */
    public long ftrValueFreq(@NotNull String ftrTitle, @NotNull Object value) {
        //FIXME Supports only discrete values
        return this.records
                   .parallelStream()
                   .map(r -> r.getFeatures().get(ftrTitle).getData())
                   .filter(e -> e.equals(value))
                   .count();
    }

    /**
     * Calculates the entropy of this Table, for the target value of its
     * Records.
     * @return The entropy of this Table, for the target value of its Records.
     */
    public double entropy() {
        //The entropy of this Table, for the target values
        double entropy = 0.0;
        //A Map with all the unique values of the target variable of the
        //Record's' of this Table as its keys and their frequency as its values
        Map<?, Long> valFreq = this.records
                                   .parallelStream()
                                   .map(Record::getTarget)
                                   .collect(Collectors.groupingBy(
                                           Function.identity(),
                                           Collectors.counting()));

        //Calculates the entropy
        for (Map.Entry<?, Long> e : valFreq.entrySet()) {
            //Calculates the relative frequency of the current value
            double relFreq = (double) e.getValue() / valFreq.size();
            //Subtracts the term of the current value from the entropy
            entropy -= relFreq * (Math.log(relFreq) / Math.log(2.0));
        }//end for

        return entropy;
    }

}//end class Table