package data2;

import java.util.Random;

/**
 * Yields a random integer
 * @see RandomThing
 * Performance: O(n)
 */
public class RandomInteger implements RandomThing<Integer> {

    int min = 0;
    int max = 50;

    public Integer makeRandom() {
        Random rand = new Random();
        int randomNum = rand.nextInt((max - min) + 1) + min;
        return randomNum;
    }
}
