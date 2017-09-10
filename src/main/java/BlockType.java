package main.java;

public enum BlockType {
    O(0), L(1), J(2), I(3), A(4), S(5), Z(6);

    private int value;

    BlockType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
