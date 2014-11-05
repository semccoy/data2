package data2;

public interface FiniteSet {

    public int cardinality();

    public boolean isEmptyHuh();

    public boolean member(int elt);

    public FiniteSet add(int elt);

    public FiniteSet remove(int elt);

    public FiniteSet union(FiniteSet u);

    public FiniteSet inter(FiniteSet u);

    public FiniteSet diff(FiniteSet u);

    public boolean equal(FiniteSet u);

    public boolean subset(FiniteSet u);

}
