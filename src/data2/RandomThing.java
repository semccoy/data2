package data2;

public interface RandomThing<T extends Comparable> {
    // convenient way to house all classes that produce random generics
    public T makeRandom();
}
