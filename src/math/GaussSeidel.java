package com.example.jesus.myapplication.math;

import Jama.Matrix;

public class GaussSeidel {
	public static IVector solve(IMatrix A, IVector b, IVector p) {
		Matrix invA0 = A.midMatrix().inverse();
		IMatrix A_prime = IMath.mult(invA0, A);
		IVector b_prime = IMath.mult(invA0, b);
		
		return step(A_prime, b_prime, p);

	}
	private static IVector step(IMatrix A, IVector b, IVector p){
		IVector diagA = A.diagonal();
		IMatrix extDiagA = A.extDiagonal();
		IVector invdiagA = IMath.div(diagA);			// implement condition for when contains 0
			
		if(IMath.isAnyNull(invdiagA)){
		    p = null;
		    return p; 
		}
		
		IVector p1;
		double norma = 1;

		while(norma > 1e-12){
			p1 = IMath.mult(extDiagA, p);
			p1 = IMath.sub(b, p1);
			p1 = IMath.mult(invdiagA, p1);
			p1 = IMath.intersect(p, p1);
		
			if(IMath.isAnyNull(p1)){
			    p = null;
			    break;
			}
			
			norma = IMath.infNorm((IMath.fakeSub(p1, p)));
			p = p1;
		
		}
		return p;
	}
}
