package data2;

public class SequenceCat<T extends Comparable> implements Sequence<T> {
    // for combining sequences!

    Sequence<T> left;
    Sequence<T> right;

    SequenceCat(Sequence lefty, Sequence righty) {
        this.left = lefty;
        this.right = righty;
    }

    public T here() {
        if (this.left.hasNext()) {
            return this.left.here();
        } else {
            return this.right.here();
        }
    }

    public boolean hasNext() {
        return this.left.hasNext() || this.right.hasNext();
    }

    public Sequence<T> next() {
        if (this.left.hasNext()) {
            return new SequenceCat(this.left.next(), this.right);
        } else {
            return this.right.next();
        }
    }

    public String stringify() {
        return this.left.stringify() + " " + this.right.stringify();
    }
}
