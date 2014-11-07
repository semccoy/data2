package data2;

import java.util.Random;

/**
 * Yields a random string
 * @see RandomThing
 * Performance: O(n)
 */
public class RandomString implements RandomThing<String> {

    int maxLength = 20;

    // can put whatever you want in the input string
    // (and doesn't require knowledge of unicode)
    String stringThings = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890`~!@#$%^&*()-_=+[{]}|;:',<.>/?";
    Random rand = new Random();

    public String makeRandom() {
        int length = new Random().nextInt(maxLength);
        StringBuilder newString = new StringBuilder("");
        for (int i = 0; i < length; i++) {
            newString.append(stringThings.charAt(new Random().nextInt(stringThings.length())));
        }
        return newString.toString();
    }
}
