package data2;

/**
 * Interface housing methods for MultiSet bags
 * @param <T> A generic type object
 * @see EmptyBag
 * @see FullBag
 * @see Sequenced
 */
public interface MultiSet<T extends Comparable> extends Sequenced<T> {

    /**
     * Returns the size of a bag
     * Example: {q, w, e, r, t, y}.cardinality() = 6.
     * @return Integer representing the number of elements in the bag
     */
    public int cardinality();

    /**
     * Returns true if the bag is empty
     * Example: {}.isEmptyHuh() = true
     * Example: {n, o, p, e}.isEmptyHuh() = false
     * @return Boolean that is true if the set is empty and otherwise false
     */
    public boolean isEmptyHuh();

    /**
     * Returns true if an element is in a bag 
     * Example: {A, B, C}.member(B) = true 
     * Example: {A, B, C}.member(x) = false
     * @param elt a generic element
     * @return Boolean that is true if the element is in the bag and otherwise false
     */
    public boolean member(T elt);

    /**
     * Adds an element to a bag and returns the new bag 
     * Example: {A, B, C}.add(D) = {A, B, C, D}
     * Example: {}.add(D) = {D}
     * @param elt a generic element
     * @return MultiSet with the element elt added
     */
    public MultiSet add(T elt);

    /**
     * Removes an element from a bag and returns the new bag 
     * Example: {A, B, C}.remove(C) = {A, B} 
     * Example: {A}.remove(A) = {}
     * @param elt a generic element
     * @return MultiSet with the element elt removed
     */
    public MultiSet remove(T elt);

    /**
     * Returns the union of two bags
     * Example: {A, B, C}.union({D, E, F}) = {A, B, C, D, E, F}
     * Example: {A, A, B}.union({B, B, C}) = {A, B, C}
     * @param u a bag
     * @return MultiSet with the combined elements of both bags
     */
    public MultiSet union(MultiSet u);

    /**
     * Returns the intersection of two bags
     * Example: {A, B, C}.inter({C, D, E}) = {C}
     * Example: {A, B, C}.inter({D, E, F}) = {}
     * @param u a bag
     * @return MultiSet with the shared elements of both bags
     */
    public MultiSet inter(MultiSet u);

    /**
     * Returns the difference of two bags
     * Example: {A, B, C}.diff({C, E, F}) = {A, B}
     * Example: {A, B, C}.diff({X, Y, Z}) = {A, B, C} Example: {A, B, C}.diff({A, B, C}) = {}
     * @param u a bag
     * @return MultiSet with the elements of the first bag that are not in the
     * second
     */
    public MultiSet diff(MultiSet u);

    /**
     * Returns true if two bags are identical
     * Example: {A, B, C}.equal({D, E, F}) = false
     * Example: {A, B, C}.equal({A, B, C}) = true
     * Example: {}.equal({}) = true
     * @param u a bag
     * @return Boolean that is true if the sets are identical and otherwise
     * false
     */
    public boolean equal(MultiSet u);

    /**
     * Returns true if second bag (u) is a subset of the first bag
     * Example: {A, B, C}.subset({A, B}) = true
     * Example: {A, B, C}.subset({X, Y, Z}) = false
     * Example: {A}.subset({A}) = true
     * @param u a bag
     * @return Boolean that is true if the second bag (u) is entirely contained
     * within the first bag
     */
    public boolean subset(MultiSet u);

    /**
     * Returns the number of times an element appears in a bag
     * Example: {A, B, B, B, C}.getCount(B) = 3
     * Example: {x, x, x, y, z}.getCount(w) = 0
     * @param elt a generic element
     * @return Integer representing the number of times the element appears in a
     * bag
     */
    public int getCount(T elt);

    /**
     * Adds an element i times to a bag and returns that bag
     * Example: {A, B, C}.addSome(D, 3) = {A, B, C, D, D, D} \
     * Example: {}.addSome(T, 1) = {T}
     * @param elt a generic element
     * @param i integer detailing the number of times the element elt is added to the bag
     * @return MultiSet containing original bag plus i instances of the element elt
     */
    public MultiSet addSome(T elt, int i);

    /**
     * Removes an element from a bag i times and returns that bag
     * Example: {A, B, C, C, C}.removeN(C, 2) = {A, B, C}
     * Example: {A, B, C, C, C}.removeN(C, 3) = {A, B}
     * @param elt a generic element
     * @param i integer detailing number of times the element elt is removed from the bag
     * @return MultiSet containing original bag less i instances of the element elt
     */
    public MultiSet removeSome(T elt, int i);

    /**
     * Removes all instances of an element from a bag and returns that bag
     * Example: {A, A, A, A, B, C}.removeAll(A) = {B, C}
     * Example: {X, X}.removeAll(X) = {}
     * @param elt a generic element
     * @return MultiSet containing original bag less all instances of the element elt
     */
    public MultiSet removeAll(T elt);

    /**
     * Turns a bag into a sequence
     * @return a generic sequence
     */
    public Sequence<T> seq();

    /**
     * Sums up number of objects in a sequence
     * Example: sequence(a, b, c).countIt() = 3
     * @return Integer indicating number of objects in sequence
     */
    public int countIt();

    /**
     * Calls toString on a sequence to turn objects in it into strings
     * Example: sequence(1, 2, 3).toStringIt() = "1 2 3"
     * @return String containing all objects in sequence
     */
    public String toStringIt();

    /**
     * Turns node of red-black tree black
     * Example: someRedTree.blacken() = someBlackTree;
     * @return MultiSet with its color set to black
     */
    public MultiSet<T> blacken();

    /**
     * Adds a new node to a red-black tree
     * Example: difficult to show
     * @param elt a generic element
     * @param i integer detailing the element elt's counter
     * @return MultiSet red-black tree containing the element elt
     */
    public MultiSet<T> addInner(T elt, int i);

    /**
     * Returns true if node of red-black tree is red
     * Example: someRedTree.isRedHuh() = true
     * @return Boolean that is true if node is red and otherwise false
     */
    public boolean isRedHuh();

    /**
     * Balances a red-black tree such that its depth varies by no more than 1
     * Example: difficult to show
     * @return MultiSet red-black tree with balanced depth
     */
    public MultiSet<T> balance();

//    /**
//     * Returns false if red-black tree has two red nodes connected
//     * Example: difficult to show
//     * @return Boolean that is false if red nodes touch and otherwise true
//     */
//    public boolean helper1();
//
//    /**
//     * Returns depth of red-black tree along a path
//     * Example treeOfDepthFour.pathCounter() = 4
//     * @return Integer detailing depth of tree along designated path
//     */
//    public int pathCounter();
}
