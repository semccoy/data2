package data2;

import java.util.Random;

public class Tests {

    /* Random Stuff */
    public static int randInt(int min, int max) {
        Random rand = new Random();
        int randomNum = rand.nextInt((max - min) + 1) + min;
        return randomNum;
    }

    public static FiniteSet createFiniteSet(int size) {
        if (size == 0) {
            return FullSet.empty();
        } else {
            return createFiniteSet(size - 1).add(randInt(0, 50));
        }
    }

    /* Testing */
    //
    //all operations checked at least once (most twice or more)
    //
    //fyi, i didn't check empty() anywhere since i feel like you can
    //check isEmptyHuh() and get the same result...
    //
    //
    //
    //if cardinality is 0, u must be empty; if not, u must not be empty
    public static void isEmptyHuhCardCheck(FiniteSet u) {
        if (u.cardinality() == 0) {
            if (u.isEmptyHuh()) {
                //System.out.println("Success - isEmptyHuhCardCheck");
            } else {
                System.out.println("Failure - isEmptyHuhCardCheck");
            }
        } else if (!u.isEmptyHuh()) {
            // System.out.println("Success - isEmptyHuhCardCheck");
        } else {
            System.out.println("Failure - isEmptyHuhCardCheck");
        }
    }

    //adding things makes cardinality go up by however many things were added
    public static void cardAddCheck(int elt) {
        FiniteSet tree = new EmptySet();
        for (int i = 0; i < elt; i++) {
            tree = tree.add(i);
        }
        if (tree.cardinality() == elt) {
            // System.out.println("Success - cardAddCheck");
        } else {
            System.out.println("Failure - cardAddCheck");
        }
    }

    //removing an elt from u results in a <= cardinality
    public static void removeCardCheck(FiniteSet u) {
        int elt = randInt(0, 100);
        FiniteSet smaller = u.remove(elt);
        if (smaller.cardinality() <= u.cardinality()) {
            // System.out.println("Success - removeCardCheck");
        } else {
            System.out.println("Failure - removeCardCheck");
        }
    }

    //if some number is not in one set but in another that's just
    //the first set + the number, adding things to sets works properly!
    //note that x MUST be outside the range of possible values of u
    //else may fail
    public static void addMemberCheck(FiniteSet u) {
        int x = randInt(4200, 4300);
        FiniteSet uPlus = u.add(x);
        if (!u.member(x) && uPlus.member(x)) {
            //  System.out.println("Success - addMemberCheck");
        } else {
            System.out.println("Failure - addMemberCheck");
        }
    }

    //unions of (sub)sets are transitive
    public static void unionSubsetCheck(FiniteSet u, FiniteSet v, FiniteSet w) {
        if ((u.union(v)).subset(w)
                == (u.subset(w) && v.subset(w))) {
            //  System.out.println("Success - unionSubsetCheck");
        } else {
            System.out.println("Failure - unionSubsetCheck");
        }
    }

    //adding things to sets is transitive
    public static void unionMemberCheck(FiniteSet u, FiniteSet v) {
        int elt = randInt(0, 100);
        FiniteSet s1 = u.add(elt);
        FiniteSet s2 = v.add(elt);
        FiniteSet x1 = (s1.union(v));
        FiniteSet x2 = (s2.union(u));
        if (x1.member(elt) && x2.member(elt)) {
            //  System.out.println("Success - unionMemberCheck");
        } else {
            System.out.println("Failure - unionMemberCheck");
        }
    }

    //the intersection of 2 sets cannot be empty if 
    //both of those 2 sets contain the thing
    public static void memberInterCheck(FiniteSet u, FiniteSet v, int elt) {
        u = u.add(elt);
        v = v.add(elt);
        if (u.member(elt) && v.member(elt)) {
            if (!u.inter(v).isEmptyHuh()) {
                //  System.out.println("Success - memberInterCheck");
            } else {
                System.out.println("Failure - memberInterCheck");
            }
        } else {
            System.out.println("Can't touch this");
        }
    }

    //four things:
    //1 - if the difference between 2 sets is empty, they are equal
    //2 - if 2 sets are different, they are not equal
    //3 - and vice versa
    //4 -  " " 
    public static void diffEqualCheck(FiniteSet u, FiniteSet v) {
        if ((u.diff(v)).isEmptyHuh() && (v.diff(u)).isEmptyHuh()) {
            if (u.equal(v)) {
                //   System.out.println("Success - diffEqualCheck pt1");
            } else {
                System.out.println("Failure - diffEqualCheck pt1");
            }
        } else if (!u.equal(v)) {
            //   System.out.println("Success - diffEqualCheck pt1.2");
        } else {
            System.out.println("Failure - diffEqualCheck pt1.2");
        }
        //vice versa
        if (u.equal(v)) {
            if (u.diff(v).isEmptyHuh() && v.diff(u).isEmptyHuh()) {
                //     System.out.println("Success - diffEqualCheck pt2");
            } else {
                System.out.println("Failure - diffEqualCheck pt2");
            }
        } else if (!u.diff(v).isEmptyHuh()
                || !v.diff(u).isEmptyHuh()) {
            //    System.out.println("Success - diffEqualCheck pt2.2");
        } else {
            System.out.println("Failure - diffEqualCheck pt2.2");
        }
    }

    //add one thing that is to u and v that is either
    //already within or outside of both of them to being with, then if:
    //unioning them = u + v + elt,
    //removing elt from them = u + v,
    //interesting them = elt,
    //unioning elt and u + v = u + v + elt
    //and the union of the intersects of the original sets = u + v + elt,
    //then all the u + v + elt sets are equal
    public static void addUnionRemoveInterEqualCheck(FiniteSet u, FiniteSet v) {
        int elt = randInt(0, 200);
        FiniteSet u2 = u.add(elt);
        FiniteSet v2 = v.add(elt);
        FiniteSet unionSet = u2.union(v2); // u + v + elt
        FiniteSet removeSet = unionSet.remove(elt); // u + v
        FiniteSet interSet = u2.inter(v2); // elt
        FiniteSet connectSet = removeSet.union(interSet); // u + v + elt
        FiniteSet uiSet = interSet.union(u).union(v); // u + v + elt
        if (unionSet.equal(uiSet) && unionSet.equal(connectSet)) { // all equal
            //   System.out.println("Success - addUnionRemoveInterEqualCheck");
        } else {
            System.out.println("Failure - addUnionRemoveInterEqualCheck");
        }
    }
}
