package data2;

/**
 * Full (non-empty) sequence
 * @param <T> A generic type object
 * @see Sequence
 * @see Sequenced
 */
public class FullSeq<T extends Comparable> implements Sequence<T>, Sequenced<T> {

    T thing;
    int counter;
    Sequence<T> nextThing;

    /**
     * Constructor that takes a node, a counter, and a sequence to create a sequence
     * @param thing A generic type object
     * @param counter An integer detailing node's counter
     * @param nextThing A sequence
     */
    FullSeq(T thing, int counter, Sequence<T> nextThing) {
        this.thing = thing;
        this.counter = counter;
        this.nextThing = nextThing;
    }

    public T here() {
        return this.thing;
    }

    public boolean hasNext() {
        return true;
    }

    public Sequence<T> next() {
        if (counter > 1) {
            return new FullSeq(thing, counter - 1, nextThing);
        } else {
            return nextThing;
        }
    }

    public String stringify() {
        return "" + this.thing;
    }

    public Sequence<T> seq() {
        return this;
    }
}
