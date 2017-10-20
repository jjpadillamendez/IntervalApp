package functions;

import Jama.Matrix;
import math.IMatrix;
import math.IVector;

public abstract class Function {
	public abstract IVector f(IVector x);
	public abstract IVector f(double[] x);
	
	public abstract IMatrix J(IVector x);
	public abstract Matrix J(double[] x);
}
