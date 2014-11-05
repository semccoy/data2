package data2;

class EmptySet<T extends Comparable> implements FiniteSet<T>, Sequenced<T> {

    // constructor for only possibility
    public void EmptySet() {
    }

    // old 
    public static FiniteSet empty() {
        return new EmptySet();
    }

    public int cardinality() {
        return 0;
    }

    public boolean isEmptyHuh() {
        return true;
    }

    public boolean member(T elt) {
        return false;
    }

    public FiniteSet add(T elt) {
        return new FullSet(elt);
    }

    public FiniteSet remove(T elt) {
        return this;
    }

    public FiniteSet union(FiniteSet u) {
        return u;
    }

    public FiniteSet inter(FiniteSet u) {
        return this;
    }

    public FiniteSet diff(FiniteSet u) {
        return u;
    }

    public boolean equal(FiniteSet u) {
        return u.isEmptyHuh();
    }

    public boolean subset(FiniteSet u) {
        return true;
    }

    // new
    public int getCount(T elt) {
        return 0;
    }

    public FiniteSet addSome(T elt, int i) {
        return new FullSet(elt, i);
    }

    public FiniteSet removeSome(T elt, int i) {
        return this;
    }

    public FiniteSet removeAll(T elt) {
        return this;
    }

    public Sequence<T> seq() {
        return new EmptySeq();
    }
}
