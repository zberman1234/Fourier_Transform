public class Polynomial {
    private Complex[] coeffs;

    public Polynomial(Complex[] _coeffs) {
        int size = _coeffs.length;
        coeffs = new Complex[size];
        for(int i = 0; i < size; i++) {
            coeffs[i] = _coeffs[i];
        }
    }

    // coeffs get method
    public Complex[] getCoeffs() {
        return coeffs;
    }

    // multiply this polynomial by another
    public Polynomial mult(Polynomial other) {
        int size = coeffs.length + other.coeffs.length - 1;
        Complex[] result = new Complex[size];
        for(int i = 0; i < size; i++) {
            result[i] = new Complex(0,0);
        }

        for(int i = 0; i < coeffs.length; i++) {
            for(int j = 0; j < other.coeffs.length; j++) {
                result[i+j] = result[i+j].add(coeffs[i].mult(other.coeffs[j]));
                // System.out.println("coeffs[i].mult(other.coeffs[j])=" + coeffs[i].mult(other.coeffs[j]) + " i=" + i + " j=" + j + " result[i+j]=" + result[i+j] + " coeffs[i]=" + coeffs[i] + " other.coeffs[j]=" + other.coeffs[j] + "");
            }
        }

        return new Polynomial(result);
    }

    public static double[] synth_div(double[] num, double[] den) {
        double[] retval = num.clone();
        double norm = den[0];
        for(int i = 0; i< num.length - den.length; i++) {
            retval[i] /= norm;
            double c = retval[i];
            if(c!=0) {
                for(int j =0; j < den.length; j++) {
                    retval[i+j] -= den[j]*c;
                }
            }
        }

        return retval;
    }

    public Complex evaluate(Complex x) {
        Complex out = new Complex(0,0);
        for(int i = 0; i < coeffs.length; i++) {
            out = out.add(coeffs[i].mult(x.exp(i)));
        }
        return out;
    }

    // evaluate if this polynomial is equal to another
    public boolean equals(Polynomial other) {
        if(coeffs.length != other.coeffs.length) {
            return false;
        }

        for(int i = 0; i < coeffs.length; i++) {
            if(coeffs[i].real != other.coeffs[i].real || coeffs[i].imag != other.coeffs[i].imag) {
                return false;
            }
        }

        return true;
    }

    public Complex[] evaluateAll(Complex[] v) {
        Complex[] out = new Complex[v.length];
        for(int i = 0; i < v.length; i++) {
            out[i] = evaluate(v[i]);
        }
        return out;
    }

    public Complex evaluateEven(Complex x) {
        int terms = coeffs.length/2;
        Complex[] EvenCoeffs = new Complex[terms];
        for(int i = 0; i < terms; i+=2) {
            EvenCoeffs[i/2] = coeffs[i];
        }
        Polynomial evens = new Polynomial(EvenCoeffs);
        return evens.evaluate(x);
    }

    public Complex evaluateOdd(Complex x) {
        int terms = coeffs.length;
        Complex[] OddCoeffs = new Complex[terms];
        for(int i = 1; i < terms; i+=2) {
            OddCoeffs[i/2] = coeffs[i];
        }
        Polynomial odds = new Polynomial(OddCoeffs);
        return odds.evaluate(x);
    }

    // public Complex[] FourierTransform() {
    //     return this.evaluateAll(Complex.rootsOfUnity(coeffs.length));
    // }

    public String toString() {
        String out = "";
        for(int i = 0; i < coeffs.length; i++) {
            out += "("+coeffs[i]+")" + "x^" + i + " + ";
        }
        return out;
    }
}
