package data2;

import java.util.Random;

public class Tests<T extends Comparable> {

    // setup
    RandomThing<T> rt;

    public Tests(RandomThing<T> rt) {
        this.rt = rt;
    }

    public static FiniteSet empty() {
        return new EmptySet();
    }

    public static int randInt(int min, int max) {
        Random rand = new Random();
        int randomNum = rand.nextInt((max - min) + 1) + min;
        return randomNum;
    }

    public FiniteSet<T> randomFiniteSet(int size) {
        // just a generic version of what we had with finite sets
        if (size == 0) {
            return empty();
        } else {
            return randomFiniteSet(size - 1).addSome(rt.makeRandom(), randInt(1, maxRandomSize));
        }
    }

    // tests
    int repeats = 100;
    int maxRandomSize = 10;

    public void isEmptyHuhCardCheck(int x) throws Exception {
        for (int i = 0; i < repeats; i++) {
            if (x == 0) {
                FiniteSet fs = empty();
                if (!fs.isEmptyHuh()) {
                    throw new Exception("isEmptyHuhCardCheck - empty bag failure");
                }
            } else {
                int length = randInt(1, maxRandomSize);
                FiniteSet fs = randomFiniteSet(length);
                if (fs.isEmptyHuh()) {
                    throw new Exception("isEmptyHuhCardCheck - full bag failure");
                }
            }
        }
    }

    public void cardAddCheck() throws Exception {
        for (int i = 0; i < repeats; i++) {
            int length = randInt(0, maxRandomSize);
            FiniteSet fs = randomFiniteSet(length);
            int init = fs.cardinality();
            if (fs.add(rt.makeRandom()).cardinality() != init + 1) {
                throw new Exception("cardAddCheck - card increase failure");
            } else if (fs.add(rt.makeRandom()).cardinality() == init) {
                throw new Exception("cardAddCheck - adding thing failure");
            }
        }
    }

    public void cardAddSomeCheck(int x) throws Exception {
        // mostly a check that add/removeSome functions work, really just same as above
        for (int i = 0; i < repeats; i++) {
            int length = randInt(0, maxRandomSize);
            FiniteSet fs = randomFiniteSet(length);
            int initialCard = fs.cardinality();
            if (fs.addSome(rt.makeRandom(), x).cardinality() != initialCard + x) {
                throw new Exception("cardAddSomeCheck - card increase failure");
            } else if (fs.addSome(rt.makeRandom(), x).cardinality() == initialCard) {
                throw new Exception("cardAddSomeCheck - adding things failure");
            }
        }
    }

    public void cardRemoveCheck() throws Exception {
        for (int i = 0; i < repeats; i++) {
            T randomThing = rt.makeRandom();
            int length = randInt(0, maxRandomSize);
            FiniteSet fs = randomFiniteSet(length);
            int endCard = fs.remove(randomThing).cardinality();
            if (fs.getCount(randomThing) >= 1 && endCard != fs.cardinality() - 1) {
                throw new Exception("cardRemoveCheck - card decrease failure");
            }
            if (fs.getCount(randomThing) == 0 && endCard != fs.cardinality()) {
                throw new Exception("cardRemoveCheck - card no change failure");
            }
        }
    }

    public void cardEmptyCheck() throws Exception {
        for (int i = 0; i < repeats; i++) {
            int length = randInt(0, maxRandomSize);
            FiniteSet fs = randomFiniteSet(length);
            if (fs.isEmptyHuh() && fs.cardinality() != 0) {
                throw new Exception("cardEmptyCheck - empty bag has card ≠ 0");
            } else if (!fs.isEmptyHuh() && fs.cardinality() == 0) {
                throw new Exception("cardEmptyCheck - full bag has card = 0");
            }
        }
    }

    public void addMemberCheck() throws Exception {
        for (int i = 0; i < repeats; i++) {
            T randomThing = rt.makeRandom();
            int length = randInt(0, maxRandomSize);
            FiniteSet fs = randomFiniteSet(length);
            fs.add(randomThing);
            if (fs.getCount(randomThing) >= 1 && !fs.member(randomThing)) {
                throw new Exception("addMemberCheck - thing not added but counter increase");
            }
            if (fs.getCount(randomThing) == 0 && fs.member(randomThing)) {
                throw new Exception("addMemberCheck - thing added but counter not increase");
            }
        }
    }

    public void addRemoveEqualCheck() throws Exception {
        for (int i = 0; i < repeats; i++) {
            T randomThing = rt.makeRandom();
            int length = randInt(0, maxRandomSize);
            FiniteSet fs = randomFiniteSet(length);
            FiniteSet fsPlus = fs.add(randomThing);
            FiniteSet fsMinus = fsPlus.remove(randomThing);
            if (fsPlus.getCount(randomThing) - 1 != fs.getCount(randomThing)) {
                throw new Exception("addRemoveEqualCheck - counter not increase after adding thing");
            }
            if (fsMinus.getCount(randomThing) != fs.getCount(randomThing)) {
                throw new Exception("addRemoveEqualCheck - counter not restored about removing added thing");
            }
            if (!fs.equal(fsMinus)) {
                throw new Exception("addRemoveEqualCheck - bag not returned to original state");
            }
        }
    }

