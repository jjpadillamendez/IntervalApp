package constructors;

import Jama.Matrix;
import math.IMath;
import math.IMatrix;

public class Lyapunor {

	public IMatrix solve(int n, IMatrix A, Matrix B, IMatrix x) {
		IMatrix Ax, AxAt;
		Matrix BBt;
	
		if(A != null && B != null) {
			Ax = IMath.mult(A,x);
			AxAt = IMath.mult(Ax,A.traspose());
			BBt = IMath.mult(B, B.transpose());
			return IMath.add(AxAt, BBt);
		}
		return null;
		
	}

}
