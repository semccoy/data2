package data2;

class EmptySet implements FiniteSet {

    EmptySet() {
    }

    public static FiniteSet empty() {
        return new EmptySet();
    }

    public int cardinality() {
        return 0;
    }

    public boolean isEmptyHuh() {
        return true;
    }

    public boolean member(int elt) {
        return false;
    }

    public FiniteSet add(int elt) {
        return new FullSet(elt, new EmptySet(), new EmptySet());
    }

    public FiniteSet remove(int elt) {
        return new EmptySet();
    }

    public FiniteSet union(FiniteSet u) {
        return u;
    }

    public FiniteSet inter(FiniteSet u) {
        return new EmptySet();
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

    @Override
    public int compareTo(Object o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
