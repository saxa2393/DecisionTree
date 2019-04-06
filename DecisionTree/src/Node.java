import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Represents a Node in a DecisionTree.
 * @param <T> The type of the output variable (class/category) of the Record's'
 * of this Node.
 */
public class Node<T> {

    /**
     * The title of the Feature this Node branches based on. Contains null if
     * this Node is a leaf Node.
     */
    private String featureTitle;

    /**
     * A Table with all the Record's' of this Node.
     */
    private Table<T> table;

    /**
     * A Map with all the child nodes of this Node. The key is an Object, to use
     * its .equals() method polymorphically, and the value is the child Node to
     * branch to.
     */
    private Map<Object, Node<T>> childNodes = new HashMap<>();

    /**
     * Maps a given key to a child Node.
     * @param key The key of the child Node.
     * @param child The child Node of this Node.
     */
    public void addChild(@NotNull Object key, @NotNull Node<T> child) {
        //Puts child in the childNodes Maps with key as its key
        this.childNodes.put(key, child);
    }

    /**
     * Gets a child Node of this Node, given a key.
     * @param key The key of the child Node to branch to.
     * @return The child Node to branch to, or null if there is no mapping for
     * to a child Node for the given key.
     */
    public @Nullable Node<T> branch(@NotNull Object key) {
        return this.childNodes.get(key);
    }

    /**
     * Indicates if Node is a leaf node, i.e. contains 0 child Nodes.
     * @return True if this Node is a leaf node, otherwise false.
     */
    public boolean isLeaf() {
        return this.childNodes.isEmpty();
    }

    /**
     * Gets the title of the Feature this Node branches based on.
     * @return The title of the Feature this Node branches based on, or null if
     * this Node is a leaf node.
     */
    public @Nullable String getFtrTitle() {
        return this.featureTitle;
    }

    /**
     * Finds the dominant (most frequent) target value of the Table of this
     * Node. Breaks ties arbitrary.
     * @return The dominant (most frequent) target value of the Table of this
     * Node or an arbitrary one, if there are more than 1 with the same
     * frequency.
     */
    public @NotNull T dominantTarget() {
        return this.table.getRecords()
                         .parallelStream()
                         .map(Record::getTarget)
                         .collect(Collectors.groupingBy(Function.identity(),
                                 Collectors.counting()))
                         .entrySet()
                         .parallelStream()
                         .max(Comparator.comparing(Map.Entry::getValue))
                         .get()
                         .getKey();
    }

}//end class Node