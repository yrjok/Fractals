import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import ComplexMath.Complex;
import ComplexMath.Function;

public class ComplexTests {
	
	private Complex one = new Complex(1, 0);
	private Complex twoj = new Complex(0, 2);
	private Complex fiveAndTwoj = new Complex(5, 2);
	
	@Before
	public void setup() {
		
	}
	
	@Test
	public void testMultiply() {
		assertTrue(one.multiply(twoj).equals(twoj));
		assertTrue(fiveAndTwoj.multiply(twoj).equals(new Complex(-4, 10)));
	}
	
	@Test
	public void testPow() {
		assertTrue(twoj.pow(2).equals(new Complex(-4, 0)));
	}
	
	@Test
	public void testFunction()  {
		assertTrue(Function.eval(new Complex(1, 0)).equals(new Complex(0, 0)));
		assertTrue(Function.eval(new Complex(0, 1)).equals(new Complex(-1, 1)));
	}

}
