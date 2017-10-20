package source.functions;

import source.Jama.Matrix;
import source.math.IMath;
import source.math.IMatrix;
import source.math.IVector;
import source.math.Interval;

public class Test extends Function {

	public IVector f(IVector x0) {
		IVector b = new IVector(x0.length());
		double x1 = x0.get(0).midpoint(), x4 = x0.get(3).midpoint(); 
		double x2 = x0.get(1).midpoint();
		double x3 = x0.get(2).midpoint();
		double t1;

		t1 = (Math.pow(x1,2) + x1 - 2)*(Math.pow(x2, 2) + 1);
		b.set(0, new Interval(t1, t1));

		t1 = (Math.pow(x2,2) + 5*x2 + 6)*(Math.pow(x1, 2) + 1);
		b.set(1, new Interval(t1, t1));

		t1 = (Math.pow(x3,2) + 2*x3 - 3)*(Math.pow(x4, 2) + 1);
		b.set(2, new Interval(t1, t1));

		t1 = (Math.pow(x4,2) - 4)*(Math.pow(x3, 2) + 1);
		b.set(3, new Interval(t1, t1));


		return b;

	}
	public IMatrix J(IVector x0) {
		IMatrix A = new IMatrix(x0.length(), x0.length());
		Interval x1 = x0.get(0), x4 = x0.get(3);
		Interval x2 = x0.get(1);
		Interval x3 = x0.get(2);

		A.set(0, 0, IMath.mult(IMath.add(IMath.mult(2, x1), new Interval(1,1)),IMath.add(IMath.pow(x1, 2), new Interval(1,1))));
		A.set(1, 0, IMath.sub(IMath.pow(IMath.sub(x2, new Interval(5/2,5/2)), 2), new Interval(1/4,1/4)));
		A.set(2, 0, new Interval(0,0));
		A.set(3, 0, new Interval(0,0));

		A.set(0, 1, IMath.mult(IMath.mult(2, x2),IMath.sub(IMath.pow(IMath.sub(x1, new Interval(1/4,1/4)), 2), new Interval(5/4,5/4))));
		A.set(1, 1, IMath.mult(IMath.add(IMath.pow(x1, 2), new Interval(1,1)), IMath.sub(IMath.mult(2, x2), IMath.mult(5, x2))));
		A.set(2, 1, new Interval(0,0));
		A.set(3, 1, new Interval(0,0));

		A.set(0, 2, new Interval(0,0));
		A.set(1, 2, new Interval(0,0));
		A.set(2, 2, IMath.mult(IMath.sub(IMath.mult(2, x3), new Interval(2,2)), IMath.add(IMath.pow(x4, 2), new Interval(1,1))));
		A.set(3, 2, IMath.mult(IMath.mult(2, x3), IMath.sub(IMath.pow(x4, 2), new Interval(4,4))));

		A.set(0, 3, new Interval(0,0));
		A.set(1, 3, new Interval(0,0));
		A.set(2, 3, IMath.mult(IMath.sub(IMath.pow(IMath.sub(x3, new Interval(1,1)), 2), new Interval(4,4)), IMath.add(IMath.mult(2, x4), new Interval(1,1))));
		A.set(3, 3, IMath.mult(IMath.mult(2, x4), IMath.add(IMath.pow(x3, 2), new Interval(1,1))));

		return A;

	}
	@Override
	public IVector f(double[] x0) {
		IVector b = new IVector(x0.length);
		double x1 = x0[0], x4 = x0[3]; 
		double x2 = x0[1];
		double x3 = x0[2];
		double t1;

		t1 = (Math.pow(x1,2) + x1 - 2)*(Math.pow(x2, 2) + 1);
		b.set(0, new Interval(t1, t1));

		t1 = (Math.pow(x2,2) + 5*x2 + 6)*(Math.pow(x1, 2) + 1);
		b.set(1, new Interval(t1, t1));

		t1 = (Math.pow(x3,2) + 2*x3 - 3)*(Math.pow(x4, 2) + 1);
		b.set(2, new Interval(t1, t1));

		t1 = (Math.pow(x4,2) - 4)*(Math.pow(x3, 2) + 1);
		b.set(3, new Interval(t1, t1));


		return b;
	}
	@Override
	public Matrix J(double[] x) {
		Matrix A = new Matrix(x.length, x.length);

		A.set(0, 0, (2*x[0]+1)*(Math.pow(x[1],2)+1));
		A.set(1, 0, (Math.pow(x[0],2)+x[0]-2)*(2*x[1]));
		A.set(2, 0, 0);
		A.set(3, 0, 0);

		A.set(0, 1, (Math.pow(x[1], 2)+5*x[1]+6)*(2*x[0]));
		A.set(1, 1, (2*x[1]+5)*(Math.pow(x[0],2)+1));
		A.set(2, 1, 0);
		A.set(3, 1, 0);

		A.set(0, 2, 0);
		A.set(1, 2, 0);
		A.set(2, 2, (2*x[2]+2)*(Math.pow(x[3],2)+1));
		A.set(3, 2,  (Math.pow(x[2],2)+2*x[2]-3)*(Math.pow(x[3],2)+1));

		A.set(0, 3, 0);
		A.set(1, 3, 0);
		A.set(2, 3, (2*x[2])*(Math.pow(x[3],2)-4));
		A.set(3, 3, (2*x[3])*(Math.pow(x[2],2)+1));

		return A;
	}

	@Override
	public String toString() {
		return  "\t(x1^2 + x1 - 2)(x2^2 + 1) = 0\n\t(x2^2 + 5*x2 + 6)(x1^2 + 1) = 0\n" +
				"\t(x3^2 + 2*x3 - 3)(x4^2 + 1) = 0\n\t(x4^2 - 4)(x3^2 + 1) = 0\n\n";
	}
	public String getVarAsString(int i){
		if(i > 3 || i < 0){
			return "";
		}else{
			return "x"+(i+1);
		}
	}
}
