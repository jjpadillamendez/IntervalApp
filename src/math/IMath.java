package math;
import java.util.LinkedList;
import java.util.Random;
import Jama.Matrix;

/***********************************************************/
/** Author: Jesus Padilla    Class: Interval Computations **/
/** Professor: Dr. Vladik Kreinovich     Homework 3       **/
/** Last Modification: September 17th 2017    (Java)      **/
/***********************************************************/
public class IMath {
		
	public static Interval add(Interval a, Interval b){
		return new Interval(a.inf() + b.inf(), a.sup() + b.sup());
		
	}
	private static Interval add(double d, Interval b) {
		return new Interval(d + b.inf(), d + b.sup());
	}

	public static IVector add(IVector va, IVector vb) {
		IVector vc = null;
		int len = (va.length() == vb.length()) ? va.length() : -1;
		if(len > 0){
			vc = new IVector(len);
			for(int i=0; i < len; i++){
				vc.set(i, IMath.add(va.get(i), vb.get(i)));
			}
		}
		return vc;
	}
	public static IVector add(double[] va, IVector vb) {
		IVector vc = null;
		int len = (va.length == vb.length()) ? va.length : -1;
		if(len > 0){
			vc = new IVector(len);
			for(int i=0; i < len; i++){
				vc.set(i, new Interval(va[i]+vb.get(i).inf(), va[i]+vb.get(i).sup()));
			}
		}
		return vc;
	}
	public static IVector add(IVector va, Matrix ma) {
		IVector vc = null;
		int len = (va.length() == ma.getRowDimension()) ? va.length() : -1;
		if(len > 0 && ma.getColumnDimension() == 1){
			vc = new IVector(len);
			for(int i=0; i < len; i++){
				vc.set(i, new Interval(va.get(i).inf()+ma.get(i,0),va.get(i).sup()+ma.get(i,0)));
			}
		}
		return vc;
	}
	public static IVector fakeSub(IVector va, IVector vb) {
		IVector vc = null;
		int len = (va.length() == vb.length()) ? va.length() : -1;
		if(len > 0){
			vc = new IVector(len);
			for(int i=0; i < len; i++){
				vc.set(i,new Interval(va.get(i).inf() - vb.get(i).inf(), va.get(i).sup() - vb.get(i).sup()));
			}
		}
		return vc;
		
	}
	public static Interval sub(Interval a, Interval b){
		return new Interval(a.inf() - b.sup(), a.sup() - b.inf());
		
	}
	public static Interval sub(Interval a, double nb){
		return new Interval(a.inf() - nb, a.sup() - nb);
		
	}
	public static Interval sub(double na, Interval b ){
		return new Interval(na - b.inf(), na - b.sup());
		
	}
	public static IVector sub(IVector va, IVector vb) {
		IVector vc = null;
		int len = (va.length() == vb.length()) ? va.length() : -1;
		if(len > 0){
			vc = new IVector(len);
			for(int i=0; i < len; i++){
				vc.set(i, IMath.sub(va.get(i), vb.get(i)));
			}
		}
		return vc;
		
	}
	public static IVector sub(double[] mva, IVector vb) {
		IVector vc = null;
		int len = (mva.length == vb.length()) ? mva.length : -1;
		if(len > 0){
			vc = new IVector(len);
			for(int i=0; i < len; i++){
				vc.set(i, IMath.sub(mva[i], vb.get(i)));
			}
		}
		return vc;
	}
	public static IVector sub(IVector va, double[] midv) {
		IVector vc = null;
		int len = (va.length() == midv.length) ? va.length() : -1;
		if(len > 0){
			vc = new IVector(len);
			for(int i=0; i < len; i++){
				vc.set(i, IMath.sub(va.get(i), midv[i]));
			}
		}
		return vc;
	}
	public static Interval mult(Interval a, Interval b){
		double c1 = a.inf()*b.inf(), c2 = a.inf()*b.sup();
		double c3 = a.sup()*b.inf(), c4 = a.sup()*b.sup();
		double min = Math.min(Math.min(c1,c2), Math.min(c3,c4));
		double max = Math.max(Math.max(c1,c2), Math.max(c3,c4));
		return new Interval(min, max);
		
	}
	public static Interval mult(double na, Interval b){
		Interval a = new Interval(na,na);
		return mult(a,b);
		
	}
	public static IVector mult(double na, IVector va) {
		int len = va.length();
		IVector vc = new IVector(len);
		Interval a = new Interval(na,na);
		for(int i=0; i < len; i++){
			vc.set(i, IMath.mult(a, va.get(i)));
		}
		
		return vc;
	}
	public static IVector mult(IVector va, IVector vb) {
		IVector vc = null;
		int len = (va.length() == vb.length()) ? va.length() : -1;
		if(len > 0){
			vc = new IVector(len);
			for(int i=0; i < len; i++){
				vc.set(i, IMath.mult(va.get(i), vb.get(i)));
			}
		}
		return vc;
	}
	public static IVector mult(Matrix ma, IVector vb) {
		IVector vc = null;
		int n = (ma.getColumnDimension() == vb.length()) ? vb.length() : -1;
		if(n > 0){
			Interval t1;
			int m = ma.getRowDimension();
			vc = new IVector(m);
			for(int i=0; i < m; i++) {
				vc.set(i, new Interval(0,0));
				for(int k=0; k < n; k++) {
					t1 = IMath.mult(ma.get(i,k), vb.get(k));
					t1 = IMath.add(vc.get(i), t1);
					vc.set(i, t1);
				}
				
			}
		}
		return vc;
	}
	public static IVector mult(IMatrix ma, IVector vb) {
		IVector vc = null;
		int n = (ma.colLen() == vb.length()) ? vb.length() : -1;
		if(n > 0){
			Interval t1;
			int m = ma.rowLen();
			vc = new IVector(m);
			for(int i=0; i < m; i++) {
				vc.set(i, new Interval(0,0));
				for(int k=0; k < n; k++) {
					t1 = IMath.mult(ma.get(i,k), vb.get(k));
					t1 = IMath.add(vc.get(i), t1);
					vc.set(i, t1);
				}
				
			}
		}
		return vc;
	}
	public static IMatrix mult(IMatrix ma, Interval b) {
		int n = ma.rowLen(); int m = ma.colLen();
		IMatrix mc = new IMatrix(n,m);
		for(int i=0; i < n; i++) {
			for(int j=0; j < m; j++) {
				mc.set(i,j, IMath.mult(ma.get(i,j), b));
			}
		}
		return mc;
	}
	
