import java.util.Arrays;
public class Main {
    static int pass = 0;
    static int fail = 0;
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");
        

        Complex c1 = new Complex(3, 3);

        Complex exp = c1.exp(5);
        if(exp.real == -972 && exp.imag == -972) pass++;
        else fail++;


        if(Complex.rootOfUnity(4,8).real == -1) pass++;
        else fail++;

        testMult();
        testSynthDiv();
        testEvaluate();
        testEvaluateEven();

        p("Passes: " + pass + " Fails: " + fail);

    }

    public static void testMult() {
        // Test with two polynomials of the same degree
        Complex[] coeffs1 = {new Complex(1, 0), new Complex(2, 0), new Complex(3, 0)};
        Polynomial p1 = new Polynomial(coeffs1);
    
        Complex[] coeffs2 = {new Complex(4, 0), new Complex(5, 0), new Complex(6, 0)};
        Polynomial p2 = new Polynomial(coeffs2);
    
        Complex[] expectedCoeffs = {new Complex(4, 0), new Complex(13, 0), new Complex(28, 0), new Complex(27, 0), new Complex(18, 0)};
        Polynomial expected = new Polynomial(expectedCoeffs);
    
        Polynomial result = p1.mult(p2);
        if(expected.equals(result)) pass++;
        else {
            fail++;
            p("Expected: " + expected);
            p("Result: " + result);
        }
        // Test with two polynomials of different degree
        coeffs1 = new Complex[] {new Complex(1, 0), new Complex(2, 0)};
        p1 = new Polynomial(coeffs1);
    
        coeffs2 = new Complex[] {new Complex(3, 0), new Complex(4, 0), new Complex(5, 0)};
        p2 = new Polynomial(coeffs2);
    
        expectedCoeffs = new Complex[] {new Complex(3, 0), new Complex(10, 0), new Complex(13, 0), new Complex(10, 0)};
        expected = new Polynomial(expectedCoeffs);
    
        result = p1.mult(p2);
        if(expected.equals(result)) pass++;
        else {
            fail++;
            p("Expected: " + expected);
            p("Result: " + result);
        }

        // create two Polynomial objects with nonzero imaginary coefficients
        p1 = new Polynomial(new Complex[] {new Complex(2, 3), new Complex(0, 0), new Complex(4, 5)});
        p2 = new Polynomial(new Complex[] {new Complex(0, 0), new Complex(3, 1), new Complex(2, 1)});
        // multiply the two Polynomial objects
        result = p1.mult(p2);

        // expected result: (0+0i, 3+11i, 1+8i, 7+19i, 3+14i)
        expectedCoeffs = new Complex[] {new Complex(0,0), new Complex(3,11), new Complex(1,8), new Complex(7,19), new Complex(3,14)};
        expected = new Polynomial(expectedCoeffs);

        // assert that the result matches the expected result
        if(expected.equals(result)) pass++;
        else {
            fail++;
            p("Expected: " + expected);
            p("Result: " + result);
        }

    }

    public static void testSynthDiv() {
        double[] num1 = {1, 2, 3, 4};
        double[] den1 = {1, 0, 1};
        double[] expected1 = {1, 1, 0, 3};
        double[] result1 = Polynomial.synth_div(num1, den1);
        if(Arrays.equals(expected1, result1)) pass++;
        else {
            fail++;
            p("Failed synth_div test 1");
            p("Expected: " + Arrays.toString(expected1));
            p("Result: " + Arrays.toString(result1) + "\n\n");
        }
    
        double[] num2 = {5, 10, 15, 20, 25};
        double[] den2 = {2, 1, 0.5};
        double[] expected2 = {2.5, 5, 7.5, 10, 12.5};
        double[] result2 = Polynomial.synth_div(num2, den2);
        if(Arrays.equals(expected2, result2)) pass++;
        else {
            fail++;
            p("Failed synth_div test 2");
            p("Expected: " + Arrays.toString(expected2));
            p("Result: " + Arrays.toString(result2) + "\n\n");
        }
    }

    public static void testEvaluate() {
        // Define the coefficients of the polynomial
        Complex[] coeffs = {new Complex(1, 0), new Complex(2, 0), new Complex(3, 0)};
    
        // Create a new instance of the polynomial
        Polynomial poly = new Polynomial(coeffs);
    
        // Define the value to evaluate the polynomial at
        Complex x = new Complex(1, 1);
    
        // Evaluate the polynomial and store the result
        Complex result = poly.evaluate(x);
    
        // Check if the result is as expected
        if(result.real == 3 && result.imag == 8) pass++;
        else {
            fail++;
            p("Expected: " + new Complex(3, 8));
            p("Result: " + result);
        }
    }

    public static void testEvaluateEven() {
        // Define the coefficients of the polynomial
        Complex[] coeffs = {new Complex(10, 0), new Complex(1, 0), new Complex(2, 0), new Complex(4, 0), new Complex(3, 0), new Complex(1, 0)};
    
        // Create a new instance of the polynomial
        Polynomial poly = new Polynomial(coeffs);
    
        // Evaluate the polynomial evens and store the result
        Complex result = poly.evaluateEven(new Complex(3, 0));
    
        // Check if the result is as expected
        if(result.real == 43 && result.imag == 0) pass++;
        else {
            fail++;
            p("Expected: " + new Complex(43, 0));
            p("Result: " + result);
        }
    }

    public static void testEvaluateOdd() {
        // Define the coefficients of the polynomial
        Complex[] coeffs = {new Complex(10, 0), new Complex(1, 0), new Complex(2, 0), new Complex(4, 0), new Complex(3, 0), new Complex(1, 0)};
    
        // Create a new instance of the polynomial
        Polynomial poly = new Polynomial(coeffs);
    
        // Evaluate the polynomial odds and store the result
        Complex result = poly.evaluateOdd(new Complex(3, 0));
    
        // Check if the result is as expected
        if(result.real == 10 && result.imag == 0) pass++;
        else {
            fail++;
            p("Failed evaluateOdd test 1");
            p("Expected: " + new Complex(10, 0));
            p("Result: " + result);
        }
    }
    
    
    

    public static void p(Object o) {
        System.out.println(o);
    }
}
