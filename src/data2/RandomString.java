package data2;

import java.util.Random;

public class RandomString implements RandomThing<String> {

    int maxLength = 20;

    String stringThings = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
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
