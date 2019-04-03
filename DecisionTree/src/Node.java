import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

/**
 * Represents a Node in a DecisionTree.
 * @param <T> The type of the output variable (class/category) of the Record's'
 * of this Node.
 */
public class Node<T> {

    /**
     * A Table with all the Record's' of this Node.
     */
    private Table<T> table;

    /**
     * A Map with all the child nodes of this Node. The key is an Object, to use
     * its .equals() method polymorphically, and the value is the child Node to
     * branch to.
     */
    private Map<Object, Node> childNodes = new HashMap<>();

    /**
     * Maps a given key to a child Node.
     * @param key The key of the child Node.
     * @param child The child Node of this Node.
     */
    public void addChild(@NotNull Object key, @NotNull Node child) {
        //Puts child in the childNodes Maps with key as its key
        this.childNodes.put(key, child);
    }

    /**
     * Gets the child Node of this Node, given a key.
     * @param key The key of the child Node to branch to.
     * @return The child Node to branch to.
     * @throws IllegalStateException If this Node is a leaf node.
     * @throws IllegalArgumentException If key has no mapping to a child Node.
     */
    public @NotNull
    Node branch(@NotNull Object key) {
        //Validates that this Node is not a leaf node
        if (this.isLeaf()) {
            throw new IllegalStateException("This Node is a leaf node, and " +
                    "contains no child Nodes.");
        }//end if

        //Gets the child Node mapping of key
        Node child = this.childNodes.get(key);
        //Validates that key has a mapping to a child Node
        if (null == child) {
            throw new IllegalArgumentException("Argument key has no mapping " +
                    "to a child Node.");
        }//end if

        return child;
    }

    /**
     * Indicates if Node is a leaf node, i.e. contains 0 child Nodes.
     * @return True if this Node is a leaf node, otherwise false.
     */
    public boolean isLeaf() {
        return this.childNodes.isEmpty();
    }

}//end class Node