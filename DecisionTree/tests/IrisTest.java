import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class IrisTest {

    public static void main(String[] args) {
        //A List with all the lines of the .csv file as String's'
        List<String> lines = null;
        //Tries to read the lines of the file in lines List
        try {
            lines = Files.readAllLines(Paths.get(args[0]));
        } catch (IOException e) {
            e.printStackTrace();
        }//end try

        //Validates that lines List contains at least 2 lines, its header, and
        //at least 1 record
        if (lines.size() < 2) {
            throw new IllegalArgumentException("Argument args[0] must " +
                    "contain a file with at least 2 lines.");
        }//end if

        //A List with all the String tokens of the header of the records.
        List<String> header = IrisTest.tokenize(lines.get(0));
        //A List with only the records in tokenized form, without the header
        List<List<String>> strRecords = IrisTest.strRecords(lines.subList(1,
                lines.size()));

        //A List with all the training records
        List<List<String>> trainStrRecords = IrisTest.trainRecords(strRecords);
        //A Collection of Record's', form trainStrRecords
        Collection<Record<String>> trainRecords =
                IrisTest.constructRecords(header, trainStrRecords);
        //Creates a DecisionTree with the training records
        DecisionTree<String> dt = new DecisionTree<>(trainRecords, 1);

        //A List with all the evaluation records
        List<List<String>> evalStrRecords =
                IrisTest.evaluationRecords(strRecords, trainStrRecords);
        //A Collection of Record's', form evalStrRecords
        Collection<Record<String>> evalRecords =
                IrisTest.constructRecords(header, evalStrRecords);
        //Prints the success prediction rate of DecisionTree dt
        System.out.println(IrisTest.successRate(dt, evalRecords));
    }

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
     * @param strRecords A List with all the records, in a tokenized form, i.e.
     * a List of String tokens. This List must not contain the header of the
     * .csv file, it must contain only the records, all with tokenized String
     * values. The List at the last index must contain the target values.
     * @param index The index of the column, in strRecords List.
     * @return A List that contains all the values of the column described by
     * index, in String form.
     */
    private static @NotNull List<String> columnRecords(
            @NotNull List<List<String>> strRecords, int index) {
        throw new UnsupportedOperationException();
    }

    /**
     * Creates a new List of Double, out of a List of String, given that the
     * String's' actually represent Double's' and can be parsed.
     * @param list A List of String values, that represent Double's'.
     * @return A List with the values of the input List, in Double form.
     */
    private static @NotNull List<Double> toDouble(@NotNull List<String> list) {
        throw new UnsupportedOperationException();
    }

    /**
     * Randomly selects a percentage from a List of records, to form a List in
     * the same form, that contains the training records. The selection must not
     * be entirely random, in order to prevent extinction of any target value.
     * To prevent that case, this method randomly selects a percentage of every
     * target value, for the training set.
     * @param records A List with all the records, in a tokenized form, i.e. a
     * List of String tokens. This List must not contain the header of the .csv
     * file, it must contain only the records, all with tokenized String values.
     * The List at the last index must contain the target values.
     * @return The input List, filtered to contain only the training records.
     */
    private static @NotNull List<List<String>> trainRecords(
            @NotNull List<List<String>> records) {
        throw new UnsupportedOperationException();
    }

    /**
     * Takes 2 List's' of records, and returns their difference.
     * @param records A List with all the records, in a tokenized form, i.e. a
     * List of String tokens. This List must not contain the header of the .csv
     * file, it must contain only the records, all with tokenized String values.
     * The List at the last index must contain the target values.
     * @param trainRecords A List that is a subset of records List, and contains
     * only the training records.
     * @return A List containing all the records that exist in records List, but
     * don't exist in trainRecords List, i.e. the evaluation records.
     */
    private static @NotNull List<List<String>> evaluationRecords(
            @NotNull List<List<String>> records,
            @NotNull List<List<String>> trainRecords) {
        throw new UnsupportedOperationException();
    }

    /**
     * Calculates the percentage of the given Record's' that were successfully
     * classified by the given DecisionTree.
     * @param dt A DecisionTree to classify the given Record's'.
     * @param records A Collection of Record's' to be classified by the given
     * DecisionTree. They must contain their target values.
     * @return A value in range [0.0, 1.0] describing the percentage of the
     * given Record's' that were successfully classified, by the given
     * DecisionTree. A value of 0.0 means total failure to correctly classify
     * even a single Record, while a value of 1.0 means that all the Record's'
     * were correctly classified.
     */
    private static double successRate(@NotNull DecisionTree dt,
            @NotNull Collection<Record<String>> records) {
        throw new UnsupportedOperationException();
    }

    /**
     * Creates Record's' for the DecisionTree, for training.
     * @param header A List with all the String tokens of the header of the
     * records.
     * @param strRecords A List with all the records, in a tokenized form, i.e.
     * a List of String tokens. This List must not contain the header of the
     * .csv file, it must contain only the records, all with tokenized String
     * values. The List at the last index must contain the target values.
     * @return A Collection of Record's' with target variable of String,
     * constructed from the given records in strRecords.
     */
    private static @NotNull Collection<Record<String>> constructRecords(
            @NotNull List<String> header,
            @NotNull List<List<String>> strRecords) {
        throw new UnsupportedOperationException();
    }

    /**
     * Creates a Collection of Record's', given all the data of the columns,
     * Feature.Generator's', and a List with all the output values, of the
     * records.
     * @param data A List of columns, with each column containing the values of
     * all the records, for that column.
     * @param generators A List with all Feature.Generator's' to create the
     * Feature's' of the Record's'.
     * @param output A List with all the output values of the records.
     * @param <Τ> The type of the output variable.
     * @return A Collection with all the Record's', to train a DecisionTree.
     */
    private static <Τ> @NotNull Collection<Record<Τ>> constructRecords(
            @NotNull List<List<? extends Comparable<?>>> data,
            @NotNull List<Feature.Generator> generators,
            @NotNull List<Τ> output) {
        final int RECORDS_NUM = data.get(0).size();
        Collection<Record<Τ>> records = new ArrayList<>(RECORDS_NUM);
        for (int i = 0; i < RECORDS_NUM; ++i) {
            List<Feature<?>> features = new ArrayList<>(data.size());
            for (int j = 0; j < data.size(); ++j) {
                features.add(generators.get(j).generate(data.get(j).get(i)));
            }//end for

            records.add(new Record<>(features, output.get(i)));
        }//end for

        return records;
    }

}//end class IrisTest