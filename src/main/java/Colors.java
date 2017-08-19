package main.java;

import java.awt.Color;

enum Colors {
    WHITE(Color.WHITE),
    GREEN(Color.GREEN),
    RED(Color.RED),
    BLUE(Color.BLUE),
    YELLOW(Color.YELLOW),
    PINK(Color.PINK),
    ORANGE(Color.ORANGE),
    CYAN(Color.CYAN);

    Color c;

    Colors(Color c) {
        this.c = c;
    }

    // I am assuming here Color is immutable
    Color getColor() {
        return c;
    }
}
