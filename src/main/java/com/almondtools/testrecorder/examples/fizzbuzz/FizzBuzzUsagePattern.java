package com.almondtools.testrecorder.examples.fizzbuzz;

import java.util.stream.Collectors;

public class FizzBuzzUsagePattern {

	public static void main(String[] args) {

		FizzBuzz fizzBuzz = new FizzBuzz();
		for (int i = 1; i <= 100; i++) {
			fizzBuzz.fizzBuzz(i);
		}
		System.out.println(fizzBuzz.getBuffer().stream()
			.collect(Collectors.joining("\n", "", "\n")));
	}

}
