import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collections;
import java.util.Map;

/**
 * Represents a row in the table with all the data.
 * @param <T> The type of the output variable (class/category) of this Record.
 */
public class Record<T> {

    /**
     * A Map with the features/attributes of this Record, keyed by their title.
     */
    private Map<String, Feature<?>> features;

    /**
     * The class/category of this Record. It may or may not be present. Non null
     * values indicate that the target is present, otherwise null values
     * indicate that it is not.
     */
    private T target;

    /**
     * Gets an unmodifiable Map, with the Feature's' of this Record, keyed by
     * their title.
     * @return An unmodifiable Map, with the Feature's' of this Record, keyed by
     * their title.
     */
    public @NotNull Map<String, Feature<?>> getFeatures() {
        return Collections.unmodifiableMap(this.features);
    }

    /**
     * Gets the class/category of this Record. Non null values indicate that the
     * target is present, otherwise null values indicate that it is not.
     * @return The class/category of this Record or null if the target value is
     * not present.
     */
    public @Nullable T getTarget() {
        return this.target;
    }

}//end class Record