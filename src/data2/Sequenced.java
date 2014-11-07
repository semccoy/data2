package data2;

/**
 * Interface housing method to sequence objects
 * @param <T> A generic type object
 * Performance: O(n)
 */
public interface Sequenced<T extends Comparable> {

    /**
     * turns objects into a sequence
     * Example: {x, y, z}.seq() = [x, y, z]
     * @return A Sequence containing the original objects
     */
    public Sequence<T> seq();
}
