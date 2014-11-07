package data2;

public class EmptyBag<T extends Comparable> implements MultiSet<T>, Sequenced<T> {

    boolean isRed;
    
    // constructors
    public void EmptyBag() {
        this.isRed = isRed;
    }
    
     public void EmptyBag(boolean isRed){
        this.isRed = isRed;
    }

    // old methods
    public MultiSet empty() {
        return new EmptyBag();
    }

    public int cardinality() {
        return 0;
    }

    public boolean isEmptyHuh() {
        return true;
    }

    public boolean member(T elt) {
        return false;
    }

    public MultiSet add(T elt) {
        return new FullBag(elt);
    }

    public MultiSet remove(T elt) {
        return this;
    }

    public MultiSet union(MultiSet u) {
        return u;
    }

    public MultiSet inter(MultiSet u) {
        return this;
    }

    public MultiSet diff(MultiSet u) {
        return u;
    }

    public boolean equal(MultiSet u) {
        return u.isEmptyHuh();
    }

    public boolean subset(MultiSet u) {
        return true;
    }

    // new methods
    public int getCount(T elt) {
        return 0;
    }

    public MultiSet addSome(T elt, int i) {
        return new FullBag(elt, i);
    }

    public MultiSet removeSome(T elt, int i) {
        return this;
    }

    public MultiSet removeAll(T elt) {
        return this;
    }

    public Sequence<T> seq() {
        return new EmptySeq();
    }
    
    // others
    public int countIt(){
        return 0;
    }
    
    public String toStringIt() {
        return toStringIts(this.seq());
    }
    
    public String toStringIts(Sequence<T> as){
        return "";
    }
    
    // rb tree
    
   public MultiSet<T> blacken(){
        return this;
    }
  
//    public Bag balance(){
//        return new EmptyBag();
//    }
  
    public MultiSet<T> addInner(T elt, int i){
        return new FullBag(elt, i);
    }
    
    public boolean isRedHuh() {
        return isRed;
    }

    public MultiSet<T> balance() {
        return new EmptyBag();
    }
}
