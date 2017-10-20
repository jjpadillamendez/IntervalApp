package source.functions;

import source.Jama.Matrix;
import source.math.IMatrix;
import source.math.IVector;

public abstract class Function {
	public abstract IVector f(IVector x);
	public abstract IVector f(double[] x);
	
	public abstract IMatrix J(IVector x);
	public abstract Matrix J(double[] x);

	public abstract String getVarAsString(int i);
}
