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
public final class Newton4 {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private Newton4() {
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
    private static double sqrt(double x, double m) {

        double e = m;
        double r = x;
        /**
         * checks if the value original value is 0 and returns 0 if it is
         */
        if (x == 0) {
            return r;
        }
        /**
         * Compares guess to the margin of error and if it is larger than the
         * margin of error squared and if it is larger than it is changed until
         * it is smaller
         */
        while (Math.abs(r * r - x) / x > e * e) {
            r = (r + x / r) / 2;
        }
        return r;
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

        int choice = 0;
        double result = 0.0;
        double margin = 0.0;
        /**
         * asks user for epsilon value and value for the calculation of the
         * square root
         */
        out.println("Enter what epsilon is equal to: ");
        margin = in.nextDouble();
        out.println(
                "Enter the number you would like to compute the square root to: ");
        choice = in.nextInteger();

        /**
         * solves the square root while the user's input is not negative and
         * continues to ask for new values
         */
        while (choice >= 0) {
            result = sqrt(choice, margin);
            out.println("The square root of " + choice + " is " + result + ".");
            out.println(
                    "Enter the number you would like to compute the square root to: ");
            choice = in.nextInteger();
        }

        in.close();
        out.close();
    }

}
