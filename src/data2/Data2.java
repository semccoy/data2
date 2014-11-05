package data2;

import static data2.Tests.*;

public class Data2 {

    public static void main(String[] args) {

        System.out.println("random int checker - " + randInt(0, 100) + " " + randInt(0, 100) + " " + randInt(0, 100));

        FiniteSet mt = new EmptySet();
        FiniteSet bst0 = createFiniteSet(1);
        FiniteSet bst6 = createFiniteSet(7);

        int adNauseum = 1; //repeats all tests this many times
        for (int i = 0; i < adNauseum; i++) {

            // System.out.println("\nemptyCardCheck:");
            isEmptyHuhCardCheck(mt);
            isEmptyHuhCardCheck(bst0);
            isEmptyHuhCardCheck(bst6);
            isEmptyHuhCardCheck(createFiniteSet(randInt(0, 10)));
            isEmptyHuhCardCheck(createFiniteSet(randInt(0, 10)));
            isEmptyHuhCardCheck(createFiniteSet(randInt(0, 10)));

            //  System.out.println("\ncardAddCheck:");
            cardAddCheck(0);
            cardAddCheck(5);
            cardAddCheck(randInt(0, 100));
            cardAddCheck(randInt(0, 100));
            cardAddCheck(randInt(0, 100));

            // System.out.println("\nremoveCardCheck:");
            removeCardCheck(createFiniteSet(randInt(0, 10)));
            removeCardCheck(createFiniteSet(randInt(0, 10)));
            removeCardCheck(createFiniteSet(randInt(0, 10)));

            // System.out.println("\naddMemberCheck:");
            addMemberCheck(createFiniteSet(randInt(0, 10)));
            addMemberCheck(createFiniteSet(randInt(0, 10)));
            addMemberCheck(createFiniteSet(randInt(0, 10)));

            // System.out.println("\nunionSubsetCheck:");
            unionSubsetCheck(createFiniteSet(randInt(0, 10)), createFiniteSet(randInt(0, 10)), createFiniteSet(randInt(0, 10)));
            unionSubsetCheck(createFiniteSet(randInt(0, 10)), createFiniteSet(randInt(0, 10)), createFiniteSet(randInt(0, 10)));
            unionSubsetCheck(createFiniteSet(randInt(0, 10)), createFiniteSet(randInt(0, 10)), createFiniteSet(randInt(0, 10)));

            // System.out.println("\nunionMemberCheck:");
            unionMemberCheck(createFiniteSet(randInt(0, 10)), createFiniteSet(randInt(0, 10)));
            unionMemberCheck(createFiniteSet(randInt(0, 10)), createFiniteSet(randInt(0, 10)));
            unionMemberCheck(createFiniteSet(randInt(0, 10)), createFiniteSet(randInt(0, 10)));

            // System.out.println("\nmemberInterCheck:");
            memberInterCheck(createFiniteSet(randInt(0, 10)), createFiniteSet(randInt(0, 10)), randInt(0, 100));
            memberInterCheck(createFiniteSet(randInt(0, 10)), createFiniteSet(randInt(0, 10)), randInt(0, 100));
            memberInterCheck(createFiniteSet(randInt(0, 10)), createFiniteSet(randInt(0, 10)), randInt(0, 100));

            // System.out.println("\ndiffEqualCheck:");
            diffEqualCheck(createFiniteSet(randInt(0, 10)), createFiniteSet(randInt(0, 10)));
            diffEqualCheck(createFiniteSet(randInt(0, 10)), createFiniteSet(randInt(0, 10)));
            diffEqualCheck(createFiniteSet(randInt(0, 10)), createFiniteSet(randInt(0, 10)));

            // System.out.println("\naddUnionRemoveInterEqualCheck:");
            addUnionRemoveInterEqualCheck(createFiniteSet(randInt(0, 10)), createFiniteSet(randInt(0, 10)));
            addUnionRemoveInterEqualCheck(createFiniteSet(randInt(0, 10)), createFiniteSet(randInt(0, 10)));
            addUnionRemoveInterEqualCheck(createFiniteSet(randInt(0, 10)), createFiniteSet(randInt(0, 10)));

            System.out.println("all tests passed");
        }
    }
}
