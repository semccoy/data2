package data2;

import java.util.Random;

public class FullSet<T extends Comparable> implements FiniteSet<T>, Sequenced<T> {

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

    // old methods
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
        // if the same just increase counter
        if (this.thing.compareTo(elt) == 0) {
            return new FullSet(this.thing, this.counter + 1, this.left, this.right);
            // if elt is larger put it on the right
        } else if (elt.compareTo(this.thing) > 0) {
            return new FullSet(this.thing, this.counter, this.left, this.right.add(elt));
        } else {
            return new FullSet(this.thing, this.counter, this.left.add(elt), this.right);
        }
    }

    public FiniteSet remove(T elt) {
        // same as add pretty much
        if (this.thing.compareTo(elt) == 0) {
            return new FullSet(this.thing, this.counter - 1, this.left, this.right);
        } else if (elt.compareTo(this.thing) > 0) {
            return new FullSet(this.thing, this.counter, this.left, this.right.remove(elt));
        } else {
            return new FullSet(this.thing, this.counter, this.left.remove(elt), this.right);
        }
    }

    public FiniteSet union(FiniteSet u) {
        return left.union(right.union(u).addSome(thing, this.getCount(thing)));
    }

    public FiniteSet inter(FiniteSet u) {
        if (u.member(this.thing)) {
            int minimum = Math.min(u.getCount(thing), this.getCount(thing));
            return new FullSet(this.thing, minimum, this.left.inter(u), this.right.inter(u));
        } else {
            return this.left.inter(u).union(this.right.inter(u));
        }
    }

    public FiniteSet diff(FiniteSet u) {
        return left.union(right).diff(u.removeSome(thing, this.getCount(thing)));
    }

    public boolean equal(FiniteSet u) {
        // no change :)
        return (this.subset(u) && u.subset(this));
    }

    public boolean subset(FiniteSet u) {
        return (this.getCount(thing) <= u.getCount(thing) && this.left.union(this.right).subset(u));
    }

    // new methods
    public int getCount(T elt) {
        if (this.thing.compareTo(elt) == 0) {
            return counter;
        } else if (this.thing.compareTo(elt) > 0) {
            return left.getCount(elt);
        } else {
            return right.getCount(elt);
        }
    }

    public FiniteSet addSome(T elt, int i) {
        // same as add except with maximum
        if (this.thing.compareTo(elt) == 0) {
            int maximum = Math.max(0, this.counter + i);
            return new FullSet(this.thing, maximum, this.left, this.right);
        } else if (elt.compareTo(this.thing) > 0) {
            return new FullSet(this.thing, this.counter, this.left, this.right.addSome(elt, i));
        } else {
            return new FullSet(this.thing, this.counter, this.left.addSome(elt, i), this.right);
        }
    }

    public FiniteSet removeSome(T elt, int i) {
        // same as addSome pretty much
        if (this.thing.compareTo(elt) == 0) {
            int maximum = Math.max(0, this.counter - i);
            return new FullSet(this.thing, maximum, this.left, this.right);
        } else if (elt.compareTo(this.thing) > 0) {
            return new FullSet(this.thing, this.counter, this.left, this.right.removeSome(elt, i));
        } else {
            return new FullSet(this.thing, this.counter, this.left.removeSome(elt, i), this.right);
        }
    }

    public FiniteSet removeAll(T elt) {
        // like a more dramatic removeSome
        if (this.thing.compareTo(elt) == 0) {
            return left.union(right);
        } else if (elt.compareTo(this.thing) > 0) {
            return new FullSet(this.thing, this.counter, this.left, this.right.removeAll(elt));
        } else {
            return new FullSet(this.thing, this.counter, this.left.removeAll(elt), this.right);
        }
    }

    public Sequence<T> seq() {
        return new FullSeq(thing, counter, (new SequenceCat(this.left.seq(), this.right.seq())));
    }

    
    // others
    public int countIt() {
        return countItS(this.seq());
    }

    public int countItS(Sequence<T> as) {
        int counter = 0;
        while (as.hasNext()) {
            counter = counter + 1;
            as = as.next();
        }
        return counter;
    }

    public String toStringIt() {
        return toStringIts(this.seq());
    }

    public String toStringIts(Sequence<T> as) {
        StringBuffer string = new StringBuffer("");
        while (as.hasNext()) {
            string.append(as.next().stringify());
            string.append(" ");
            as = as.next();
        }
        return string.toString();
    }

}


// can i get rid of all the this.es?
