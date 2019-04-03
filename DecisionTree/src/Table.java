import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.List;

/**
 * Represents a list of Record's'.
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
     */
    public Table(@NotNull List<Record<T>> records) {
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