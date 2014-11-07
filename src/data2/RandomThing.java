package data2;

/**
 * Interface housing method for random type production classes
 * @see RandomBoolean
 * @see RandomInteger
 * @see RandomString
 * Performance: O(n)
 */
public interface RandomThing<T extends Comparable> {

    /**
     * Generates a random object
     * @return T a random object
     */
    public T makeRandom();
}
