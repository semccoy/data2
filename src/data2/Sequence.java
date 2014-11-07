package data2;

/**
 * Interface housing methods for sequences
 * @param <T> A generic type object
 * @see EmptySeq
 * @see FullSeq
 * @see SequenceCat
 * @see Sequenced
 */
public interface Sequence<T extends Comparable> {

    /**
     * Indicates location of sequence
     * Example: [x, y, z].here() = x
     * @return T A generic object marking location of sequence
     */
    public T here();

    /**
     * Determines if sequence has a next value
     * Example: [x, y, z].hasNext() = true
     * @return A Boolean that is true if there is a next object and otherwise false
     */
    public boolean hasNext();

    /**
     * Returns the sequence after here
     * Example: [x, y, z].next() = [y, z]
     * @return A Sequence, of all elements T after here (x)
     */
    public Sequence<T> next();

    /**
     * Creates a string from the elements of the sequence
     * Example: [x, y, z].toStringSeq() = "x y z"
     * @return A String containing the elements of the sequence
     */
    public String stringify();
}
