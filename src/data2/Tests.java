package data2;

import java.util.Random;

public class Tests<T extends Comparable> {

    // setup
    RandomThing<T> rt;

    public Tests(RandomThing<T> rt) {
        this.rt = rt;
    }

    public static MultiSet empty() {
        return new EmptyBag();
    }

    public static int randInt(int min, int max) {
        Random rand = new Random();
        int randomNum = rand.nextInt((max - min) + 1) + min;
        return randomNum;
    }

    public MultiSet<T> randomMultiSet(int size) {
        // just a generic version of what we had with finite sets
        if (size == 0) {
            return empty();
        } else {
            return randomMultiSet(size - 1).addSome(rt.makeRandom(), randInt(1, maxRandomSize));
        }
    }

    // tests
    static int repeats = 100;
    int maxRandomSize = 10;

    public void isEmptyHuhCardCheck(int x) throws Exception {
        for (int i = 0; i < repeats; i++) {
            if (x == 0) {
                MultiSet fs = empty();
                if (!fs.isEmptyHuh()) {
                    throw new Exception("isEmptyHuhCardCheck - empty bag failure");
                }
            } else {
                int length = randInt(1, maxRandomSize);
                MultiSet fs = randomMultiSet(length);
                if (fs.isEmptyHuh()) {
                    throw new Exception("isEmptyHuhCardCheck - full bag failure");
                }
            }
        }
    }

    public void cardAddCheck() throws Exception {
        for (int i = 0; i < repeats; i++) {
            int length = randInt(0, maxRandomSize);
            MultiSet fs = randomMultiSet(length);
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
            MultiSet fs = randomMultiSet(length);
            int initialCard = fs.cardinality();
            if (fs.addSome(rt.makeRandom(), x).cardinality() != initialCard + x) {
                throw new Exception("cardAddSomeCheck - card increase failure");
                // x != 0 is necessary in case we try to add zero things (then initialCard == finalCard)
            } else if (x != 0 && fs.addSome(rt.makeRandom(), x).cardinality() == initialCard) {
                throw new Exception("cardAddSomeCheck - adding things failure");
            }
        }
    }

    public void cardRemoveCheck() throws Exception {
        for (int i = 0; i < repeats; i++) {
            T randomThing = rt.makeRandom();
            int length = randInt(0, maxRandomSize);
            MultiSet fs = randomMultiSet(length);
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
            MultiSet fs = randomMultiSet(length);
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
            MultiSet fs = randomMultiSet(length);
            fs.add(randomThing);
            if (fs.getCount(randomThing) >= 1 && !fs.member(randomThing)) {
                throw new Exception("addMemberCheck - thing not added but counter increase");
            }
            if (fs.getCount(randomThing) == 0 && fs.member(randomThing)) {
                throw new Exception("addMemberCheck - thing added but counter not increase");
            }
        }
    }

    public void addMemberInterCheck() throws Exception {
        for (int i = 0; i < repeats; i++) {
            int length = randInt(0, maxRandomSize);
            T randomThing = rt.makeRandom();
            MultiSet fs1 = randomMultiSet(length);
            MultiSet fs2 = randomMultiSet(length);
            MultiSet fs1Plus = fs1.add(randomThing);
            MultiSet fs2Plus = fs2.add(randomThing);
            // we've already tested that adding worksin addMemberCheck() so not re-testing here
            if (fs1Plus.member(randomThing) && fs2Plus.member(randomThing)
                    && fs1Plus.inter(fs2Plus).isEmptyHuh()) {
                throw new Exception("addMemberInterCheck - thing in two bags not intersection properly");
            }
        }
    }

    public void addRemoveEqualCheck() throws Exception {
        for (int i = 0; i < repeats; i++) {
            T randomThing = rt.makeRandom();
            int length = randInt(0, maxRandomSize);
            MultiSet fs = randomMultiSet(length);
            MultiSet fsPlus = fs.add(randomThing);
            MultiSet fsMinus = fsPlus.remove(randomThing);
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
            MultiSet fs = randomMultiSet(length);
            MultiSet fsPlus = fs.addSome(randomThing, x);
            MultiSet fsMinus = fsPlus.removeSome(randomThing, x);
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
            MultiSet fs1 = randomMultiSet(length);
            MultiSet fs2 = randomMultiSet(length);
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
            MultiSet fs1 = randomMultiSet(length);
            MultiSet fs2 = randomMultiSet(length);
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
            MultiSet fs1 = randomMultiSet(length);
            MultiSet fs2 = randomMultiSet(length);
            MultiSet fs1Plus = fs1.add(randomThing);
            MultiSet fs2Plus = fs2.add(randomThing);
            MultiSet u1 = (fs1Plus.union(fs2Plus));
            MultiSet u2 = (fs2Plus.union(fs1Plus));
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
            MultiSet fs1 = randomMultiSet(length);
            MultiSet fs2 = fs1.addSome(randomThing, randomInt);
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
            T randomThing = rt.makeRandom();
            MultiSet fs1 = randomMultiSet(length).add(randomThing);
            MultiSet fs2 = randomMultiSet(length);
            if (fs1.diff(fs2).member(randomThing)) {
                if (!fs1.member(randomThing)) {
                    throw new Exception("diffMemberCheck - thing not put in bag correctly");
                }
            }
        }
    }

