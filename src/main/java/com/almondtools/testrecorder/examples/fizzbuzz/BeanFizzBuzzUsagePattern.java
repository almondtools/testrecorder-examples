package com.almondtools.testrecorder.examples.fizzbuzz;

public class BeanFizzBuzzUsagePattern {

	public static void main(String[] args){

		BeanFizzBuzz fizzBuzz = new BeanFizzBuzz();
		for(int i= 1; i <= 100; i++){
			fizzBuzz.fizzBuzz(i);
		}
	}
	
}
