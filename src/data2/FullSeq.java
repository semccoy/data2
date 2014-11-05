package data2;

public class FullSeq<T extends Comparable> implements Sequence<T>, Sequenced<T> {

    T thing;
    int counter;
    Sequence<T> nextThing;

    // so we can use this down below
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
