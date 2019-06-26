package com.efraim.model;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

//TODO This is just code snippets that need patching and some redesign
class DiceGraph<T>  {

	private Map<T, Node<T>> nodeHashMap = new HashMap<>();
	private ArrayList<Edge<T>> edgeArrayList = new ArrayList<>();

	class Edge<T> implements Comparable<Edge<T>> {// gör om till dice
		private Node<T> firstNode;
		private Node<T> secondNode;
		private int cost;

		Edge(Node<T> firstNode, Node<T> secondNode, int cost) {
			this.firstNode = firstNode;
			this.secondNode = secondNode;
			this.cost = cost;
		}

		@Override
		public int compareTo(Edge<T> other) {
			// jämför, returnerar 1 om other.cost < cost, -1 om tvärtom, 0 om lika
			return Integer.compare(cost, other.cost);
		}
	}

	class Node<T> implements Comparable<Node<T>> {

		private T nodeData;
		private T previousNode;
		private boolean isVisited;
		private Set<T> adjacentNodes;

		Node(T data) {
			this.nodeData = data;
			isVisited = false;
			adjacentNodes = new HashSet<>();
			previousNode = null;
		}

		@Override
		public int compareTo(Node<T> other) {
			if (other.nodeData.equals(nodeData)) {
				return 0;
			} else {
				return -1;
			}
		}
	}

//	public boolean add(T newNodeData) { // tror ej behövs, ska inte lägga till nytt nummer på tärning
//		if (nodeHashMap.containsKey(newNodeData)) {
//			return false;
//		}
//
//		Node<T> node = new Node<>(newNodeData);
//		nodeHashMap.put(newNodeData, node);
//		return true;
//	}

	public boolean connect(T node1, T node2, int weight) {
		if (!nodeHashMap.containsKey(node1) || !nodeHashMap.containsKey(node2)) {
			return false;
		}

//		if (weight <= 0) {
//			return false;
//		}

		boolean edgeFound = false;
		Edge<T> theFoundEdge = null;

		for(Edge<T> edge : edgeArrayList){
			if(edge.firstNode.nodeData.equals(node1) && edge.secondNode.nodeData.equals(node2) ||
					edge.firstNode.nodeData.equals(node2) && edge.secondNode.nodeData.equals(node1)){
				edgeFound = true;
				theFoundEdge = edge;
			}
		}

		if (edgeFound) {
			theFoundEdge.cost = weight;
			return true;
		}

		if (!edgeFound) {
			Node<T> firstNode = nodeHashMap.get(node1);
			Node<T> secondNode = nodeHashMap.get(node2);
			Edge<T> tEdge = new Edge<>(firstNode, secondNode, weight);
			edgeArrayList.add(tEdge);
			secondNode.adjacentNodes.add(node1);
			firstNode.adjacentNodes.add(node2);
			return true;
		}
		return false;
	}

	public boolean isConnected(T oneNode, T anotherNode) {
		if (!nodeHashMap.containsKey(oneNode) || !nodeHashMap.containsKey(anotherNode)) {
			return false;
		}

		for (Edge<T> edge : edgeArrayList) {
			if (edge.firstNode.nodeData.equals(oneNode) && edge.secondNode.nodeData.equals(anotherNode) ||
					edge.firstNode.nodeData.equals(anotherNode) && edge.secondNode.nodeData.equals(oneNode)) {
				return true;
			}
		}
		return false;
	}

	private boolean edgeContains(Edge<T> edge, T data, T data2) {
		if (edge.firstNode.nodeData.equals(data) && edge.secondNode.nodeData.equals(data2)) {
			return true;
		}
		else return edge.firstNode.nodeData.equals(data2) && edge.secondNode.nodeData.equals(data);
	}

	public int getCost(T node1, T node2) {
		for (Edge<T> edge : edgeArrayList) {
			if (edgeContains(edge, node1, node2)) {
				return edge.cost;
			}
		}
		return -1;
	}

	private boolean visitedAllAdjacent(T data) {
		for (T node : nodeHashMap.get(data).adjacentNodes) {
			if (!nodeHashMap.get(node).isVisited) {
				return false;
			}
		}
		return true;
	}

	public List<T> depthFirstSearch(T start, T end) {
		for (T node : nodeHashMap.keySet()) {
			nodeHashMap.get(node).isVisited = false; //börjar med att nollställa flaggor så alla är obesökta
		}

//		if (start == null) { //behövs ej(?) för det är alltid 1-6
//			return null;
//		}

		T data = start;

		if (!nodeHashMap.containsKey(start) && !nodeHashMap.containsKey(end)) {
			return null;
		}

		LinkedList<T> outcome = new LinkedList<>();
		Stack<T> stack = new Stack<>();
		stack.push(start);
		nodeHashMap.get(start).isVisited = true;

		if (start.equals(end)) {
			return stack;
		}
		while (!stack.isEmpty()) {
			if (visitedAllAdjacent(stack.peek()) && !stack.peek().equals(end)) {
				stack.pop();
			}
			else {
				for (T t : nodeHashMap.get(data).adjacentNodes) {
					if (!nodeHashMap.get(t).isVisited && isConnected(nodeHashMap.get(t).nodeData, nodeHashMap.get(data).nodeData)) {
						nodeHashMap.get(t).isVisited = true;
						stack.push(t);
					}
					data = stack.peek();
				}
			}
			if (stack.peek().equals(end)) {
				break;
			}
		}
		while (!stack.isEmpty()) {
			outcome.addFirst(stack.pop());
		}
		return outcome;
	}

}