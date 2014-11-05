package data2;

import java.util.Random;

class FullSet implements FiniteSet {

    int node;
    FiniteSet left;
    FiniteSet right;

    FullSet(int node, FiniteSet left, FiniteSet right) {
        this.node = node;
        this.left = left;
        this.right = right;
    }

    public static FiniteSet empty() {
        return new EmptySet();
    }

    public int cardinality() {
        return left.cardinality() + 1 + right.cardinality();
    }

    public boolean isEmptyHuh() {
        return false;
    }

    public boolean member(int elt) {
        if (this.node == elt) {
            return true;
        } else if (this.node > elt) {
            return left.member(elt);
        } else {
            return right.member(elt);
        }
    }

    public FiniteSet add(int elt) {
        if (this.node == elt) {
            return this;
        } else {
            if (this.node > elt) {
                return new FullSet(node, left.add(elt), right);
            } else {
                return new FullSet(node, left, right.add(elt));
            }
        }
    }

    public FiniteSet union(FiniteSet u) {
        return this.left.union(u.union(right).add(this.node));
    }

    public FiniteSet remove(int elt) {
        if (node == elt) {
            return right.union(left);
        } else if (node > elt) {
            return new FullSet(node, left, right.remove(elt));
        } else {
            return new FullSet(node, left.remove(elt), right);
        }
    }

    public FiniteSet inter(FiniteSet u) {
        //if in the set, put in inter
        if (u.member(node)) {
            return new FullSet(node, this.left.inter(u), this.right.inter(u));
        } //if not in the set, remove node from inter
        else {
            return this.remove(node).inter(u);
        }
    }

    public FiniteSet diff(FiniteSet u) {
        //returns things different between existing FiniteSet and u
        //so if is a member, we want to remove it
        if (u.member(node)) {
            return this.left.union(this.right).diff(u.remove(this.node));
        } //and if not, we don't need to remove it
        else {
            return this.left.union(this.right).diff(u);
        }
    }

    public boolean subset(FiniteSet u) {
        return (left.subset(u) && u.member(node) && right.subset(u));
    }

    public boolean equal(FiniteSet u) {
        return (this.subset(u) && u.subset(this));
    }

    @Override
    public int compareTo(Object o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
