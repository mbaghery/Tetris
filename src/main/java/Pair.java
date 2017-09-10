package main.java;

public class Pair<T, U> {
    public final T a;
    public final U b;

    public Pair(T a, U b) {
        this.a = a;
        this.b = b;
    }

    public T _1() {
        return a;
    }

    public U _2() {
        return b;
    }
}