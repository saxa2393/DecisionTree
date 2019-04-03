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

}//end class Record