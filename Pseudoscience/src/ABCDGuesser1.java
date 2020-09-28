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
public final class ABCDGuesser1 {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private ABCDGuesser1() {
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
        /*
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

        int countW = 0, countX = 0, countY = 0, countZ = 0;

        final double[] exp = { -5, -4, -3, -2, -1, -1 / 2d, -1 / 3d, -1 / 4d, 0,
                1 / 4d, 1 / 3d, 1 / 2d, 1, 2, 3, 4, 5 };
        int[] abcd = { 0, 0, 0, 0 };

        double formulaOut = Math.pow(w, exp[0]) * Math.pow(x, exp[0])
                * Math.pow(y, exp[0]) * Math.pow(z, exp[0]);

        /**
         * loops through each possible exponent for each value entered to get
         * the closest to the inputed constant as possible
         */
        while (countW < exp.length) {
            while (countX < exp.length) {
                while (countY < exp.length) {
                    while (countZ < exp.length) {
                        if (Math.abs(Math.pow(w, exp[countW])
                                * Math.pow(x, exp[countX])
                                * Math.pow(y, exp[countY])
                                * Math.pow(z, exp[countZ]) - u) < Math
                                        .abs(formulaOut - u)) {

                            abcd[0] = countW;
                            abcd[1] = countX;
                            abcd[2] = countY;
                            abcd[th] = countZ;

                            formulaOut = Math.pow(w, exp[countW])
                                    * Math.pow(x, exp[countX])
                                    * Math.pow(y, exp[countY])
                                    * Math.pow(z, exp[countZ]);
                        }
                        countZ++;
                    }
                    countY++;
                    countZ = 0;
                }
                countX++;
                countY = 0;
            }
            countW++;
            countX = 0;
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
