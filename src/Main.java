public class Main {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");
        int pass = 0;
        int fail = 0;

        Complex c1 = new Complex(3, 3);
        
        Complex exp = c1.exp(5);
        if(exp.real == -972 && exp.imag == -972) pass++;
        else fail++;

        if(Complex.rootOfUnity(4,8).real == -1) pass++;
        else fail++;

        Complex[] coeffs = new Complex[4];
        for(int i = 1; i <= 4; i++) {
            Complex c = new Complex(i, i+4); 
        }
        
        

        p("Passes: " + pass + " Fails: " + fail);

    }

    public static void p(Object o) {
        System.out.println(o);
    }
}
