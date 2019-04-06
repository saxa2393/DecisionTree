import org.jetbrains.annotations.NotNull;

/**
 * Represents a feature/attribute in a record.
 * @param <T> The type of the data this Feature contains.
 */
public class Feature<T extends Comparable<T>> {

    /**
     * Represents the TYPE of the data contained by this Feature. Discrete or
     * continuous.
     */
    public enum Type {

        /**
         * A Feature that contains discrete values.
         */
        DISCRETE,

        /**
         * A Feature that contains continuous values.
         */
        CONTINUOUS

    }//end enum Type

    /**
     * The title of this Feature.
     */
    private final String TITLE;

    /**
     * The data of this Feature.
     */
    private final T DATA;

    /**
     * The TYPE of the data this Feature contains.
     */
    private final Type TYPE;

    /**
     * Creates a Feature, given its title and data.
     * @param title The title of this Feature.
     * @param data The data of this Feature.
     * @param type The TYPE of the data this Feature contains.
     */
    public Feature(@NotNull String title, @NotNull T data, @NotNull Type type) {
        this.TITLE = title;
        this.DATA = data;
        this.TYPE = type;
    }

    /**
     * Gets the title of this Feature.
     * @return The title of this Feature.
     */
    public @NotNull String getTitle() {
        return this.TITLE;
    }

    /**
     * Gets the data of this Feature.
     * @return The data of this Feature.
     */
    public @NotNull T getData() {
        return this.DATA;
    }

    /**
     * Gets the TYPE of data this Feature contains.
     * @return The TYPE of data this Feature contains.
     */
    public @NotNull Type getType() {
        return this.TYPE;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Feature<?> feature = (Feature<?>) o;

        return TITLE.equals(feature.TITLE);

    }

    @Override
    public int hashCode() {
        return TITLE.hashCode();
    }

}//end class Feature