import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Find the square root of the user's inputed value.
 *
 * @author Jonathan Chemaly
 *
 */
public final class Test {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private Test() {
    }

    /**
     * Computes estimate of square root of x to within relative error 0.01%.
     *
     * @param x
     *            positive number to compute square root of
     * @param m
     *            double used as epsilon
     * @return estimate of square root
     */
    private static boolean sorted(int x, int y, int z) {

        return (x < y && y < z);
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments
     */
    public static void main(String[] args) {
        SimpleReader in = new SimpleReader1L();
        SimpleWriter out = new SimpleWriter1L();

        double[] exp = { -5, -4, -3, -2, -1, -0.5, -1 / 3d, -1 / 4, 0, 1 / 4,
                1 / 3, 1 / 2, 1, 2, 3, 4, 5 };
        int i = 0;
        while (i < exp.length - 1) {
            i++;
            out.println(i);
        }

        in.close();
        out.close();
    }

}
