package source;
import java.util.LinkedList;
import java.util.Stack;

import source.Jama.Matrix;
import source.constructors.Constructor;
import source.constructors.Krawcxyk;
import source.functions.Function;
import source.functions.Test;
import source.math.Interval;
import source.math.IVector;
import source.math.IMath;
import source.math.IMatrix;


public class Brain {

	private static Constructor constructor = new Krawcxyk();
	private static Stack<IVector> solution = new Stack<IVector>();
	private static Function function = new Test();
	private static double runtime;
	private static int iterations;

	public static double getRuntime(){
		return runtime;
	}
	public static int getIterations(){
		return iterations;
	}
    public static Stack<IVector> getSolution(){ return solution; }
    public static Function getFunction(){ return function; }

	public static void setFunction(Function f){ function = f; }

	public static void runSolver(IVector xd){
		runtime = 0;
		iterations = 0;

		double start = System.currentTimeMillis();
		solve(xd);
		double end = System.currentTimeMillis();
		runtime = (end - start);
	}

	public static Stack<IVector> solve(IVector xd) {
		Stack<IVector> xstack = new Stack<IVector>();
		solution = new Stack<IVector>();
		IVector x0, x1, K;
		double epsilon = 1e-7;

		xstack.push(xd);
		while(!xstack.isEmpty()) {
			iterations++;
			LinkedList<IVector> listx1;
			x0 = xstack.pop();

			K = constructor.solve(function, x0);
			if(K != null) {
				x1 = IMath.intersect(K, x0);
				if(x1 != null) {
					if(IMath.maxWidth(x1) < epsilon) {
						solution.push(x1);
					}else {
						if(!IMath.areEqual(x0, x1)) {
							xstack.push(x1);
						}else {
							listx1 = IMath.bisect(x0);
							for(IVector xn : listx1)
								xstack.push(xn);
						}
					}
				}
			}else {
				listx1 = IMath.bisect(x0);
				for(IVector xn : listx1)
					xstack.push(xn);
			}
		}
		return solution;
	}

}