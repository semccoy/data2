package data2;

public class EmptySeq<T extends Comparable> implements Sequence<T> {

    public T here() {
        return null;
    }

    public boolean hasNext() {
        return false;
    }

    public Sequence<T> next() {
        return this;
    }

    public String stringify() {
        return "";
    }

    public Sequence<T> seq() {
        return this;
    }
}
