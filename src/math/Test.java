package com.example.jesus.myapplication.math;

import java.util.LinkedList;

public class Test {
	public static void main(String args[]) {
		Interval a = new Interval(2,4);
		Interval b = new Interval(-3,-1);
		Interval c = new Interval(-2,2);
		
		System.out.println("Test Cases: a = [2,4] ; b = [-3,-1]");
		System.out.println("   a+b = Expected: [-1,3] :" + " Result: "+IMath.add(a, b));
		System.out.println("   a-b = Expected: [3,7] :"+" Result: "+IMath.sub(a, b));
		System.out.println("   a*b = Expected: [-12,-2] :"+" Result:"+IMath.mult(a, b));
		System.out.println("   a/b = Expected: [-4,-2/3] : "+" Result:"+IMath.div(a, b)+"\n");
		
		System.out.println("Test Cases: a = [2,4] ; c = [-2,2]");
		System.out.println("   a/c = Expected: null :" + " Result: "+IMath.div(a, c));

		System.out.println("   ext(a/c) = Expected: [-inf,-1],[1,inf]");
		LinkedList<Interval> list = IMath.extDiv(c);
		for(Interval c0 : list) {
			System.out.println("\t      Result: " + IMath.mult(a, c0));
		}
		
	}
}
