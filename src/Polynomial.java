public class Polynomial {
    private Complex[] coeffs;

    public Polynomial(Complex[] _coeffs) {
        int size = _coeffs.length;
        for(int i = 0; i < size; i++) {
            coeffs[i] = _coeffs[i];
        }
    }

    // @ return result, remainder
    public double[] synth_div(double[] num, double[] den) {
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
            out.add(coeffs[i].mult(x.exp(i)));
        }

        return out;
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

    public Complex[] FourierTransform() {
        return this.evaluateAll(Complex.rootsOfUnity(coeffs.length));
    }
}
