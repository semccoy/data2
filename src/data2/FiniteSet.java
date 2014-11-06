package data2;

public interface FiniteSet<T extends Comparable> extends Sequenced<T> {

    // old
    public int cardinality();

    public boolean isEmptyHuh();

    public boolean member(T elt);

    public FiniteSet add(T elt);

    public FiniteSet remove(T elt);

    public FiniteSet union(FiniteSet u);

    public FiniteSet inter(FiniteSet u);

    public FiniteSet diff(FiniteSet u);

    public boolean equal(FiniteSet u);

    public boolean subset(FiniteSet u);

    // new
    public int getCount(T elt);

    public FiniteSet addSome(T elt, int i);

    public FiniteSet removeSome(T elt, int i);

    public FiniteSet removeAll(T elt);

    public Sequence<T> seq();

    // others - these are just for visualizing sequences (c/o bryce)
    public int countIt();

    public String toStringIt();

    // rb tree methods here
}
