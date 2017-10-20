package functions;

import Jama.Matrix;
import math.IMath;
import math.IMatrix;
import math.IVector;
import math.Interval;

public class LoktaVolterra {
	
	double theta1 = 3;
	double  theta2 = 1;
	double v0 = 1.2;
	double w0 = 1.1;
	double h = 0.1;
	int n = 5;
	
	public IMatrix J(IVector x) {
		IVector v = new IVector(n);
		IVector w = new IVector(n);
		IMatrix JJ = new IMatrix(2*n, 2*n);
		int i, j;

		for(i=0; i < n; i++){
			v.set(i,x.get(i));
			w.set(i,x.get(n+i));
		}

		// Filling the Jacobian with zeros 

		for(i=0; i < JJ.rowLen(); i++){
			for(j=0; j < JJ.colLen(); j++){
				JJ.set(i,j, new Interval(0,0));     
			}
		}

		// Defining the values of the Jacobian 

		JJ.set(0,0, IMath.mult(-2*h*theta1, IMath.sub(1, w.get(0))));
		JJ.set(0,1, new Interval(1,1));
		JJ.set(0,n, IMath.mult(2*h*theta1, v.get(0)));

		JJ.set(1,0, IMath.mult(-2*h*theta2, w.get(0)));
		JJ.set(1,n, IMath.mult(-2*h*theta2, IMath.sub(v.get(0), 1)));
		JJ.set(1,n+1, new Interval(1,1));

		for(i=1; i < n-1; i++){
			JJ.set(2*i,i-1, new Interval(-1,-1));
			JJ.set(2*i,i, IMath.mult(-2*h*theta1, IMath.sub(1, w.get(i))));
			JJ.set(2*i,i+1, new Interval(1,1));
			JJ.set(2*i,n+i, IMath.mult(2*h*theta1, v.get(i)));

			JJ.set(2*i+1,n+i-1, new Interval(-1,-1));
			JJ.set(2*i+1,i, IMath.mult(-2*h*theta2, w.get(i)));
			JJ.set(2*i+1,n+i+1, new Interval(1,1));
			JJ.set(2*i+1,n+i, IMath.mult(-2*h*theta2, IMath.sub(v.get(i), 1)));
		}

		JJ.set(2*i,i-1, new Interval(-1,-1));
		JJ.set(2*i,i, IMath.sub(1, IMath.mult(h*theta1, IMath.sub(1, w.get(i)))));
		JJ.set(2*i,n+i, IMath.mult(h*theta1, v.get(i)));

		JJ.set(2*i+1,i, IMath.mult(-h*theta2, w.get(i)));
		JJ.set(2*i+1,n+i-1, new Interval(-1,-1));
		JJ.set(2*i+1,n+i, new Interval(1,1));
		JJ.set(2*i+1, n+i,IMath.sub(1, IMath.mult(h*theta2, IMath.sub(v.get(i), 1))));

		return JJ;
	

	}
	public IVector f(double[] x) {
			double[] v = new double[n];
			double[] w = new double[n];
			double[] FF = new double[2*n];
			int i;

			for(i=0; i < n; i++){
				v[i]=x[i];
				w[i]=x[i+n];
			}

			FF[0]=v[1] - v0 - (2*h*theta1*v[0]*(1-w[0]));
			FF[1]=w[1] - w0 - (2*h*theta2*w[0]*(v[0]-1));

			for(i=1; i < n-1; i++){
				FF[2*i] = v[i+1] - v[i-1] - (2*h*theta1*v[i]*(1-w[i]));
				FF[2*i+1] = w[i+1] - w[i-1] - (2*h*theta2*w[i]*(v[i]-1));
			}

			FF[2*i] = v[i] - v[i-1] - (h*theta1*v[i]*(1-w[i]));
			FF[2*i+1] = w[i] - w[i-1] - (h*theta2*w[i]*(v[i]-1));

			IVector vf = new IVector(FF.length);
			for(i=0; i < FF.length; i++)
				vf.set(i, new Interval(FF[i], FF[i]));
			
			return vf;
		
	}
	public Matrix J(double[] x) {
		double[]    v = new double[n];
		double[]    w = new double[n];
		double[][] JJ = new double[2*n][2*n];
		int i, j;

		for(i=0; i < n; i++){
			v[i]=x[i];
			w[i]=x[n+i];
		}

		// Filling the Jacobian with zeros 

		for(i=0; i < JJ.length; i++){
			for(j=0; j < JJ[0].length; j++){
				JJ[i][j]=0.0;     
			}
		}

		// Defining the values of the Jacobian 


		JJ[0][0] = -2*h*theta1*(1-w[0]);
		JJ[0][1] = 1;
		JJ[0][n] = 2*h*theta1*v[0];

		JJ[1][0] = -2*h*theta2*w[0];
		JJ[1][n] = -2*h*theta2*(v[0]-1);
		JJ[1][n+1] = 1;

		for(i=1; i < n-1; i++){
			JJ[2*i][i-1] = -1;
			JJ[2*i][i] = -2*h*theta1*(1-w[i]);
			JJ[2*i][i+1] = 1;
			JJ[2*i][n+i] = 2*h*theta1*v[i];

			JJ[2*i+1][n+i-1] = -1;
			JJ[2*i+1][i] = -2*h*theta2*w[i];
			JJ[2*i+1][n+i+1] = 1;
			JJ[2*i+1][n+i] = -2*h*theta2*(v[i]-1);
		}

		JJ[2*i][i-1] = -1;
		JJ[2*i][i] = 1 - (h*theta1*(1-w[i]));
		JJ[2*i][n+i] = h*theta1*v[i];

		JJ[2*i+1][i] = -h*theta2*w[i];
		JJ[2*i+1][n+i-1] = -1;
		JJ[2*i+1][n+i] = 1 - (h*theta2*(v[i]-1));


		Matrix A= new Matrix(JJ);  
		return A;
	
	}
}
