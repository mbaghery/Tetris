package main.java;

/* copied and pasted from the internet */
public class MatrixStuff {
    public static void transpose(boolean[][] m) {

        for (int i = 0; i < m.length; i++) {
            for (int j = i; j < m[0].length; j++) {
                boolean x = m[i][j];
                m[i][j] = m[j][i];
                m[j][i] = x;
            }
        }
    }

    public static void swapRows(boolean[][] m) {
        for (int i = 0, k = m.length - 1; i < k; ++i, --k) {
            boolean[] x = m[i];
            m[i] = m[k];
            m[k] = x;
        }
    }

    public static void rotateCCW(boolean[][] m) {
        transpose(m);
        swapRows(m);
    }

    public static void rotateCW(boolean[][] m) {
        swapRows(m);
        transpose(m);
    }
}
