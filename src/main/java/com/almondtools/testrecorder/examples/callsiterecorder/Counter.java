package com.almondtools.testrecorder.examples.callsiterecorder;

public class Counter {
	private int counter;

	public int get() {
		return counter;
	}

	public Counter inc() {
		counter++;
		return this;
	}

	public Counter reset() {
		this.counter = 0;
		return this;
	}
}