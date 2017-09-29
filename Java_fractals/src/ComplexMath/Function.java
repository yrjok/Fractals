package ComplexMath;

public class Function {
	
	private static Complex one = new Complex(1, 0);
	private static int exp = 4;
	
	public static Complex eval(Complex z) {
		return (z.pow(exp).sub(one));
	}
	
	public static Complex derivative(Complex z) {
		return z.pow(exp-1).multiply(new Complex(exp, 0));
	}

}
