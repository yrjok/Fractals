package ComplexMath;

public class NewtonRaphson {
	
	public static Converging findZero(Complex startingPoint, int maxIterations) {
		Complex iterationPoint = startingPoint;
		Complex nextIterationPoint = iterationPoint;
		boolean converged = false;
		int iterations = 0;
		
		while ( (iterations < maxIterations) && (! converged) ) {
			nextIterationPoint = iterationPoint.sub(Function.eval(iterationPoint).divideBy(
					Function.derivative(iterationPoint)));
			// Prints for debugging...
			//System.out.println(iterationPoint.toString() + nextIterationPoint.toString());
			if ( NewtonRaphson.getDistance(iterationPoint, nextIterationPoint) < 10e-8 ) {
				converged = true;
			}
			iterations++;
			iterationPoint = nextIterationPoint;
		}
			
		return new Converging(iterations, nextIterationPoint, startingPoint);
	}
	
	public static double getDistance(Complex c1, Complex c2) {
		return Math.sqrt( Math.pow((c1.getReal() - c2.getReal()), 2) +
						Math.pow((c1.getImaginary() - c2.getImaginary()), 2));
	}
	
	
}
