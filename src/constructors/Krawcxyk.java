package constructors;

import Jama.Matrix;
import functions.Function;
import functions.Test;
import math.IMath;
import math.IMatrix;
import math.IVector;

public class Krawcxyk extends Constructor {
	
	public IVector solve(Function funct, IVector x) {
		int len;
		double[] midx;
		Matrix M;
		IVector xx0, Kx, Jxx0, Mf0, x0Mf0;
		IMatrix Jx, MJ, J_phi;
		
		len = x.length();
		midx = x.midpoint();
		try {
			M = funct.J(midx).inverse();
		}catch(java.lang.RuntimeException e) {
			M = null;
		}
		if(M != null) {
			Jx = funct.J(x);
			MJ = IMath.mult(M,Jx);
			J_phi = IMath.sub(IMath.identity(len), MJ);
			Mf0 = IMath.mult(M, funct.f(midx));
			x0Mf0 = IMath.sub(midx, Mf0);
			xx0 = IMath.sub(x, midx);
			Jxx0 = IMath.mult(J_phi, xx0);
			Kx = IMath.add(x0Mf0, Jxx0);
		}else {
			Kx = null;
		}
		return Kx;
		
	}
}
