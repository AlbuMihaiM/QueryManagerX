public class Pair<F, S> {

    private final F first;
    private final S second;

    public static <F, S> Pair<F, S> createPair(F element0, S element1) {
        return new Pair<F, S>(element0, element1);
    }
    
    public Pair() {
        this.first = null;
        this.second = null;
    }

    public Pair(F k, S v) {
        this.first = k;
        this.second = v;
    }

    public F getFirst() {
        return first;
    }

    public S getSecond() {
        return second;
    }

}