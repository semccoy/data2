package data2;

import java.util.Random;

public class RandomBoolean implements RandomThing<Boolean> {

    public Boolean makeRandom() {
       return Tests.randInt(0,1) == 0;
    }
}
