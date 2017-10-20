package source.math;

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
	public double[] midpoint() {
		// think about efficiency
		double midv[] = new double[this.vector.length];
		for(int i=0; i < midv.length; i++){
			midv[i] = this.vector[i].midpoint();
		}
		return midv;
		
	}
	
}
