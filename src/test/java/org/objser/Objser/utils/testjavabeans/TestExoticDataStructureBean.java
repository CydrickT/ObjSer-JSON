package org.objser.Objser.utils.testjavabeans;

import java.util.LinkedList;
import java.util.Stack;
import java.util.TreeMap;
import java.util.TreeSet;

public class TestExoticDataStructureBean implements IBean {
	private final LinkedList<Object> linkedList;
	private final Stack<Object> stack;
	private final TreeSet<Object> treeSet;
	private final TreeMap<Object, Object> treeMap;

	public TestExoticDataStructureBean(LinkedList<Object> linkedList, Stack<Object> stack, TreeSet<Object> treeSet,
			TreeMap<Object, Object> treeMap) {
		this.linkedList = linkedList;
		this.stack = stack;
		this.treeSet = treeSet;
		this.treeMap = treeMap;
	}

	protected LinkedList<Object> getLinkedList() {
		return linkedList;
	}

	protected Stack<Object> getStack() {
		return stack;
	}

	protected TreeSet<Object> getTreeSet() {
		return treeSet;
	}

	protected TreeMap<Object, Object> getTreeMap() {
		return treeMap;
	}

}
