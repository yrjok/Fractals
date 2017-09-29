package ComplexMath;

/**
 * Class for making calculations with complex numbers.
 * 
 * @author Yrjo Koyen
 *
 */
public class Complex {
	
	public static boolean ANGULAR = true;
	public static boolean NORMAL = false;
	/**
	 * Creates a new complex number of the form: a+bj.
	 * @param a
	 * @param b
	 */
	public Complex(double a, double b) {
		this.a = a;
		this.b = b;
		this.calculatePolar();
	}
	
	public Complex(double a, double b, boolean type) {
		if (type == ANGULAR) {
			this.r = a;
			this.t = b;
			calculateNormal();
		} else {
			this.a = a;
			this.b = b;
			calculatePolar();
		}
	}
	
	
	public double getRadius() {
		return r;
	}
	
	private double r;

	public double getAngle() {
		return t;
	}

	private double t;
	
	public double getReal() {
		return this.a;
	}
	
	private double a;
	
	public double getImaginary() {
		return this.b;
	}
	
	private double b;
	
	private void calculatePolar() {
		this.r = Math.sqrt(a*a + b*b);
		if ( a > 0 )
			t = Math.atan(b/a);
		else if ( a < 0 )
			t = Math.PI + Math.atan(b/a);
		else
			t = ( b > 0 ? Math.PI/2 : -Math.PI/2 );
	}
	
	private void calculateNormal() {
		this.a = Math.cos(t)*r;
		this.b = Math.sin(t)*r;
	}
	
	public boolean equals(Complex other) {
		return this.equals(other, 0.0001);
	}
	
	public boolean equals(Complex other, double eps) {
		return ( Math.abs(this.getReal() - other.getReal()) <=  eps && 
				Math.abs(this.getImaginary() - other.getImaginary()) <= eps);
	}
	
	public double norm() {
		return Math.sqrt(Math.pow((getReal() + getImaginary()), 2));
	}
	
	public Complex add(Complex other) {
		return new Complex(this.getReal() + other.getReal(),
							this.getImaginary() + other.getImaginary());
	}
	
	public Complex sub(Complex other) {
		return new Complex(this.getReal() - other.getReal(),
				this.getImaginary() - other.getImaginary());
	}
	
	public Complex multiply(Complex factor) {
		return new Complex(this.getReal()*factor.getReal() - this.getImaginary()*factor.getImaginary(),
							this.getImaginary()*factor.getReal() + this.getReal()*factor.getImaginary());
	}
	
	public Complex divideBy(Complex factor) {
		return new Complex(this.getRadius()/factor.getRadius(), this.getAngle() - factor.getAngle(), Complex.ANGULAR);
	}
	
	public Complex pow(int exp) {
		return new Complex(Math.pow(getRadius(), exp), exp*getAngle()%(2*Math.PI), Complex.ANGULAR);
	}
	
	public String toString() {
		return "[" + getReal() + " + " + getImaginary() + "j]";
	}
	
	
	

}
