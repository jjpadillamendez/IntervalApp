package math;

import Jama.Matrix;

public class IMatrix {
	
	private Interval matrix[][];
	
	public IMatrix(int colLen, int rowLen) {
		this.matrix = new Interval[colLen][rowLen];
	}
	public void set(int row, int col, Interval ri) {
		this.matrix[row][col] = ri;
	}
	public Interval get(int row, int col) {
		return this.matrix[row][col];	
	}
	public int rowLen() {
		return this.matrix.length;
	}
	public int colLen() {
		return this.matrix[0].length;
	}
	public String toString() {
		String output = "";
		for(int i=0; i < this.matrix.length; i++){
			for(int j=0; j < this.matrix[0].length; j++){
				output += matrix[i][j] + " ";
			}
			output += "\n";
		}
		return output;
		
	}
	public IVector diagonal(){
		int len = rowLen();
		IVector ivector = new IVector(len);
		for(int i=0; i < len; i++){
			ivector.set(i, this.matrix[i][i]);
		}
		return ivector;
		
	}
	public IMatrix extDiagonal() {
		int rowLen = rowLen(), colLen = colLen();
		IMatrix extma = new IMatrix(rowLen, colLen);
		for(int i=0; i < rowLen; i++) {
			for(int j=0; j < colLen; j++) {
				if(i == j){
					extma.set(i,j, new Interval(0,0));
				}
				else{
					Interval a = this.matrix[i][j];
					extma.set(i,j, new Interval(a.inf(), a.sup()));
				}
			}
		}
		return extma;
		
	}
	public Matrix midMatrix() {
		int n=rowLen(), m=colLen();
		Matrix matrix = new Matrix(n, m);
		for(int i=0; i < n; i++) {
			for(int j=0; j < m; j++) {
				matrix.set(i,j, get(i,j).midpoint());
			}
		}
		return matrix;
		
	}
	public IMatrix traspose() {
		int n=matrix.length; int m=matrix[0].length;
		IMatrix ma = new IMatrix(m, n);
		for(int i=0; i < n; i++) {
			for(int j=0; j < m; j++) {
				ma.set(j,i, new Interval(this.matrix[i][j].inf(), this.matrix[i][j].sup()));
			}
		}
		return ma;
	}
	
	
}
