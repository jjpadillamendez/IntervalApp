package source.constructors;

import source.functions.Function;
import source.math.IVector;

public abstract class Constructor {

	abstract public IVector solve(Function func, IVector x);
	
}
