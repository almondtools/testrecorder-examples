package com.almondtools.testrecorder.examples.mylinkedlist;

public class MyLinkedListUsagePattern {

	public static void main(String[] args) {
		MyLinkedList myList = new MyLinkedList();
		myList.add("string");
		myList.add(1);
		myList.add(true);
		myList.remove(1);
		myList.add(myList.get(0));
		myList.add(myList.get(1));
		myList.remove(0);
		myList.remove(0);
		
	}

}
