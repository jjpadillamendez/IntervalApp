package functions;

import Jama.Matrix;
import math.IMath;
import math.IMatrix;
import math.IVector;
import math.Interval;

public class Test1 extends Function {
	public IVector f(IVector x0) {
		IVector b = new IVector(2);
		double x = x0.get(0).midpoint(), y = x0.get(1).midpoint(); 
		double t1;
		t1 = Math.pow(x,2)+Math.pow(y,2)-1;
		b.set(0, new Interval(t1, t1));

		t1 = x - Math.pow(y,2);
		b.set(1, new Interval(t1, t1));

		return b;

	}
	public IMatrix J(IVector x0) {
		IMatrix A = new IMatrix(2,2);
		Interval x = x0.get(0), y = x0.get(1);
		Interval one = new Interval(1,1);

		A.set(0, 0, IMath.mult(2, x));
		A.set(1, 0, one);
		A.set(0, 1, IMath.mult(2, y));
		A.set(1, 1, IMath.mult(-2, y));

		return A;

	}
	@Override
	public IVector f(double[] x0) {
		IVector b = new IVector(2);
		double x = x0[0], y = x0[1]; 
		double t1;
		t1 = Math.pow(x,2)+Math.pow(y,2)-1;
		b.set(0, new Interval(t1, t1));

		t1 = x - Math.pow(y,2);
		b.set(1, new Interval(t1, t1));

		return b;
	}
	@Override
	public Matrix J(double[] x) {
		Matrix A = new Matrix(2,2);
	
		A.set(0, 0, 2*x[0]);
		A.set(1, 0, 1);
		A.set(0, 1, 2*x[1]);
		A.set(1, 1, -2*x[1]);

		return A;
	}
}
