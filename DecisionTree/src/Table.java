import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.List;

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
     * List with a null target value.
     */
    public Table(@NotNull List<Record<T>> records) {
        //Validates that all the Record's' in records List have non-null target
        //value
        for (Record<T> r : records) {
            //Checks if Record r has no target value
            if (r.getTarget() == null) {
                throw new IllegalArgumentException("All the Record's' in " +
                        "List records must contain a nun-null target value.");
            }//end if
        }//end for

        this.records = records;
    }

    /**
     * Gets the Record's' of this Table.
     * @return An unmodifiable List with the Record's' of this Table.
     */
    public @NotNull List<Record<T>> getRecords() {
        return Collections.unmodifiableList(this.records);
    }

}//end class Table