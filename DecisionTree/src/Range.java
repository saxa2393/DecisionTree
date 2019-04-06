import org.jetbrains.annotations.NotNull;

import java.util.Objects;

/**
 * Represents a range of continuous values, in order to reduce continuous values
 * into discrete categories.
 * @param <T> The type of the data this Feature contains.
 */
public class Range<T extends Comparable<T>> {

    /**
     * The lower (included) bound of this Range.
     */
    private T low;

    /**
     * The upper (included) bound of this Range.
     */
    private T high;

    /**
     * Creates a Range, given its lower and upper bounds, that are included.
     * @param low The lower (included) bound of this Range.
     * @param high The upper (included) bound of this Range.
     * @throws IllegalArgumentException If low > high.
     */
    public Range(@NotNull T low, @NotNull T high) {
        //Validates that low <= high
        if (low.compareTo(high) > 0) {
            throw new IllegalArgumentException("Arguments low and high must " +
                    "satisfy the inequality: low <= high.");
        }//end if

        this.low = low;
        this.high = high;
    }

    /**
     * Indicates if this Range contains the given value.
     * @param value A value to check if it is contained in this Range.
     * @return True if the given value is contained in this Range, otherwise
     * false.
     */
    public boolean contains(@NotNull T value) {
        return this.low.compareTo(value) <= 0 && this.high.compareTo(value) >=
                0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Range<?> range = (Range<?>) o;
        return low.equals(range.low) && high.equals(range.high);
    }

    @Override
    public int hashCode() {
        return Objects.hash(low, high);
    }

}//end class Range