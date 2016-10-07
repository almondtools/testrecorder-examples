package com.almondtools.testrecorder.examples.fizzbuzz;

import net.amygdalum.testrecorder.Snapshot;

public class BeanFizzBuzz {

	private String last;

	public BeanFizzBuzz() {
	}
	
	public void setLast(String last) {
		this.last = last;
	}

	@Snapshot
	public void fizzBuzz(int i) {
		if (i % 15 == 0) {
			last = "FizzBuzz";
		} else if (i % 3 == 0) {
			last = "Fizz";
		} else if (i % 5 == 0) {
			last = "Buzz";
		} else {
			last = String.valueOf(i);
		}
		System.out.println(last);
	}
	
	
}