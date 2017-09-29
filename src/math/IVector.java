package com.example.jesus.myapplication.math;

public class IVector {

private Interval vector[];
	
	public IVector(int len) {
		this.vector = new Interval[len];
	}
	public void set(int len, Interval ri) {
		this.vector[len] = ri;
	}
	public Interval get(int len) {
		return this.vector[len];	
	}
	public int length() {
		return this.vector.length;
	}
	public String toString() {
		String output = "";
		for(int i=0; i < this.vector.length; i++){
			output += ""+this.vector[i]+ "\n";
		}
		output += "\n";
		return output;
		
	}
	public IVector midpoint() {
		// think about efficiency
		IVector midVector = new IVector(this.vector.length);
		for(int i=0; i < this.vector.length; i++){
			midVector.set(i, new Interval(this.vector[i].midpoint(),this.vector[i].midpoint()));
		}
		return midVector;
		
	}
	
}