	public static IMatrix mult(Matrix ma, IMatrix mb) {
		IMatrix mc = null;
		int n, m, l;
		n = (ma.getColumnDimension() == mb.rowLen()) ? mb.rowLen() : -1;
		if(n > 0){
			m = ma.getRowDimension();
			l = mb.colLen();
			Interval t1;
			
			mc = new IMatrix(m, l); 
			for(int i=0; i < m; i++) {
				for(int j=0; j < l; j++) {
					mc.set(i, j, new Interval(0,0));
					for(int k=0; k < n; k++) {
						t1 = IMath.mult(ma.get(i,k), mb.get(k,j));
						mc.set(i, j, IMath.add(mc.get(i,j), t1));
					}
				}
			}
		}
		return mc;	
		
	}
	public static Interval div(Interval a, Interval b){
		if(! b.in(0)){
			double c1 = a.inf()/b.inf(), c2 = a.inf()/b.sup();
			double c3 = a.sup()/b.inf(), c4 = a.sup()/b.sup();
			double min = Math.min(Math.min(c1,c2), Math.min(c3,c4));
			double max = Math.max(Math.max(c1,c2), Math.max(c3,c4));
			return new Interval(min, max);
		}
		else{
			return null;
		}
		
	}
	public static IVector div(IVector vb){
		int len = vb.length();
		IVector vc = new IVector(len);
		Interval one = new Interval(1,1);
		for(int i=0; i < len; i++){
			vc.set(i, IMath.div(one, vb.get(i)));
			
		}
		return vc;
	}	
	public static LinkedList<Interval> extDiv(Interval b){ 
	// extended division which handles the situation where the interval b contains 0
		LinkedList<Interval> list = new LinkedList<Interval>();
		Interval one = new Interval(1,1);
		if(b.inf() > 0 || b.sup() < 0){		
			list.add(IMath.div(one, b));
		}
		else if(b.inf() == 0 && b.sup() > 0){
			list.add(new Interval(1/b.sup(), Double.POSITIVE_INFINITY));
		}
		else if(b.inf() < 0 && b.sup() > 0){
			list.add(new Interval(Double.NEGATIVE_INFINITY, 1/b.inf()));
			list.add(new Interval(1/b.sup(), Double.POSITIVE_INFINITY));
		}
		else if(b.inf() < 0 && b.sup() == 0){
			list.add(new Interval(Double.NEGATIVE_INFINITY, 1/b.inf()));
		}
		else {
			list = null;
		}
		return list;
		
	}
	public static double infNorm(IMatrix imatrix) {
		double max = 0, rowSum, low, high;
		Interval ri;
		for(int i=0; i < imatrix.rowLen(); i++) {
			rowSum = 0;
			for(int j=0; j < imatrix.colLen(); j++) {
				ri = imatrix.get(i, j);
				low = Math.abs(ri.inf());
				high = Math.abs(ri.sup());
				rowSum += (low > high) ? low : high;	
			}
			if(rowSum > max)
				max = rowSum;
		}
		return max;
		
	}
	public static double infNorm(IVector va) {
		double max = 0, currMax, low, high;
		int len = va.length();
		for(int i=0; i < len; i++) {
			low = Math.abs(va.get(i).inf());
			high = Math.abs(va.get(i).sup());
			currMax = (low > high) ? low : high;
				
			if(currMax > max)
				max = currMax;
		}
		return max;
		
	}
	public static boolean areEqual(Interval i1, Interval i2) {
		double epsilon = 1e-12;
		return Math.abs(i1.inf()-i2.inf()) < epsilon &&  Math.abs(i1.sup()-i2.sup()) < epsilon; 
		//return i1.inf() == i2.inf() && i1.sup() == i2.sup();
	}
	public static boolean areEqual(IVector va, IVector vb) {
		boolean isEqual = true;
		int len = (va.length() == vb.length()) ? va.length() : -1;
		if(len > 0){
			for(int i=0; i < len; i++){
				if(!areEqual(va.get(i), vb.get(i))){
					isEqual = false;
					break;
				}
			}
		}
		else{
			isEqual = false;
		}
		return isEqual;
		
	}
	public static double maxWidth(IVector ivector) {
		double maxWidth = 0, currWidth = 0;
		int len = ivector.length();
		for(int i=0; i < len; i++) {
			currWidth = ivector.get(i).width();
			if(currWidth > maxWidth)
				maxWidth = currWidth;
			
		}
		
		return maxWidth;
		
	}
	public static double maxWidth(IMatrix ma) {
		double maxWidth = 0, currWidth = 0;
		int n = ma.rowLen(), m = ma.colLen();
		for(int i=0; i < n; i++) {
			for(int j=0; j < m; j++) {
				currWidth = ma.get(i,j).width();
				if(currWidth > maxWidth)
					maxWidth = currWidth;
			}
		}
		return maxWidth;
		
	}
	public static boolean isAnyNull(IVector p1) {
		int len = p1.length();
		for(int i=0; i < len; i++){
			if(p1.get(i) == null)
				return true;
		}
		return false;
		
	}
	public static Interval intersect(Interval a, Interval b) {
		double minSup, maxInf;
		Interval c;
		
		maxInf = Math.max(a.inf(), b.inf());
		minSup = Math.min(a.sup(), b.sup());
		c = (maxInf <= minSup) ? new Interval(maxInf, minSup) : null;
		return c;
		
	}
	public static IVector intersect(IVector va, IVector vb) {
		int len = (va.length() == vb.length()) ? va.length() : -1;
		IVector vc = null;
		Interval a;
		if(len > 0){
			vc = new IVector(len);
			for(int i=0; i < len; i++){
				a = intersect(va.get(i), vb.get(i));
				if(a != null)
					vc.set(i, a);
				else {
					vc = null;
					break;
				}
			}
		}
		return vc;
		
	}
	public static IMatrix intersect(IMatrix ma, IMatrix mb) {
		IMatrix mc = null;
		Interval a;
		int n=ma.rowLen(), m=ma.colLen();
		if(n == mb.rowLen() && m == mb.colLen()){
			mc = new IMatrix(n,m);
			for(int i=0; i < n; i++){
				for(int j=0; j < m; j++) {
					a = intersect(ma.get(i,j), mb.get(i,j));
					if(a != null)
						mc.set(i,j, a);
					else {
						mc = null;
						break;
					}
				}
			}
		}
		return mc;
	}
	public static LinkedList<IVector> bisect(IVector va) {
		int n, maxrow;
		double maxwidth, cwidth;
		LinkedList<IVector> bism1 = new LinkedList<IVector>();
		
		n=va.length();
		maxwidth = 0; cwidth = 0;
		maxrow=0;
		for(int i=0; i < n; i++) {
			cwidth = va.get(i).width();
			if(cwidth > maxwidth){
				maxrow=i;
				maxwidth = cwidth;
			}
		}

		Interval a;
		IVector vb = new IVector(n);
		IVector vc = new IVector(n);
		for(int i=0; i < n; i++) {
			a = va.get(i);
			if(i == maxrow){
				vb.set(i, new Interval(a.inf(), a.midpoint()));
				vc.set(i, new Interval(a.midpoint(), a.sup()));
			}
			else{
				vb.set(i, new Interval(a.inf(), a.sup()));
				vc.set(i, new Interval(a.inf(), a.sup()));
			}
		}
		bism1.add(vc);
		bism1.add(vb);
		return bism1;
	}
	public static Interval pow(Interval a, int n) {
		Interval rst;
		if(n%2 == 0){
			double powinf = Math.pow(a.inf(), n);
			double powsup = Math.pow(a.sup(), n);
			if(a.in(0)){
				rst = new Interval(0, Math.max(powinf, powsup));
			}else{
				rst = new Interval(Math.min(powinf, powsup), Math.max(powinf, powsup));
			}
		}else{
			rst = new Interval(Math.pow(a.inf(), n), Math.pow(a.sup(), n));
		}
		return rst;
		
	}
	public static IVector toNegative(IVector va) {
		int n=va.length();
		IVector negvector = new IVector(n);
		for(int i=0; i < n; i++) {
			negvector.set(i, new Interval(-va.get(i).sup(), -va.get(i).inf()));
		}
		return negvector;
		
	}
	public static IMatrix identity(int len) {
		IMatrix ma = new IMatrix(len,len);
		for(int i=0; i < len; i++) {
			for(int j=0; j < len; j++) {
				if(i==j)
					ma.set(i, i, new Interval(1,1));
				else
					ma.set(i, j, new Interval(0,0));
			}
		}
		return ma;
		
	}
	public static Matrix midMatrix(IMatrix imatrix) {
		int n=imatrix.rowLen(), m=imatrix.colLen();
		Matrix matrix = new Matrix(n, m);
		for(int i=0; i < n; i++) {
			for(int j=0; j < m; j++) {
				matrix.set(i,j, imatrix.get(i,j).midpoint());
			}
		}
		return matrix;
		
	}
	public static IMatrix sub(IMatrix ma, IMatrix mb) {
		// CASE WHEN THEY ARE NOT OF THE SAME SIZE
		IMatrix imatrix = new IMatrix(ma.rowLen(), ma.colLen());
		for(int i=0; i < ma.rowLen(); i++) {
			for(int j=0; j < ma.colLen(); j++) {
				imatrix.set(i,j, IMath.sub(ma.get(i,j), mb.get(i,j)));
			}
		}
		return imatrix;
		
	}
	public static IVector buildVector(int len, Interval ri) {
		IVector va = null;
		if(len > 0) {
			va = new IVector(len);
			for(int i=0; i < len; i++) {
				va.set(i, ri);
			}
		}
		return va;
		
	}
	public static Matrix randMatrix(IMatrix ma) {
		Random rand = new Random();
		int n=ma.rowLen(), m=ma.colLen();
		Matrix matrix = new Matrix(n, m);
		for(int i=0; i < n; i++) {
			for(int j=0; j < m; j++) {
				matrix.set(i,j,  Math.floor(ma.get(i,j).inf()) + ((Math.floor(ma.get(i,j).inf()) -  Math.floor(ma.get(i,j).inf())) * rand.nextDouble()));
			}
		}
		return matrix;
	}
	public static IMatrix randomIMatrix(int n) {
		IMatrix ma = null;
		if(n > 0) {
			int max = 1000;
			int min = -1000;
			Random rand = new Random();
			ma = new IMatrix(n,n);
			for(int i=0; i < n; i++) {
				for(int j=0; j < n; j++) {
					int r1 =  rand.nextInt(max + 1 -min) + min; 
					int r2 =  rand.nextInt(max + 1 -min) + min; 
					if(r1 > r2)
						ma.set(i,j,new Interval(r2,r1));
					else
						ma.set(i,j,new Interval(r1,r2));
				}
			}
		}
		return ma;
	}
	public static Matrix randomMatrix(int n) {
		Matrix ma = null;
		if(n > 0) {
			int max = 1000;
			int min = -1000;
			Random rand = new Random();
			ma = new Matrix(n,n);
			for(int i=0; i < n; i++) {
				for(int j=0; j < n; j++) {
					int r1 =  rand.nextInt(max + 1 -min) + min; 
					ma.set(i,j, r1);
				}
			}
		}
		return ma;
	}
	public static Matrix mult(Matrix ma, Matrix mb) {
		Matrix mc = null;
		int n, m, l;
		n = (ma.getColumnDimension() == mb.getRowDimension()) ? mb.getRowDimension() : -1;
		if(n > 0){
			m = ma.getRowDimension();
			l = mb.getColumnDimension();
			double t1;
			
			mc = new Matrix(m, l); 
			for(int i=0; i < m; i++) {
				for(int j=0; j < l; j++) {
					mc.set(i, j, 0);
					for(int k=0; k < n; k++) {
						t1 = ma.get(i,k) * mb.get(k,j);
						mc.set(i, j, mc.get(i,j)+t1);
					}
				}
			}
		}
		return mc;	
		
	}
	public static IMatrix mult(IMatrix ma, IMatrix mb) {
		IMatrix mc = null;
		int n, m, l;
		n = (ma.colLen() == mb.rowLen()) ? mb.colLen() : -1;
		if(n > 0){
			m = ma.rowLen();
			l = mb.colLen();
			Interval t1;
			
			mc = new IMatrix(m, l); 
			for(int i=0; i < m; i++) {
				for(int j=0; j < l; j++) {
					mc.set(i, j, new Interval(0,0));
					for(int k=0; k < n; k++) {
						t1 = IMath.mult(ma.get(i,k), mb.get(k,j));
						mc.set(i, j, IMath.add(mc.get(i,j), t1));
					}
				}
			}
		}
		return mc;	
		
	}
	public static IMatrix add(IMatrix ma, Matrix mb) {
		IMatrix mc = null;
		int n = ma.rowLen(), m = ma.colLen();
		if(n == mb.getRowDimension() && m == mb.getColumnDimension()) {
			mc = new IMatrix(n,m);
			for(int i=0; i < n; i++) {
				for(int j=0; j < m; j++) {
					mc.set(i,j, IMath.add(mb.get(i,j), ma.get(i,j)));
				}
			}
		}
		return mc;	
	}
	
	
	
}
