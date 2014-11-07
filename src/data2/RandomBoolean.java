package data2;

/**
 * Yields a random boolean
 * @see RandomThing
 * Performance: O(n)
 */
public class RandomBoolean implements RandomThing<Boolean> {

    public Boolean makeRandom() {
        return Tests.randInt(0, 1) == 0;
    }
}
