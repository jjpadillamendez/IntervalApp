package source.math;

import source.Jama.Matrix;

public class LinearKrawcxyk {

	public static IVector solve(IMatrix A, IVector b, IVector xd) {
		Matrix y;
		IMatrix yA, I, E; 
		IVector x1;
		
		try {
			y = IMath.randMatrix(A).inverse();
			yA = IMath.mult(y, A);
			I = IMath.identity(A.rowLen());
			E = IMath.sub(I, yA);
			
			IVector yb, EX;
			yb = IMath.mult(y,b);
			EX = IMath.mult(E, xd);
			x1 = IMath.intersect(IMath.add(yb, EX), xd);
		}
		catch(RuntimeException e) {
			x1 = null;
		}
	
		return x1;
	
	}

}
