import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.set.Set;
import components.set.Set1L;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * @author Jonathan Chemaly
 *
 */
public class StringReasemblyTest {

    /*
     * Tests of combination
     */

    @Test
    public void testCombinationCar() {
        String str1 = "I drive a car";
        String str2 = "car which is faster than walking.";
        final int overlap = 3;
        String resultExpected = "I drive a car which is faster than walking.";
        String result = StringReassembly.combination(str1, str2, overlap);
        assertEquals(resultExpected, result);
    }

    @Test
    public void testCombinationHello() {
        String str1 = "Hello my name is";
        String str2 = "name is Jonathan.";
        final int overlap = 7;
        String resultExpected = "Hello my name is Jonathan.";
        String result = StringReassembly.combination(str1, str2, overlap);
        assertEquals(resultExpected, result);
    }

    /*
     * Tests of addToSetAvoidingSubstrings
     */

    @Test
    public void testAddToSetAvoidingSubstringsAdd() {
        Set<String> strSet = new Set1L<>();
        strSet.add("How");
        strSet.add(" are");
        String str = " you?";
        Set<String> resultExpected = new Set1L<>();
        resultExpected.add("How");
        resultExpected.add(" are");
        resultExpected.add(" you?");
        StringReassembly.addToSetAvoidingSubstrings(strSet, str);
        assertEquals(resultExpected, strSet);
    }

    @Test
    public void testAddToSetAvoidingSubstringsAddMid() {
        Set<String> strSet = new Set1L<>();
        strSet.add("I'm");
        strSet.add(" great!");
        String str = " doing";
        Set<String> resultExpected = new Set1L<>();
        resultExpected.add("I'm");
        resultExpected.add(" doing");
        resultExpected.add(" great!");
        StringReassembly.addToSetAvoidingSubstrings(strSet, str);
        assertEquals(resultExpected, strSet);
    }

    @Test
    public void testAddToSetAvoidingSubstringsNoChange() {
        Set<String> strSet = new Set1L<>();
        strSet.add("My favorite");
        strSet.add(" color is");
        strSet.add(" green.");
        String str = "lor is";
        Set<String> resultExpected = new Set1L<>();
        resultExpected.add("My favorite");
        resultExpected.add(" color is");
        resultExpected.add(" green.");
        StringReassembly.addToSetAvoidingSubstrings(strSet, str);
        assertEquals(resultExpected, strSet);
    }

    /*
     * Tests of linesFromInput
     */

    @Test
    public void testLinesFromInputPass() {
        SimpleReader input = new SimpleReader1L("data/testSimpleReader");
        Set<String> strSet = new Set1L<>();
        strSet.add("I am");
        strSet.add(" a scrambled");
        strSet.add(" sentence.");
        Set<String> resultExpected = new Set1L<>();
        resultExpected.add(" a scrambled");
        resultExpected.add("I am");
        resultExpected.add(" sentence.");
        StringReassembly.linesFromInput(input);
        assertEquals(resultExpected, strSet);

        input.close();
    }

    /*
     * Tests of printWithLineSeparators
     */

    @Test
    public void testPrintWithLineSeparators() {
        SimpleWriter output = new SimpleWriter1L("data/testSimpleReader");
        String text = "Dear person,~hope you have a great day!~Sincerely,~ Jonathan";
        StringReassembly.printWithLineSeparators(text, output);
        //Check the testSimpleReader file in the data folder to see if the
        //text has new lines where the "~" are
    }

}