    public void addRemoveSomeEqualCheck(int x) throws Exception {
        // again, just a check to see if add/removeSome work, otherwise same as above
        for (int i = 0; i < repeats; i++) {
            T randomThing = rt.makeRandom();
            int length = randInt(0, maxRandomSize);
            FiniteSet fs = randomFiniteSet(length);
            FiniteSet fsPlus = fs.addSome(randomThing, x);
            FiniteSet fsMinus = fsPlus.removeSome(randomThing, x);
            // maybe - x + 1?
            if (fsPlus.getCount(randomThing) - x != fs.getCount(randomThing)) {
                throw new Exception("addRemoveSomeEqualCheck - counter not increase after adding things");
            }
            if (fsMinus.getCount(randomThing) != fs.getCount(randomThing)) {
                throw new Exception("addRemoveSomeEqualCheck - counter not restored about removing added things");
            }
            if (!fs.equal(fsMinus)) {
                throw new Exception("addRemoveSomeEqualCheck - bag not returned to original state");
            }
        }
    }

    public void unionSubsetCheck() throws Exception {
        for (int i = 0; i < repeats; i++) {
            int length = randInt(0, maxRandomSize);
            FiniteSet fs1 = randomFiniteSet(length);
            FiniteSet fs2 = randomFiniteSet(length);
            // could reverse order of these
            if (!fs1.subset(fs1.union(fs2)) || !fs2.subset(fs1.union(fs2))) {
                throw new Exception("unionSubsetCheck - one bag not subset of two bags' union");
            }
        }
    }

    public void unionCardCheck() throws Exception {
        // this sounds like something state employees have to do :(
        for (int i = 0; i < repeats; i++) {
            int length = randInt(0, maxRandomSize);
            FiniteSet fs1 = randomFiniteSet(length);
            FiniteSet fs2 = randomFiniteSet(length);
            // strictly greater than because fs1 and fs2 can be mutually exclusive to start
            if (fs1.union(fs2).cardinality() > fs1.cardinality() + fs2.cardinality()) {
                throw new Exception("unionCardCheck - card not adding properly");
            }
        }
    }

    public void unionMemberCheck() throws Exception {
        // this one does too...
        for (int i = 0; i < repeats; i++) {
            int length = randInt(0, maxRandomSize);
            T randomThing = rt.makeRandom();
            FiniteSet fs1 = randomFiniteSet(length);
            FiniteSet fs2 = randomFiniteSet(length);
            FiniteSet fs1Plus = fs1.add(randomThing);
            FiniteSet fs2Plus = fs2.add(randomThing);
            FiniteSet u1 = (fs1Plus.union(fs2Plus));
            FiniteSet u2 = (fs2Plus.union(fs1Plus));
            if (!u1.member(randomThing) && !u2.member(randomThing)) {
                throw new Exception("unionMemberCheck - unions not membering properly");
            }
        }
    }

    public void memberRemoveAllCheck() throws Exception {
        for (int i = 0; i < repeats; i++) {
            T randomThing = rt.makeRandom();
            int length = randInt(0, maxRandomSize);
            int randomInt = randInt(0, 10);
            FiniteSet fs1 = randomFiniteSet(length);
            FiniteSet fs2 = fs1.addSome(randomThing, randomInt);
            if (fs2.removeAll(randomThing).member(randomThing)) {
                throw new Exception("memberRemoveAllCheck - thing in bag that was removeAlled");
            }
            if (fs2.removeAll(randomThing).getCount(randomThing) != 0) {
                throw new Exception("memberRemoveAllCheck - thing appeared more than zero times");
            }
        }
    }

    public void diffMemberCheck() throws Exception {
        // check to see if the bags got... dismembered (*rimshot*)
        for (int i = 0; i < repeats; i++) {
            int length = randInt(0, maxRandomSize);
            FiniteSet fs1 = randomFiniteSet(length);
            FiniteSet fs2 = randomFiniteSet(length);
            T randomThing = rt.makeRandom();
            if (fs1.diff(fs2).member(randomThing)) {
                if (!fs1.member(randomThing)) {
                    throw new Exception("diffMemberCheck - thing not put in bag correctly");
                }
            }
        }
    }

    public void equalIntercheck() throws Exception {
        for (int i = 0; i < repeats; i++) {
            int length = randInt(0, maxRandomSize);
            FiniteSet fs1 = randomFiniteSet(length);
            FiniteSet fs2 = randomFiniteSet(length);
            if (fs1.union(fs2).equal(fs1.inter(fs2)) && !fs1.equal(fs2)) {
                throw new Exception("equalIntercheck - inter and equal of two different bags can't be the same");
            }
        }
    }

    // c/o bryce - mostly just checking to see how these work :)
    
    public void countItCardCheck() throws Exception {
        for (int i = 0; i < repeats; i++) {
            int length = randInt(0, maxRandomSize);
            FiniteSet fs = randomFiniteSet(length);
            if (fs.countIt() != fs.cardinality()) {
                throw new Exception("countItCardCheck - countIt ≠ card");
            }
        }
    }

    public void toStringItCheck() {
        for (int i = 0; i < 5; i++) {
            int length = randInt(0, maxRandomSize);
            FiniteSet fs = randomFiniteSet(length);
            System.out.println("Sequencing Output: " + fs.toStringIt());
        }
    }

    ////////////
    
    
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
