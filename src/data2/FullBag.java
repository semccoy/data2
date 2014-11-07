package data2;

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
        return this.getCount(elt) != 0;
    }

    public MultiSet<T> add(T elt) {
        return addSome(elt, 1);
    }

    public MultiSet<T> remove(T elt) {
        return this.removeSome(elt, 1);
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
        // doesn't need a call to balance because removeSome calls union,
        // and union calls addSome, and addSome calls addInner,
        // and addInner calls balance!
        if (this.thing.compareTo(elt) == 0) {
            // prevents from removing negative
            if ((this.counter - i) < 0) {
                return this.left.union(this.right);
            } else {
                return new FullBag(this.thing, this.counter - i, this.left, this.right);
            }
        } else if (elt.compareTo(this.thing) > 0) {
            return new FullBag(this.thing, this.counter, this.left, this.right.removeSome(elt, i));
        } else {
            return new FullBag(this.thing, this.counter, this.left.removeSome(elt, i), this.right);
        }
    }

    public MultiSet<T> removeAll(T elt) {
        return removeSome(elt, this.getCount(elt));
    }

    public Sequence<T> seq() {
        return new FullSeq(thing, counter, (new SequenceCat(this.left.seq(), this.right.seq())));
    }

    // for visualizing sequences
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
        return this.isRed;
    }

    public MultiSet<T> addInner(T elt, int i) {
        if (elt.compareTo(this.thing) == 0) {
            return new FullBag(this.thing, this.counter + i, true, this.left, this.right);
        } else if (elt.compareTo(this.thing) < 0) {
            return new FullBag(this.thing, this.counter, true, ((MultiSet) this.left).addInner(elt, i), this.right).balance();
        } else {
            return new FullBag(this.thing, this.counter, true, this.left, this.right.addInner(elt, i)).balance();
        }
    }

    // refer to: https://lh5.googleusercontent.com/-vFblLq5ooAc/VFgPtnU-tSI/AAAAAAAAAI4/E09IMdDCFz0/s1600/20141103_165446.jpg
    public MultiSet<T> balance() {
        if (!this.isRedHuh() && this.left instanceof FullBag && ((FullBag) this.left).left instanceof FullBag
                && ((FullBag) this.left).isRedHuh() && ((FullBag) ((FullBag) this.left).left).isRedHuh()) {

            FullBag L1 = ((FullBag) this.left);
            FullBag L2 = ((FullBag) L1.left);

            //System.out.println("Case 1");
            return new FullBag(L1.thing, L1.counter, true,
                    new FullBag(L2.thing, L2.counter, false, L2.left, L2.right),
                    new FullBag(this.thing, this.counter, false, L1.right, this.right));

        } else if (!this.isRedHuh() && this.left instanceof FullBag && ((FullBag) this.left).right instanceof FullBag
                && ((FullBag) this.left).isRedHuh() && ((FullBag) ((FullBag) this.left).right).isRedHuh()) {

            FullBag L1 = ((FullBag) this.left);
            FullBag LR = ((FullBag) L1.right);

            //System.out.println("Case 2");
            return new FullBag(LR.thing, LR.counter, true,
                    new FullBag(L1.thing, L1.counter, false, L1.left, LR.left),
                    new FullBag(this.thing, this.counter, false, LR.right, this.right));

        } else if (!this.isRedHuh() && this.right instanceof FullBag && ((FullBag) this.right).left instanceof FullBag
                && ((FullBag) this.right).isRedHuh() && ((FullBag) ((FullBag) this.right).left).isRedHuh()) {

            FullBag R1 = ((FullBag) this.right);
            FullBag RL = ((FullBag) R1.left);

            //System.out.println("Case 3");
            return new FullBag(RL.thing, RL.counter, true,
                    new FullBag(this.thing, this.counter, false, this.left, RL.left),
                    new FullBag(R1.thing, R1.counter, false, RL.right, R1.right));

        } else if (!this.isRedHuh() && this.right instanceof FullBag && ((FullBag) this.right).right instanceof FullBag
                && ((FullBag) this.right).isRedHuh() && ((FullBag) ((FullBag) this.right).right).isRedHuh()) {

            FullBag R1 = ((FullBag) this.right);
            FullBag R2 = ((FullBag) R1.right);

            //System.out.println("Case 4");
            return new FullBag(R1.thing, R1.counter, true,
                    new FullBag(this.thing, this.counter, false, this.left, R1.left),
                    new FullBag(R2.thing, R2.counter, false, R2.left, R2.right));

        } else {
            //System.out.println("Case 5");
            return this;
        }
    }

    public boolean helper1() {

        if (this.isRed && (this.left.isRedHuh() || this.right.isRedHuh())) {
            return false;
        } else {
            return this.left.helper1() && this.right.helper1();
        }
    }

    public int pathCounter() {
        if (Math.random() > .5) {
            if (!this.isRedHuh()) {
                return 1 + this.left.pathCounter();
            }
            return this.left.pathCounter();
        }
        if (!this.isRedHuh()) {
            return 1 + this.right.pathCounter();
        }
        return this.right.pathCounter();
    }
}
