import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.Collection;

/**
 * Contains test, for the proper execution of the DecisionTree.
 */
public class FlowTest {

    public static void main(String[] args) {
        DecisionTree<String> dt = new DecisionTree<>(FlowTest.createRecords(),
                1);

        final String AGE = "age";
        final String SALARY = "salary";
        final String SEX = "sex";

        Feature<Integer> f1 = new Feature<>(AGE, 30, Feature.Type.CONTINUOUS);
        Feature<Integer> f2 = new Feature<>(SALARY, 40_816, Feature.Type.CONTINUOUS);
        Feature<String> f3 = new Feature<>(SEX, "female", Feature.Type.DISCRETE);
        Record<String> r1 = new Record<>(Arrays.asList(f1, f2, f3), null);

        System.out.println("result: " + dt.predict(r1));
    }

    /**
     * Creates test Record's' for the DecisionTree.
     * @return Test Record's' for the DecisionTree.
     */
    static @NotNull Collection<Record<String>> createRecords() {
        final String AGE = "age";
        final String SALARY = "salary";
        final String SEX = "sex";

        final String TRG_GOOD = "good";
        final String TRG_BAD = "bad";

        Feature<Integer> f1 = new Feature<>(AGE, 25, Feature.Type.CONTINUOUS);
        Feature<Integer> f2 = new Feature<>(SALARY, 50_000, Feature.Type.CONTINUOUS);
        Feature<String> f3 = new Feature<>(SEX, "female", Feature.Type.DISCRETE);
        Record<String> r1 = new Record<>(Arrays.asList(f1, f2, f3), TRG_GOOD);

        f1 = new Feature<>(AGE, 51, Feature.Type.CONTINUOUS);
        f2 = new Feature<>(SALARY, 45_816, Feature.Type.CONTINUOUS);
        f3 = new Feature<>(SEX, "male", Feature.Type.DISCRETE);
        Record<String> r2 = new Record<>(Arrays.asList(f1, f2, f3), TRG_BAD);

        f1 = new Feature<>(AGE, 42, Feature.Type.CONTINUOUS);
        f2 = new Feature<>(SALARY, 75_491, Feature.Type.CONTINUOUS);
        f3 = new Feature<>(SEX, "male", Feature.Type.DISCRETE);
        Record<String> r3 = new Record<>(Arrays.asList(f1, f2, f3), TRG_GOOD);

        f1 = new Feature<>(AGE, 36, Feature.Type.CONTINUOUS);
        f2 = new Feature<>(SALARY, 15_034, Feature.Type.CONTINUOUS);
        f3 = new Feature<>(SEX, "female", Feature.Type.DISCRETE);
        Record<String> r4 = new Record<>(Arrays.asList(f1, f2, f3), TRG_BAD);

        f1 = new Feature<>(AGE, 21, Feature.Type.CONTINUOUS);
        f2 = new Feature<>(SALARY, 65_500, Feature.Type.CONTINUOUS);
        f3 = new Feature<>(SEX, "female", Feature.Type.DISCRETE);
        Record<String> r5 = new Record<>(Arrays.asList(f1, f2, f3), TRG_GOOD);

        f1 = new Feature<>(AGE, 62, Feature.Type.CONTINUOUS);
        f2 = new Feature<>(SALARY, 35_000, Feature.Type.CONTINUOUS);
        f3 = new Feature<>(SEX, "male", Feature.Type.DISCRETE);
        Record<String> r6 = new Record<>(Arrays.asList(f1, f2, f3), TRG_BAD);

        f1 = new Feature<>(AGE, 23, Feature.Type.CONTINUOUS);
        f2 = new Feature<>(SALARY, 74_154, Feature.Type.CONTINUOUS);
        f3 = new Feature<>(SEX, "male", Feature.Type.DISCRETE);
        Record<String> r7 = new Record<>(Arrays.asList(f1, f2, f3), TRG_BAD);

        f1 = new Feature<>(AGE, 56, Feature.Type.CONTINUOUS);
        f2 = new Feature<>(SALARY, 120_000, Feature.Type.CONTINUOUS);
        f3 = new Feature<>(SEX, "female", Feature.Type.DISCRETE);
        Record<String> r8 = new Record<>(Arrays.asList(f1, f2, f3), TRG_GOOD);

        f1 = new Feature<>(AGE, 34, Feature.Type.CONTINUOUS);
        f2 = new Feature<>(SALARY, 25_150, Feature.Type.CONTINUOUS);
        f3 = new Feature<>(SEX, "male", Feature.Type.DISCRETE);
        Record<String> r9 = new Record<>(Arrays.asList(f1, f2, f3), TRG_BAD);

        f1 = new Feature<>(AGE, 28, Feature.Type.CONTINUOUS);
        f2 = new Feature<>(SALARY, 165_000, Feature.Type.CONTINUOUS);
        f3 = new Feature<>(SEX, "male", Feature.Type.DISCRETE);
        Record<String> r10 = new Record<>(Arrays.asList(f1, f2, f3), TRG_GOOD);

        return Arrays.asList(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10);
    }

}//end class FlowTest