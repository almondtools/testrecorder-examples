package com.almondtools.testrecorder.examples.codeserializer;

public class ExampleObject {
    private int counter;

    public ExampleObject inc() {
    	counter++;
        return this;
    }
    
    public ExampleObject reset() {
    	this.counter = 0;
    	return this;
    }
    
    public int get() {
		return counter;
	}
}