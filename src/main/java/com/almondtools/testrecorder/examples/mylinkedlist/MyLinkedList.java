package com.almondtools.testrecorder.examples.mylinkedlist;

import java.util.NoSuchElementException;

import net.amygdalum.testrecorder.Snapshot;

public class MyLinkedList {
	private MyElement next;
	
	@Snapshot
	public void add(Object element) {
		if (next == null) {
			next = new MyElement(element);
		} else {
			MyElement current = next;
			while (current.next != null) {
				current = current.next;
			}
			current.next = new MyElement(element);
		}
	}
	
	@Snapshot
	public void remove(int index) {
		if (index == 0) {
			if (next ==  null) {
				throw new NoSuchElementException();
			} else {
				next = next.next;
			}
		} else {
			remove(next, index);
		}
	}
	
	private void remove(MyElement current, int index) {
		if (index == 1) {
			if (current.next == null) {
				throw new NoSuchElementException();
			} else {
				current.next = current.next.next;
			}
		} else {
			remove (current.next, index - 1);
		}
	}

	@Snapshot
	public Object get(int index) {
		return get(next, index);
	}

	private Object get(MyElement current, int index) {
		if (current == null) {
			throw new NoSuchElementException();
		}
		if (index == 0) {
			return current.data;
		} else {
			return get(current.next, index -1);
		}
	}

	public static class MyElement {

		public Object data;
		public MyElement next;

		public MyElement(Object data) {
			this.data = data;
		}

	}
}
