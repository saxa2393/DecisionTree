import org.jetbrains.annotations.NotNull;

/**
 * Represents a mechanism that picks the correct branch to follow from a Node in
 * a DecisionTree, based on a given value.
 */
public abstract class Brancher {

    /**
     * Picks the correct branch from a Node to another, based on a given value.
     * @param data The value to branch based on.
     * @param <T> The type of the branching value.
     * @return The index of the branch to pick.
     */
    public abstract <T extends Comparable<T>> int branch(@NotNull T data);

}//end class Brancher
