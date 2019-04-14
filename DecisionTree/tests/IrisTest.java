import org.jetbrains.annotations.NotNull;

import java.util.List;

public class IrisTest {

    /**
     * Tokenizes a String, by splitting it at ',' and returns a List with all
     * the tokens.
     * @param str A String to tokenize at ','.
     * @return A List with all the tokens of the splitting.
     */
    private static @NotNull List<String> tokenize(@NotNull String str) {
        throw new UnsupportedOperationException();
    }

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

    /**
     * Creates a List with all the lines of the file, in a tokenized form, i.e.
     * a List of String tokens.
     * @param lines A List with all the lines of the file in String's'.
     * @return A List with all the lines of the file, in a tokenized form, i.e.
     * a List of String tokens.
     */
    private static @NotNull List<List<String>> strRecords(
            @NotNull List<String> lines) {
        throw new UnsupportedOperationException();
    }

    /**
     * Creates a List that contains all the values of a column in String form.
     * The output List contains all the records, but only their values at the
     * column/Feature described by the given index, that applies in the input
     * List.
     * @param strRecords A List with all the lines of the file, in a tokenized
     * form, i.e. a List of String tokens.
     * @param index The index of the column, in strRecords List.
     * @return A List that contains all the values of the column described by
     * index, in String form.
     */
    private static @NotNull List<String> columnRecords(
            @NotNull List<List<String>> strRecords, int index) {
        throw new UnsupportedOperationException();
    }

}//end class IrisTest