package constructors;

import functions.Function;
import math.GaussSeidel;
import math.IMath;
import math.IMatrix;
import math.IVector;
import math.LinearKrawcxyk;

public class Newton extends Constructor {
		
	public IVector solve(Function func, IVector x){
		IMatrix A;
		IVector b, v, retVec, x1, p;
		double[] midx;
		
		A = func.J(x);
		midx = x.midpoint();
		b = IMath.toNegative(func.f(x));
		v = LinearKrawcxyk.solve(A, b, IMath.sub(x, midx));
		
		retVec = null;
		
		if(v != null) {
			x1 = IMath.add(x, v);
			p = IMath.intersect(x, x1);
			retVec = p;				// retVec can still be either null OR new x
		}
		
		return retVec;
		
		
	}
}
