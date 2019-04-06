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
     * Creates a Table, given its Record's'.
     * @param records The Record's' of this Table.
     * @throws IllegalArgumentException If there is at least 1 Record in records
     * Collection with a null target value.
     */
    public Table(@NotNull Collection<Record<T>> records) {
        //Validates that all the Record's' in records Collection have non-null
        //target value
        for (Record<T> r : records) {
            //Checks if Record r has no target value
            if (r.getTarget() == null) {
                throw new IllegalArgumentException("All the Record's' in " +
                        "Collection records must contain a non-null target " +
                        "value.");
            }//end if
        }//end for

        this.records = new ArrayList<>(records);
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