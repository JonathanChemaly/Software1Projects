import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumber2;

/**
 * @author Jonathan Chemaly
 *
 */
public class CryptoUtilitiesTest {

    /*
     * Tests of reduceToGCD
     */

    @Test
    public void testReduceToGCD_0_0() {
        NaturalNumber n = new NaturalNumber2(0);
        NaturalNumber nExpected = new NaturalNumber2(0);
        NaturalNumber m = new NaturalNumber2(0);
        NaturalNumber mExpected = new NaturalNumber2(0);
        CryptoUtilities.reduceToGCD(n, m);
        assertEquals(nExpected, n);
        assertEquals(mExpected, m);
    }

    @Test
    public void testReduceToGCD_30_21() {
        NaturalNumber n = new NaturalNumber2(30);
        NaturalNumber nExpected = new NaturalNumber2(3);
        NaturalNumber m = new NaturalNumber2(21);
        NaturalNumber mExpected = new NaturalNumber2(0);
        CryptoUtilities.reduceToGCD(n, m);
        assertEquals(nExpected, n);
        assertEquals(mExpected, m);
    }

    /*
     * Tests of isEven
     */

    @Test
    public void testIsEven_0() {
        NaturalNumber n = new NaturalNumber2(0);
        NaturalNumber nExpected = new NaturalNumber2(0);
        boolean result = CryptoUtilities.isEven(n);
        assertEquals(nExpected, n);
        assertEquals(true, result);
    }

    @Test
    public void testIsEven_1() {
        NaturalNumber n = new NaturalNumber2(1);
        NaturalNumber nExpected = new NaturalNumber2(1);
        boolean result = CryptoUtilities.isEven(n);
        assertEquals(nExpected, n);
        assertEquals(false, result);
    }

    /*
     * Tests of powerMod
     */

    @Test
    public void testPowerMod_0_0_2() {
        NaturalNumber n = new NaturalNumber2(0);
        NaturalNumber nExpected = new NaturalNumber2(1);
        NaturalNumber p = new NaturalNumber2(0);
        NaturalNumber pExpected = new NaturalNumber2(0);
        NaturalNumber m = new NaturalNumber2(2);
        NaturalNumber mExpected = new NaturalNumber2(2);
        CryptoUtilities.powerMod(n, p, m);
        assertEquals(nExpected, n);
        assertEquals(pExpected, p);
        assertEquals(mExpected, m);
    }

    @Test
    public void testPowerMod_17_18_19() {
        NaturalNumber n = new NaturalNumber2(17);
        NaturalNumber nExpected = new NaturalNumber2(1);
        NaturalNumber p = new NaturalNumber2(18);
        NaturalNumber pExpected = new NaturalNumber2(18);
        NaturalNumber m = new NaturalNumber2(19);
        NaturalNumber mExpected = new NaturalNumber2(19);
        CryptoUtilities.powerMod(n, p, m);
        assertEquals(nExpected, n);
        assertEquals(pExpected, p);
        assertEquals(mExpected, m);
    }

    @Test
    public void testPowerMod_8_2_5() {
        NaturalNumber n = new NaturalNumber2(5);
        NaturalNumber nExpected = new NaturalNumber2(1);
        NaturalNumber p = new NaturalNumber2(2);
        NaturalNumber pExpected = new NaturalNumber2(2);
        NaturalNumber m = new NaturalNumber2(8);
        NaturalNumber mExpected = new NaturalNumber2(8);
        CryptoUtilities.powerMod(n, p, m);
        assertEquals(nExpected, n);
        assertEquals(pExpected, p);
        assertEquals(mExpected, m);
    }

    /*
     * Tests of isWitnessToCompositeness
     */

    @Test
    public void testIsWitnessToCompositeness_8_5() {
        NaturalNumber n = new NaturalNumber2(8);
        NaturalNumber nExpected = new NaturalNumber2(8);
        NaturalNumber w = new NaturalNumber2(5);
        NaturalNumber wExpected = new NaturalNumber2(5);
        assertTrue(CryptoUtilities.isWitnessToCompositeness(w, n));
        assertEquals(nExpected, n);
        assertEquals(wExpected, w);
    }

    @Test
    public void testIsWitnessToCompositeness_5_2() {
        NaturalNumber n = new NaturalNumber2(5);
        NaturalNumber nExpected = new NaturalNumber2(5);
        NaturalNumber w = new NaturalNumber2(2);
        NaturalNumber wExpected = new NaturalNumber2(2);
        assertTrue(CryptoUtilities.isWitnessToCompositeness(w, n));
        assertEquals(nExpected, n);
        assertEquals(wExpected, w);
    }

    @Test
    public void testIsWitnessToCompositeness_7_3() {
        NaturalNumber n = new NaturalNumber2(7);
        NaturalNumber nExpected = new NaturalNumber2(7);
        NaturalNumber w = new NaturalNumber2(3);
        NaturalNumber wExpected = new NaturalNumber2(3);
        assertEquals(false, CryptoUtilities.isWitnessToCompositeness(w, n));
        assertEquals(nExpected, n);
        assertEquals(wExpected, w);
    }

    /*
     * Tests of isPrime2
     */

    @Test
    public void testIsPrime2_53() {
        NaturalNumber n = new NaturalNumber2(53);
        NaturalNumber nExpected = new NaturalNumber2(53);
        assertTrue(CryptoUtilities.isPrime2(n));
        assertEquals(nExpected, n);
    }

    @Test
    public void testIsPrime2_57() {
        NaturalNumber n = new NaturalNumber2(57);
        NaturalNumber nExpected = new NaturalNumber2(57);
        assertEquals(false, CryptoUtilities.isPrime2(n));
        assertEquals(nExpected, n);
    }

    /*
     * Tests of generateNextLikelyPrime
     */

    @Test
    public void testGenerateNextLikelyPrime_53() {
        NaturalNumber n = new NaturalNumber2(53);
        NaturalNumber nExpected = new NaturalNumber2(53);
        CryptoUtilities.generateNextLikelyPrime(n);
        assertEquals(nExpected, n);
    }

    @Test
    public void testGenerateNextLikelyPrime_57() {
        NaturalNumber n = new NaturalNumber2(57);
        NaturalNumber nExpected = new NaturalNumber2(59);
        CryptoUtilities.generateNextLikelyPrime(n);
        assertEquals(nExpected, n);
    }

    @Test
    public void testGenerateNextLikelyPrime_71() {
        NaturalNumber n = new NaturalNumber2(70);
        NaturalNumber nExpected = new NaturalNumber2(71);
        CryptoUtilities.generateNextLikelyPrime(n);
        assertEquals(nExpected, n);
    }

}