    public void diffEqualCheck() throws Exception {
        for (int i = 0; i < repeats; i++) {
            int length = randInt(0, maxRandomSize);
            MultiSet fs1 = randomMultiSet(length);
            MultiSet fs2 = randomMultiSet(length);
            if (fs1.diff(fs2).isEmptyHuh() && fs2.diff(fs1).isEmptyHuh() && !fs1.equal(fs2) && fs2.equal(fs1)) {
                throw new Exception("diffEqualCheck - things with no diff not seen as equal");
            } else if (fs1.equal(fs2) && !fs1.diff(fs2).isEmptyHuh() && !fs2.diff(fs1).isEmptyHuh()) {
                throw new Exception("diffEqualCheck - equal things not seen as having no diff");
            }
        }
    }

    public void equalIntercheck() throws Exception {
        for (int i = 0; i < repeats; i++) {
            int length = randInt(0, maxRandomSize);
            MultiSet fs1 = randomMultiSet(length);
            MultiSet fs2 = randomMultiSet(length);
            if (fs1.union(fs2).equal(fs1.inter(fs2)) && !fs1.equal(fs2)) {
                throw new Exception("equalIntercheck - inter and equal of two different bags can't be the same");
            }
        }
    }

    public void isRedHuhBlackenCheck() throws Exception {
        for (int i = 0; i < repeats; i++) {
            T randomThing = rt.makeRandom();
            int counter = 1;
            MultiSet left = empty();
            MultiSet right = empty();

            //random every time!
            boolean isRed = randInt(0, 1) == 0;

            MultiSet fs = new FullBag(randomThing, counter, isRed, left, right);
            MultiSet fsb = fs.blacken();

            if (isRed) {
                // if red thing is actually black, it should be black instead
                if (!fs.isRedHuh()) {
                    throw new Exception("isRedHuhCheck - red thing should be black");
                } // if black thing is actually red, it should be red instead
                else if (fsb.isRedHuh()) {
                    throw new Exception("isRedHuhCheck - black thing should be red");
                }
            } else {
                // if black thing is actually red, it should be red instead
                if (fs.isRedHuh()) {
                    throw new Exception("isRedHuhCheck - black thing should be red");
                }
            }
        }
    }

    public void balanceCheck() throws Exception {
        for (int i = 0; i < repeats; i++) {
            MultiSet theBag = new EmptyBag();
            for (int j = 0; j < maxRandomSize; j++) {
                theBag = theBag.add(randInt(1, 100));
            }
            boolean helper2 = true;
            int arbitraryBCount = theBag.pathCounter();
            for (int j = 0; j < maxRandomSize; j++) {
                if (arbitraryBCount != theBag.pathCounter()) {
                    helper2 = false;
                }
            }
            System.out.println("" + theBag.helper1());
            System.out.println("" + helper2);
            if (!theBag.helper1() || !helper2) {
                throw new Exception("balanceCheck - tree imbalanced");
            }
        }

    }
}

//    // these are just for visualizing sequences
//    // c/o bryce - mostly just checking to see how these work :)
//    public void countItCardCheck() throws Exception {
//        for (int i = 0; i < repeats; i++) {
//            int length = randInt(0, maxRandomSize);
//            MultiSet fs = randomMultiSet(length);
//            if (fs.countIt() != fs.cardinality()) {
//                throw new Exception("countItCardCheck - countIt ≠ card");
//            }
//        }
//    }
//
//    public void toStringItCheck() {
//        for (int i = 0; i < 5; i++) {
//            int length = randInt(0, maxRandomSize);
//            MultiSet fs = randomMultiSet(length);
//            System.out.println("Sequencing Output: " + fs.toStringIt());
//        }
//    }
}
