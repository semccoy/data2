package data2;

public interface MultiSet<T extends Comparable> extends Sequenced<T> {

    // old
    public int cardinality();

    public boolean isEmptyHuh();

    public boolean member(T elt);

    public MultiSet add(T elt);

    public MultiSet remove(T elt);

    public MultiSet union(MultiSet u);

    public MultiSet inter(MultiSet u);

    public MultiSet diff(MultiSet u);

    public boolean equal(MultiSet u);

    public boolean subset(MultiSet u);

    // new
    public int getCount(T elt);

    public MultiSet addSome(T elt, int i);

    public MultiSet removeSome(T elt, int i);

    public MultiSet removeAll(T elt);

    public Sequence<T> seq();

    // others - these are just for visualizing sequences (c/o bryce)
    public int countIt();

    public String toStringIt();

    // rb tree
    public MultiSet<T> blacken();
    
    public MultiSet<T> addInner(T elt, int i);
    
    public MultiSet<T> rbInsert(T elt, int i);
    
    public boolean isRedHuh();
    
    
}
