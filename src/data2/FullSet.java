package data2;

import java.util.Random;

class FullSet<T extends Comparable> implements FiniteSet<T>, Sequenced<T> {

    T thing;
    int counter;
    FiniteSet left;
    FiniteSet right;

    // constructors for different possibilities
    public FullSet(T thing) {
        this.thing = thing;
        this.counter = 1;
        this.left = empty();
        this.right = empty();
    }

    public FullSet(T thing, int counter) {
        this.thing = thing;
        this.counter = counter;
        this.left = empty();
        this.right = empty();
    }

    public FullSet(T thing, FiniteSet left, FiniteSet right) {
        this.thing = thing;
        this.counter = 1;
        this.left = left;
        this.right = right;
    }

    public FullSet(T thing, int counter, FiniteSet left, FiniteSet right) {
        this.thing = thing;
        this.counter = counter;
        this.left = left;
        this.right = right;
    }

    // multiset methods
    public static FiniteSet empty() {
        return new EmptySet();
    }

    public int cardinality() {
        return counter + this.left.cardinality() + this.right.cardinality();
    }

    public boolean isEmptyHuh() {
        if (this.getCount(thing) == 0) {
            if (!left.isEmptyHuh()) {
                return right.isEmptyHuh();
            } else {
                return left.isEmptyHuh();
            }
        } else {
            return false;
        }
    }

    public boolean member(T elt) {
        if (this.thing.compareTo(elt) == 0) {
            return this.counter > 0;
        } else if (this.thing.compareTo(elt) > 0) {
            return left.member(elt);
        } else {
            return right.member(elt);
        }
    }

    public FiniteSet add(T elt) {
        if (this.thing.compareTo(elt) == 0) {
            return this;
        } else {
            if (this.thing > elt) {
                return new FullSet(thing, left.add(elt), right);
            } else {
                return new FullSet(thing, left, right.add(elt));
            }
        }
    }

    public FiniteSet union(FiniteSet u) {
        return this.left.union(u.union(right).add(this.thing));
    }

    public FiniteSet remove(T elt) {
        if (thing == elt) {
            return right.union(left);
        } else if (thing > elt) {
            return new FullSet(thing, left, right.remove(elt));
        } else {
            return new FullSet(thing, left.remove(elt), right);
        }
    }

    public FiniteSet inter(FiniteSet u) {
        //if in the set, put in inter
        if (u.member(thing)) {
            return new FullSet(thing, this.left.inter(u), this.right.inter(u));
        } //if not in the set, remove thing from inter
        else {
            return this.remove(thing).inter(u);
        }
    }

    public FiniteSet diff(FiniteSet u) {
        //returns things different between existing FiniteSet and u
        //so if is a member, we want to remove it
        if (u.member(thing)) {
            return this.left.union(this.right).diff(u.remove(this.thing));
        } //and if not, we don't need to remove it
        else {
            return this.left.union(this.right).diff(u);
        }
    }

    public boolean subset(FiniteSet u) {
        return (left.subset(u) && u.member(thing) && right.subset(u));
    }

    public boolean equal(FiniteSet u) {
        return (this.subset(u) && u.subset(this));
    }

    public int getCount(T elt) {
        if (this.thing.compareTo(elt) == 0) {
            return counter;
        } else if (this.thing.compareTo(elt) > 0) {
            return left.getCount(elt);
        } else {
            return right.getCount(elt);
        }
    }

}
