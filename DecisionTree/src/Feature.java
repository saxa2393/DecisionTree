import org.jetbrains.annotations.NotNull;

/**
 * Represents a feature/attribute in a record.
 * @param <T> The type of the data this Feature contains.
 */
public class Feature<T extends Comparable<T>> {

    /**
     * The title of this Feature.
     */
    private final String TITLE;

    /**
     * The data of this Feature.
     */
    private final T DATA;

    /**
     * Creates a Feature, given its title and data.
     * @param title The title of this Feature.
     * @param data The data of this Feature.
     */
    public Feature(@NotNull String title, @NotNull T data) {
        this.TITLE = title;
        this.DATA = data;
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