import org.jetbrains.annotations.NotNull;

public class IrisTest {

    /**
     * Creates a Feature.Generator, for a given tab in the data. The tab must be
     * in the form: featureTitle:0 for discrete Feature's' or featureTitle:1 for
     * continuous Feature's'.
     * @param tab A String that contains the title and the type of the Feature
     * the Feature.Generator will be made for. Its form is: featureTitle:0 for
     * discrete Feature's' or featureTitle:1 for continuous Feature's'.
     * @param <T> The type of the Feature.Generator.
     * @return A Feature.Generator for Feature the given tab represents.
     */
    private static <T extends Comparable<T>> Feature.@NotNull Generator<T>
            getGenerator(@NotNull String tab) {
        throw new UnsupportedOperationException();
    }

}//end class IrisTest