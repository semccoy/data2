package data2;

import static data2.Tests.*;

public class Data2 {

    public static void main(String[] args) throws Exception {

        System.out.println("random int checker - " + randInt(0, 100) + " " + randInt(0, 100) + " " + randInt(0, 100));

        int adNauseum = 1; //repeats all tests this many times
        for (int i = 0; i < adNauseum; i++) {

            Tests determinedInt = new Tests(new RandomInteger());
            Tests determinedString = new Tests(new RandomString());

            
            int randomInt = randInt(0, 1);
        determinedInt.isEmptyHuhCardCheck(randomInt);
        determinedString.isEmptyHuhCardCheck(randomInt);
            
        // etc

//
//            //  System.out.println("\ncardAddCheck:");
//            cardAddCheck(0);
//            cardAddCheck(5);
//            cardAddCheck(randInt(0, 100));
//            cardAddCheck(randInt(0, 100));
//            cardAddCheck(randInt(0, 100));
//
//            // System.out.println("\nremoveCardCheck:");
//            removeCardCheck(createFiniteSet(randInt(0, 10)));
//            removeCardCheck(createFiniteSet(randInt(0, 10)));
//            removeCardCheck(createFiniteSet(randInt(0, 10)));
//
//            // System.out.println("\naddMemberCheck:");
//            addMemberCheck(createFiniteSet(randInt(0, 10)));
//            addMemberCheck(createFiniteSet(randInt(0, 10)));
//            addMemberCheck(createFiniteSet(randInt(0, 10)));
//
//            // System.out.println("\nunionSubsetCheck:");
//            unionSubsetCheck(createFiniteSet(randInt(0, 10)), createFiniteSet(randInt(0, 10)), createFiniteSet(randInt(0, 10)));
//            unionSubsetCheck(createFiniteSet(randInt(0, 10)), createFiniteSet(randInt(0, 10)), createFiniteSet(randInt(0, 10)));
//            unionSubsetCheck(createFiniteSet(randInt(0, 10)), createFiniteSet(randInt(0, 10)), createFiniteSet(randInt(0, 10)));
//
//            // System.out.println("\nunionMemberCheck:");
//            unionMemberCheck(createFiniteSet(randInt(0, 10)), createFiniteSet(randInt(0, 10)));
//            unionMemberCheck(createFiniteSet(randInt(0, 10)), createFiniteSet(randInt(0, 10)));
//            unionMemberCheck(createFiniteSet(randInt(0, 10)), createFiniteSet(randInt(0, 10)));
//
//            // System.out.println("\nmemberInterCheck:");
//            memberInterCheck(createFiniteSet(randInt(0, 10)), createFiniteSet(randInt(0, 10)), randInt(0, 100));
//            memberInterCheck(createFiniteSet(randInt(0, 10)), createFiniteSet(randInt(0, 10)), randInt(0, 100));
//            memberInterCheck(createFiniteSet(randInt(0, 10)), createFiniteSet(randInt(0, 10)), randInt(0, 100));
//
//            // System.out.println("\ndiffEqualCheck:");
//            diffEqualCheck(createFiniteSet(randInt(0, 10)), createFiniteSet(randInt(0, 10)));
//            diffEqualCheck(createFiniteSet(randInt(0, 10)), createFiniteSet(randInt(0, 10)));
//            diffEqualCheck(createFiniteSet(randInt(0, 10)), createFiniteSet(randInt(0, 10)));
//
//            // System.out.println("\naddUnionRemoveInterEqualCheck:");
//            addUnionRemoveInterEqualCheck(createFiniteSet(randInt(0, 10)), createFiniteSet(randInt(0, 10)));
//            addUnionRemoveInterEqualCheck(createFiniteSet(randInt(0, 10)), createFiniteSet(randInt(0, 10)));
//            addUnionRemoveInterEqualCheck(createFiniteSet(randInt(0, 10)), createFiniteSet(randInt(0, 10)));

            System.out.println("all tests passed");
        }
    }
}
