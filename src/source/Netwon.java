package source;
import java.util.LinkedList;
import java.util.Stack;

import com.example.jesus.myapplication.math.IMatrix;
import com.example.jesus.myapplication.math.Interval;
import com.example.jesus.myapplication.math.IVector;
import com.example.jesus.myapplication.math.GaussSeidel;
import com.example.jesus.myapplication.math.IMath;


public class Netwon {

	Stack<IVector> solution;
	Stack<IVector> getSolution(){
		return solution;
	}
	public Netwon(){
		run();
	}
	public static void run() {			
		IVector x = new IVector(2);
		x.set(0, new Interval(0.5,1));
		x.set(1, new Interval(0.5,1));
		Stack<IVector> solution = solve(x);
		
	}
	public static Stack<IVector> solve(IVector xd) {
		Stack<IVector> xstack = new Stack<IVector>();
		Stack<IVector> solution = new Stack<IVector>();
		IMatrix A;
		IVector Nx, b, V, x0, x1;
		double epsilon = 1e-12;

		xstack.push(xd);
		while(!xstack.isEmpty()) {
			x0 = xstack.pop();

			A = devf(x0);
			b = IMath.toNegative(f(x0));
			V = IMath.sub(x0, x0.midpoint());
			Nx = GaussSeidel.solve(A, b, V);

			if(Nx != null) {
				Nx = IMath.add(x0.midpoint(), Nx);
				x1 = IMath.intersect(Nx, x0);
			//	if(!IMath.areEqual(x0, x1)) {
					if(IMath.maxWidth(x1) < epsilon) {
						solution.push(x1);
					}
					else {
						xstack.push(x1);
					}
			//	}
//				else {
//					LinkedList<IVector> listx1 = IMath.bisect(x1);
//					for(IVector xn : listx1)
//						xstack.push(xn);
//				}
			//	System.out.println(x1);
			}
		
		}
		return solution;

	}
//	private static IVector f(IVector x0) {
//		IVector b = new IVector(x0.length());
//		double x1 = x0.get(0).midpoint(), x4 = x0.get(3).midpoint(); 
//		double x2 = x0.get(1).midpoint();
//		double x3 = x0.get(2).midpoint();
//		double t1;
//		
//		t1 = (Math.pow(x1,2) + x1 - 2)*(Math.pow(x2, 2) + 1);
//		b.set(0, new Interval(t1, t1));
//
//		t1 = (Math.pow(x2,2) + 5*x2 + 6)*(Math.pow(x1, 2) + 1);
//		b.set(1, new Interval(t1, t1));
//		
//		t1 = (Math.pow(x3,2) + 2*x3 - 3)*(Math.pow(x4, 2) + 1);
//		b.set(2, new Interval(t1, t1));
//
//		t1 = (Math.pow(x4,2) - 4)*(Math.pow(x3, 2) + 1);
//		b.set(3, new Interval(t1, t1));
//		
//
//		return b;
//
//	}
//	private static IMatrix devf(IVector x0) {
//		IMatrix A = new IMatrix(x0.length(), x0.length());
//		Interval x1 = x0.get(0), x4 = x0.get(3);
//		Interval x2 = x0.get(1);
//		Interval x3 = x0.get(2);
//		Interval one = new Interval(1,1);
//
//		A.set(0, 0, IMath.mult(IMath.add(IMath.mult(2, x1), new Interval(1,1)),IMath.add(IMath.pow(x1, 2), new Interval(1,1))));
//		A.set(1, 0, IMath.sub(IMath.pow(IMath.sub(x2, new Interval(5/2,5/2)), 2), new Interval(1/4,1/4)));
//		A.set(2, 0, new Interval(0,0));
//		A.set(3, 0, new Interval(0,0));
//		
//		A.set(0, 1, IMath.mult(IMath.mult(2, x2),IMath.sub(IMath.pow(IMath.sub(x1, new Interval(1/4,1/4)), 2), new Interval(5/4,5/4))));
//		A.set(1, 1, IMath.mult(IMath.add(IMath.pow(x1, 2), new Interval(1,1)), IMath.sub(IMath.mult(2, x2), IMath.mult(5, x2))));
//		A.set(2, 1, new Interval(0,0));
//		A.set(3, 1, new Interval(0,0));
//		
//		A.set(0, 2, new Interval(0,0));
//		A.set(1, 2, new Interval(0,0));
//		A.set(2, 2, IMath.mult(IMath.sub(IMath.mult(2, x3), new Interval(2,2)), IMath.add(IMath.pow(x4, 2), new Interval(1,1))));
//		A.set(3, 2, IMath.mult(IMath.mult(2, x3), IMath.sub(IMath.pow(x4, 2), new Interval(4,4))));
//		
//		A.set(0, 3, new Interval(0,0));
//		A.set(1, 3, new Interval(0,0));
//		A.set(2, 3, IMath.mult(IMath.sub(IMath.pow(IMath.sub(x3, new Interval(1,1)), 2), new Interval(4,4)), IMath.add(IMath.mult(2, x4), new Interval(1,1))));
//		A.set(3, 3, IMath.mult(IMath.mult(2, x4), IMath.add(IMath.pow(x3, 2), new Interval(1,1))));
//
//		return A;
//
//	}
	private static IVector f(IVector x0) {
		IVector b = new IVector(2);
		double x = x0.get(0).midpoint(), y = x0.get(1).midpoint(); 
		double t1;
		t1 = Math.pow(x,2)+Math.pow(y,2)-1;
		b.set(0, new Interval(t1, t1));

		t1 = x - Math.pow(y,2);
		b.set(1, new Interval(t1, t1));

		return b;

	}
	private static IMatrix devf(IVector x0) {
		IMatrix A = new IMatrix(2,2);
		Interval t1,t2;
		Interval x = x0.get(0), y = x0.get(1);
		Interval one = new Interval(1,1);

		A.set(0, 0, IMath.mult(2, x));
		A.set(1, 0, one);
		A.set(0, 1, IMath.mult(2, y));
		A.set(1, 1, IMath.mult(-2, y));

		return A;

	}

}
//IVector b = new IVector(3);
//b.set(0, new Interval(3,4));
//b.set(1, new Interval(0,2));
//b.set(2, new Interval(3,4));
//
//IVector p = new IVector(3);
//p.set(0, new Interval(-10,10));
//p.set(1, new Interval(-10,10));
//p.set(2, new Interval(-10,10));
//
//IMatrix A = new IMatrix(3,3);
//A.set(0,0, new Interval(4,5));
//A.set(1,0, new Interval(-0.5,0.5));
//A.set(2,0, new Interval(-1.5,-0.5));
//A.set(0,1, new Interval(-1,1));
//A.set(1,1, new Interval(-7,-5));
//A.set(2,1, new Interval(-0.7,-0.5));
//A.set(0,2, new Interval(1.5,2.5));
//A.set(1,2, new Interval(1,2));
//A.set(2,2, new Interval(2,3));
//
//GaussSeidel.solve(A, b, p);
//