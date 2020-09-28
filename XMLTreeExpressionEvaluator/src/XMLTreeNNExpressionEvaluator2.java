import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumber2;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.utilities.Reporter;
import components.xmltree.XMLTree;
import components.xmltree.XMLTree1;

/**
 * Program to evaluate XMLTree expressions of {@code int}.
 *
 * @author Put your name here
 *
 */
public final class XMLTreeNNExpressionEvaluator2 {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private XMLTreeNNExpressionEvaluator2() {
    }

    /**
     * Evaluate the given expression.
     *
     * @param exp
     *            the {@code XMLTree} representing the expression
     * @return the value of the expression
     * @requires <pre>
     * [exp is a subtree of a well-formed XML arithmetic expression]  and
     *  [the label of the root of exp is not "expression"]
     * </pre>
     * @ensures evaluate = [the value of the expression]
     */
    private static NaturalNumber evaluate(XMLTree exp) {
        assert exp != null : "Violation of: exp is not null";
        NaturalNumber result = new NaturalNumber2(0);
        //creates two temporary values that are used to complete operations
        NaturalNumber temp1 = new NaturalNumber2(0);
        NaturalNumber temp2 = new NaturalNumber2(0);

        if (exp.label().equals("number")) {
            NaturalNumber input = new NaturalNumber2(
                    Integer.parseInt(exp.attributeValue("value")));
            result.copyFrom(input);
        } else {

            temp1.copyFrom(evaluate(exp.child(0)));
            temp2.copyFrom(evaluate(exp.child(1)));
            //goes through each possible operation
            if (exp.label().equals("plus")) {
                temp1.add(temp2);
                result.copyFrom(temp1);
            }
            if (exp.label().equals("minus")) {
                //checks that the subtraction does not end in a negative number
                if (temp1.compareTo(temp2) < 0) {
                    Reporter.fatalErrorToConsole(
                            "Unable to subtract two natural numbers that result "
                                    + "in a neagtive number.");
                } else {
                    temp1.subtract(temp2);
                    result.copyFrom(temp1);
                }
            }
            if (exp.label().equals("times")) {
                temp1.multiply(temp2);
                result.copyFrom(temp1);
            }
            if (exp.label().equals("divide")) {

                //checks to make sure there is no division by zero
                if (evaluate(exp.child(1)).isZero()) {
                    Reporter.fatalErrorToConsole("Unable to divide by zero.");
                } else {
                    temp1.divide(temp2);
                    result.copyFrom(temp1);
                }
            }
        }

        return result;
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

        out.print("Enter the name of an expression XML file: ");
        String file = in.nextLine();
        while (!file.equals("")) {
            XMLTree exp = new XMLTree1(file);
            out.println(evaluate(exp.child(0)));
            out.print("Enter the name of an expression XML file: ");
            file = in.nextLine();
        }

        in.close();
        out.close();
    }

}
