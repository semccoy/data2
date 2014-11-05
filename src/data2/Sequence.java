package data2;

public interface Sequence<T extends Comparable> {

    public T here();

    public boolean hasNext();

    public Sequence<T> next();

    public String stringify();
}
