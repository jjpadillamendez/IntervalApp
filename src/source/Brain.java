package source;
import java.util.LinkedList;
import java.util.Stack;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

import Jama.Matrix;
import constructors.Constructor;
import constructors.Krawcxyk;
import constructors.Lyapunor;
import constructors.Newton;
import functions.Function;
import functions.Test;
import math.Interval;
import math.IVector;
import math.IMath;
import math.IMatrix;


public class Brain {

//	public static void main(String args[]) {
//		IVector xd = new IVector(4);
//		for(int i=0; i < xd.length(); i++)
//			xd.set(i, new Interval(-5,5));
//		double a = System.currentTimeMillis();
//		Brain.multiSolve(xd);
//		double b = System.currentTimeMillis();
//		System.out.println(solution.size() + " " + (b-a));
//	}
	
	public static Constructor constructor = new Krawcxyk();
	public static Function function = new Test();
	public static Stack<IVector> solution = new Stack<IVector>();
	public static Stack<IVector> xstack = new Stack<IVector>();
	
	public static void multiSolve(IVector xd){
		Stack<Thread> threads = new Stack<Thread>();
		
		threads.push(new Thread(new ThreadSolver(xd)));
		threads.peek().start();
		while(!threads.isEmpty()) {
			try {
				threads.peek().join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			threads.pop();
			while(!xstack.isEmpty()) {
				threads.push(new Thread(new ThreadSolver(xstack.pop())));
				threads.peek().start();
			}
		}
	
	}
	public static LinkedList<IVector> runSolve(IVector xd) {
		IVector x0, x1, K;
		LinkedList<IVector> xlist = new LinkedList<IVector>();
		double epsilon = 1e-7;

		x0 = xd;
		while(x0 != null){
			K = constructor.solve(function, x0);
			if(K != null) {
				x1 = IMath.intersect(K, x0);
				if(x1 != null) {
					if(IMath.maxWidth(x1) < epsilon) {
						xlist.add(x1);
						break;
					}else {
						if(!IMath.areEqual(x0, x1)) {
							x0 = x1;
						}else {
							xlist = IMath.bisect(x0);
							break;
						}
					}
				}else{
					x0 = null;
				}
			}else {
				xlist = IMath.bisect(x0);
				break;
			}
		}
		return xlist;
	}
	
	public static Stack<IVector> solve(IVector xd) {		
		Stack<IVector> xstack = new Stack<IVector>();
		Stack<IVector> solution = new Stack<IVector>();
		IVector x0, x1, K;
		double epsilon = 1e-7;

		xstack.push(xd);
		while(!xstack.isEmpty()) {
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
class ThreadSolver implements Runnable {
	private IVector vx;
	
	ThreadSolver(IVector theVx){
		vx = theVx;
	}
	@Override
	public void run() {
		LinkedList<IVector> xlist;
        xlist = Brain.runSolve(vx);
        if(xlist.size() == 1){
        	Brain.solution.push(xlist.get(0));
        }else if(xlist.size() == 2) {
        	Brain.xstack.push(xlist.get(0));
        	Brain.xstack.push(xlist.get(1));
        }
	}
	   
}