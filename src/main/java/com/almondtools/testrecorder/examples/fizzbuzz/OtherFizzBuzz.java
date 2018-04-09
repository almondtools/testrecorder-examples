package com.almondtools.testrecorder.examples.fizzbuzz;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import net.amygdalum.testrecorder.profile.Recorded;

public class OtherFizzBuzz {

	private Set<String> notInvolvedSet;
	private Map<String, String> notInvolvedMap;
	private Set<String> nullSet;
	private String[] notInvolvedArray;
	
	public OtherFizzBuzz() {
		this.notInvolvedSet = new HashSet<>();
		this.notInvolvedMap = new HashMap<>();
		notInvolvedMap.put("key", "value");
		this.notInvolvedArray = new String[0];
		this.nullSet = null;
	}
	
	public Set<String> getNotInvolvedSet() {
		return notInvolvedSet;
	}
	
	public Map<String, String> getNotInvolvedMap() {
		return notInvolvedMap;
	}
	
	public String[] getNotInvolvedArray() {
		return notInvolvedArray;
	}
	
	public Set<String> getNullSet() {
		return nullSet;
	}

	@Recorded
	public String fizzBuzz(int i) {
		if (i % 15 == 0) {
			return "FizzBuzz";
		} else if (i % 3 == 0) {
			return "Fizz";
		} else if (i % 5 == 0) {
			return "Buzz";
		} else {
			return String.valueOf(i);
		}
	}

}