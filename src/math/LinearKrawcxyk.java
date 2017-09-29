package com.example.jesus.myapplication.math;

//import Jama.Matrix;

public class LinearKrawcxyk {
//
//	public static IMatrix solve(IMatrix A, IMatrix b) {
//		Matrix y;
//		IMatrix yA, I, E, x0, xd, x1;
//		
//		y = IMath.midMatrix(A).inverse();
//	//	System.out.println(y.toString());
//		yA = IMath.mult(y, A);
//	//	System.out.println("yA " + yA);
//		I = IMath.identity(A.rowLen());
//	//	System.out.println("I " + I);
//		E = IMath.sub(I, yA);
//	//	System.out.println("E " + E);
//		
//		double yb, Ex0;
//		
//		yb = IMath.infNorm(IMath.mult(y, b));
//	//	System.out.println("yb " + yb);
//		Ex0 = 1 - IMath.infNorm(E);
//	//	System.out.println("Ex0 " + Ex0);
//		xd = IMath.buildMatrix(b.rowLen(), 1, new RealInterval(-1,1));
//	//	System.out.println("xd " + xd);
//		x0 = IMath.mult(yb/Ex0, xd);		
//	//	System.out.println("x0 " + x0);
//		try{
//			x1 = IMath.intersect(IMath.add(IMath.mult(y, b), IMath.mult(E,x0)), x0);
//		//	System.out.println("x1 " + x1);
//		}catch(net.sourceforge.interval.ia_math.IAException e) {
//			x1 = null;
//		}
//		return x1;
//	
//	}

}
