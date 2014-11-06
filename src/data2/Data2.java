package data2;

import static data2.Tests.*;

public class Data2 {

    public static void main(String[] args) throws Exception {

        System.out.println("Random Int Checker - " + randInt(0, 100) + " " + randInt(0, 100) + " " + randInt(0, 100));

        int adNauseum = 10; //repeats all tests this many times
        for (int i = 0; i < adNauseum; i++) {

            Tests inty = new Tests(new RandomInteger());
            Tests stringy = new Tests(new RandomString());

            int randomInt = randInt(0, 1);
            inty.isEmptyHuhCardCheck(randomInt);
            stringy.isEmptyHuhCardCheck(randomInt);

            inty.cardAddCheck();
            stringy.cardAddCheck();

            int randInt = randInt(0, 20);
            inty.cardAddSomeCheck(randInt);
            stringy.cardAddSomeCheck(randInt);

            inty.cardRemoveCheck();
            stringy.cardRemoveCheck();

            inty.cardEmptyCheck();
            stringy.cardEmptyCheck();

            inty.addMemberCheck();
            stringy.addMemberCheck();

            inty.addMemberInterCheck();
            stringy.addMemberInterCheck();

            inty.addRemoveEqualCheck();
            stringy.addRemoveEqualCheck();

            int rInt = randInt(0, 20);
            inty.addRemoveSomeEqualCheck(rInt);
            stringy.addRemoveSomeEqualCheck(rInt);

            inty.unionSubsetCheck();
            stringy.unionSubsetCheck();

            inty.unionCardCheck();
            stringy.unionCardCheck();

            inty.unionMemberCheck();
            stringy.unionMemberCheck();

            inty.memberRemoveAllCheck();
            stringy.memberRemoveAllCheck();

            inty.diffMemberCheck();
            stringy.diffMemberCheck();

            inty.diffEqualCheck();
            stringy.diffEqualCheck();

            inty.equalIntercheck();
            stringy.equalIntercheck();

//            // these are for visualizing sequences - thanks again to bryce :)
//            inty.countItCardCheck();
//            stringy.countItCardCheck();
//
//            inty.toStringItCheck();
//            stringy.toStringItCheck();
        }
        System.out.println("");
        System.out.println("The following tests each passed " + adNauseum * Tests.repeats + " times! :)");
        System.out.println("");
        System.out.println("isEmptyHuhCardCheck()");
        System.out.println("cardAddCheck()");
        System.out.println("cardAddSomeCheck()");
        System.out.println("cardRemoveCheck()");
        System.out.println("cardEmptyCheck()");
        System.out.println("addMemberCheck()");
        System.out.println("addRemoveEqualCheck()");
        System.out.println("addRemoveSomeEqualCheck()");
        System.out.println("addMemberInterCheck()");
        System.out.println("unionSubsetCheck()");
        System.out.println("unionCardCheck()");
        System.out.println("unionMemberCheck()");
        System.out.println("memberRemoveAllCheck()");
        System.out.println("diffMemberCheck()");
        System.out.println("diffEqualCheck()");
        System.out.println("equalIntercheck()");
        System.out.println("");
    }
}
