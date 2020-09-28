import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.utilities.FormatChecker;

/**
 * Calculating the Jager formula.
 *
 * @author Jonathan Chemaly
 *
 */
public final class ABCDGuesser2 {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private ABCDGuesser2() {
    }

    /**
     * Repeatedly asks the user for a positive real number until the user enters
     * one. Returns the positive real number.
     *
     * @param in
     *            the input stream
     * @param out
     *            the output stream
     * @return a positive real number entered by the user
     */
    private static double getPositiveDouble(SimpleReader in, SimpleWriter out) {

        out.println("Enter a constant: ");
        String value = in.nextLine();
        /*
         * checks if the value is positive and can be a double
         */
        while (!FormatChecker.canParseDouble(value)
                || Double.parseDouble(value) < 0) {
            out.println("Enter a constant: ");
            value = in.nextLine();
        }

        return Double.parseDouble(value);
    }

    /**
     * Repeatedly asks the user for a positive real number not equal to 1.0
     * until the user enters one. Returns the positive real number.
     *
     * @param in
     *            the input stream
     * @param out
     *            the output stream
     * @return a positive real number not equal to 1.0 entered by the user
     */
    private static double getPositiveDoubleNotOne(SimpleReader in,
            SimpleWriter out) {

        out.println("Enter any positive real number not equal to 1: ");
        String value = in.nextLine();
        /**
         * checks if the value is positive, can be a double and not equal to 1
         */
        while (!FormatChecker.canParseDouble(value) || value.contentEquals("1")
                || Double.parseDouble(value) < 0) {
            out.println("Enter any positive real number not equal to 1: ");
            value = in.nextLine();
        }
        return Double.parseDouble(value);
    }

    /**
     * Calculates the Jager formula.
     *
     * @param w
     * @param x
     * @param y
     * @param z
     * @param exp
     * @param countW
     * @param countX
     * @param countY
     * @param countZ
     *
     * @return double as a result of Jager formula
     */
    private static double jager(double w, double x, double y, double z,
            final double[] exp, int countW, int countX, int countY,
            int countZ) {
        /**
         * It is showing an error for more than 7 parameters even though it is
         * allowed. Just wanted to acknowledge that is was not an error in the
         * code.
         */
        return Math.pow(w, exp[countW]) * Math.pow(x, exp[countX])
                * Math.pow(y, exp[countY]) * Math.pow(z, exp[countZ]);
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
        final int th = 3;
        double u = getPositiveDouble(in, out);
        double w = getPositiveDoubleNotOne(in, out);
        double x = getPositiveDoubleNotOne(in, out);
        double y = getPositiveDoubleNotOne(in, out);
        double z = getPositiveDoubleNotOne(in, out);

        final double[] exp = { -5, -4, -3, -2, -1, -1 / 2d, -1 / 3d, -1 / 4d, 0,
                1 / 4d, 1 / 3d, 1 / 2d, 1, 2, 3, 4, 5 };
        int[] abcd = { 0, 0, 0, 0 };

        double formulaOut = jager(w, x, y, z, exp, 0, 0, 0, 0);

        /**
         * loops through each possible exponent for each value entered to get
         * the closest to the inputed constant as possible
         */
        for (int countW = 0; countW < exp.length; countW++) {
            for (int countX = 0; countX < exp.length; countX++) {
                for (int countY = 0; countY < exp.length; countY++) {
                    for (int countZ = 0; countZ < exp.length; countZ++) {
                        if (Math.abs(jager(w, x, y, z, exp, countW, countX,
                                countY, countZ) - u) < Math
                                        .abs(formulaOut - u)) {

                            abcd[0] = countW;
                            abcd[1] = countX;
                            abcd[2] = countY;
                            abcd[th] = countZ;

                            formulaOut = jager(w, x, y, z, exp, countW, countX,
                                    countY, countZ);
                        }
                    }
                }
            }
        }
        final double error = Math.abs((u - formulaOut) / u * 100);

        out.println("The exponent for " + w + " is " + exp[abcd[0]] + ".");
        out.println("The exponent for " + x + " is " + exp[abcd[1]] + ".");
        out.println("The exponent for " + y + " is " + exp[abcd[2]] + ".");
        out.println("The exponent for " + z + " is " + exp[abcd[th]] + ".");
        out.print("The best approximation is ");
        out.print(formulaOut, th, false);
        out.println(".");
        out.print("The relative error to the constant is ");
        out.print(error, 2, false);
        out.println("%.");
    }

}
