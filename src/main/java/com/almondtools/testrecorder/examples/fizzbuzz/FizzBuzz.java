package com.almondtools.testrecorder.examples.fizzbuzz;

import java.util.ArrayList;
import java.util.List;

import net.amygdalum.testrecorder.Snapshot;

public class FizzBuzz {

	private List<String> buffer;

	public FizzBuzz() {
		this(new ArrayList<>());
	}
	
	public FizzBuzz(List<String> buffer) {
		this.buffer = buffer;
	}

	@Snapshot
	public void fizzBuzz(int i) {
		if (i % 15 == 0) {
			buffer.add("FizzBuzz");
		} else if (i % 3 == 0) {
			buffer.add("Fizz");
		} else if (i % 5 == 0) {
			buffer.add("Buzz");
		} else {
			buffer.add(String.valueOf(i));
		}
	}
	
	public List<String> getBuffer() {
		return buffer;
	}
	
	
}