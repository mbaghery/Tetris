package main.java;

import java.awt.Color;

// I am trusting Color to be immutable, in which case this class is immutable.
enum Colour {
    WHITE(Color.WHITE),
    GREEN(Color.GREEN),
    RED(Color.RED),
    BLUE(Color.BLUE),
    YELLOW(Color.YELLOW),
    PINK(Color.PINK),
    ORANGE(Color.ORANGE),
    CYAN(Color.CYAN);

    Color c;

    Colour(Color c) {
        this.c = c;
    }

    public Color getColor() {
        return c;
    }
}
