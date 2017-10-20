package source.math;

public class Interval {
	
	private double inf;
	private double sup;
		
	public Interval(double theInf, double theSup) {
		this.inf = theInf;
		this.sup = theSup;
	}
	public double inf() {
		return this.inf;
	}
	public double sup() {
		return this.sup;
	}
	public double midpoint() {
		return (this.inf + this.sup)/2;
	}
	public double width() {
		return (this.sup - this.inf);
	}
	public boolean in(double num){
		return (this.inf() < num && this.sup() > num);
	}
	public String toString() {
		return "[" + this.inf() + "," + this.sup() + "]"; 
	}
}
