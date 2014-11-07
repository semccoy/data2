package data2;

import java.util.Random;

public class FullBag<T extends Comparable> implements MultiSet<T>, Sequenced<T> {

    T thing;
    int counter;
    MultiSet left;
    MultiSet right;
    boolean isRed;

    // constructors for different possibilities
    public FullBag(T thing) {
        this.thing = thing;
        this.counter = 1;
        this.left = empty();
        this.right = empty();
    }

    public FullBag(T thing, int counter) {
        this.thing = thing;
        this.counter = counter;
        this.left = empty();
        this.right = empty();
    }

    public FullBag(T thing, MultiSet left, MultiSet right) {
        this.thing = thing;
        this.counter = 1;
        this.left = left;
        this.right = right;
    }

    public FullBag(T thing, int counter, MultiSet left, MultiSet right) {
        this.thing = thing;
        this.counter = counter;
        this.left = left;
        this.right = right;
    }

    public FullBag(T thing, int counter, boolean isRed, MultiSet<T> left, MultiSet<T> right) {
        this.thing = thing;
        this.counter = counter;
        this.isRed = isRed;
        this.left = left;
        this.right = right;
    }

    // old methods
    public static MultiSet empty() {
        return new EmptyBag();
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

    public MultiSet<T> add(T elt) {
        return addSome(elt, 1);
    }

    public MultiSet<T> remove(T elt) {
        // same as add pretty much
        if (this.thing.compareTo(elt) == 0) {
            return new FullBag(this.thing, this.counter - 1, this.left, this.right);
        } else if (elt.compareTo(this.thing) > 0) {
            return new FullBag(this.thing, this.counter, this.left, this.right.remove(elt));
        } else {
            return new FullBag(this.thing, this.counter, this.left.remove(elt), this.right);
        }
    }

    public MultiSet<T> union(MultiSet u) {
        return left.union(right.union(u).addSome(thing, this.getCount(thing)));
    }

    public MultiSet<T> inter(MultiSet u) {
        if (u.member(this.thing)) {
            int minimum = Math.min(u.getCount(thing), this.getCount(thing));
            return new FullBag(this.thing, minimum, this.left.inter(u), this.right.inter(u));
        } else {
            return this.left.inter(u).union(this.right.inter(u));
        }
    }

    public MultiSet<T> diff(MultiSet u) {
        return left.union(right).diff(u.removeSome(thing, this.getCount(thing)));
    }

    public boolean equal(MultiSet u) {
        // no change :)
        return (this.subset(u) && u.subset(this));
    }

    public boolean subset(MultiSet u) {
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

    public MultiSet<T> addSome(T elt, int i) {
        return this.addInner(elt, i).blacken();
    }

    public MultiSet<T> removeSome(T elt, int i) {
        if (this.thing.compareTo(elt) == 0) {
            return new FullBag(this.thing, this.counter - i, this.left, this.right);
        } else if (elt.compareTo(this.thing) > 0) {
            return new FullBag(this.thing, this.counter, this.left, this.right.removeSome(elt, i));
        } else {
            return new FullBag(this.thing, this.counter, this.left.removeSome(elt, i), this.right);
        }
    }

    public MultiSet<T> removeAll(T elt) {
        // like a more dramatic removeSome
        if (this.thing.compareTo(elt) == 0) {
            return left.union(right);
        } else if (elt.compareTo(this.thing) > 0) {
            return new FullBag(this.thing, this.counter, this.left, this.right.removeAll(elt));
        } else {
            return new FullBag(this.thing, this.counter, this.left.removeAll(elt), this.right);
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

    // rb tree
    public MultiSet<T> blacken() {
        return new FullBag(this.thing, this.counter, false, this.left, this.right);
    }

    public boolean isRedHuh() {
        return isRed;
    }

    public MultiSet<T> addInner(T elt, int i) {
        if (elt.compareTo(this.thing) == 0) {
            return new FullBag(this.thing, this.counter + i, true, this.left, this.right);
        } else if (elt.compareTo(this.thing) < 0) {
            MultiSet tempBag = new FullBag(this.thing, this.counter, true, ((MultiSet) this.left).addInner(elt, i), this.right);
            return tempBag.balance();
        } else {
            MultiSet tempBag = new FullBag(this.thing, this.counter, true, this.left, this.right.addInner(elt, i));
            return tempBag.balance();
        }
    }

    public MultiSet<T> rbInsert(T elt, int i) {
        return this.addInner(elt, i).blacken();
    }

    // refer to: https://lh5.googleusercontent.com/-vFblLq5ooAc/VFgPtnU-tSI/AAAAAAAAAI4/E09IMdDCFz0/s1600/20141103_165446.jpg
    public MultiSet<T> balance() {
        FullBag left;
        FullBag leftOfLeft;
        FullBag leftOfRight;
        FullBag right;
        FullBag rightOfLeft;
        FullBag rightOfRight;

        // I. if black and everything else is a full bag
        // then put leftOfLeft to the left of the original left,
        // and put the node on the right of the original left
        if ((!this.isRedHuh() && (this.left instanceof FullBag) && (((FullBag) this.left).left instanceof FullBag)
                && ((FullBag) this.left).isRedHuh() && ((FullBag) this.left).left.isRedHuh())) {

            left = ((FullBag) this.left);
            leftOfLeft = ((FullBag) left.left);

            return new FullBag(left.thing, left.counter, true,
                    new FullBag(leftOfLeft.thing, leftOfLeft.counter, false, leftOfLeft.left, leftOfLeft.right),
                    new FullBag(this.thing, this.counter, false, leftOfLeft.right, this.right));

            // II. if black and everything else is a full bag
            // then make rightOfLeft the new node,
            // and put original node on the right of rightOfLeft,
            // keeping left where it was
        } else if ((!this.isRedHuh() && (this.left instanceof FullBag) && (((FullBag) this.left).right instanceof FullBag)
                && ((FullBag) this.left).isRedHuh() && ((FullBag) this.left).right.isRedHuh())) {

            left = ((FullBag) this.left);
            leftOfLeft = ((FullBag) left.left);
            rightOfLeft = ((FullBag) left.right);

            return new FullBag(rightOfLeft.thing, rightOfLeft.counter, true,
                    new FullBag(left.thing, left.counter, false, leftOfLeft, rightOfLeft.left),
                    new FullBag(this.thing, this.counter, false, rightOfLeft.right, this.right));

            // III. (I. in reverse) if black and everything else is a full bag
            // then put rightOfRight to the right of the original right
            // and put the node on the left of the original right
        } else if ((!this.isRedHuh() && (this.right instanceof FullBag) && (((FullBag) this.right).left instanceof FullBag)
                && ((FullBag) this.right).isRedHuh() && ((FullBag) this.right).left.isRedHuh())) {

            right = ((FullBag) this.right);
            rightOfRight = ((FullBag) right.right);

            return new FullBag(rightOfRight.thing, rightOfRight.counter, true,
                    new FullBag(this.thing, this.counter, false, this.left, rightOfRight.left),
                    new FullBag(right.thing, right.counter, false, rightOfRight.right, right.right));

            // IV. (II. in reverse) if black and everything else is a full bag
            // then make leftOfRight the new node,
            // and put original node on the left of leftOfRight,
            // keeping right where it was
        } else if ((!this.isRedHuh() && (this.right instanceof FullBag) && (((FullBag) this.right).right instanceof FullBag)
                && ((FullBag) this.right).isRedHuh() && ((FullBag) this.right).right.isRedHuh())) {

            right = ((FullBag) this.right);
            rightOfRight = ((FullBag) right.right);
            leftOfRight = ((FullBag) right.left);

            return new FullBag(right.thing, right.counter, true,
                    new FullBag(this.thing, this.counter, false, this.left, leftOfRight),
                    new FullBag(rightOfRight.thing, rightOfRight.counter, false, rightOfRight.left, rightOfRight.right));
            // V. do nothing
            // aw yiss
        } else {
            return this;
        }
    }
}
