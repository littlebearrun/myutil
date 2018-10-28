package com.likang.myutil.util.node;

import java.util.ArrayDeque;
import java.util.Queue;
/**
 *                   	9
 *                   /	   \	
 *                  1 	 	3
 *                /  \	   /	
 *               2	 4	  8
 *              / \ 
 *             6   7	  
 *
 */

public class CreateTree {

	
	// 中序遍历
	public static void SelectTree(Node root) {
		if (root == null)
			return;
		SelectTree(root.left);
		System.out.print(root.data + " ");
		SelectTree(root.right);
	}

	// 先序遍历
	public static void SelectTree1(Node root) {
		if (root == null)
			return;
		System.out.print(root.data + " ");
		SelectTree1(root.left);
		SelectTree1(root.right);
	}

	// 后序遍历
	public static void SelectTree2(Node root) {
		if (root == null)
			return;
		SelectTree2(root.left);
		SelectTree2(root.right);
		System.out.print(root.data + " ");

	}

	// 叶子数
	public static int leafNum(Node node) {
		if (node != null) {
			if (node.left == null && node.right == null) {
				return 1;
			}
			return leafNum(node.left) + leafNum(node.right);
		}
		return 0;
	}

	//求二叉树的深度 
	public static int deep(Node node) {
		int h1, h2;
		if (node == null) {
			return 0;
		} else {
			h1 = deep(node.left);
			h2 = deep(node.right);
			return (h1 < h2) ? h2 + 1 : h1 + 1;
		}

	}

	// 层次遍历 
	public static void levelOrder(Node node) {
		if (node == null)
			return;
		Queue<Node> queue = new ArrayDeque<Node>();
		queue.add(node);
		while (!queue.isEmpty()) {
			Node temp = queue.poll();
			System.out.print(temp.data);
			if (temp.left != null)
				queue.add(temp.left);
			if (temp.right != null)
				queue.add(temp.right);
		}
	}
	
	public static void main(String[] args) {

		Node root = new Node();
		root.data = 9;

		Node temp01 = new Node();
		temp01.data = 1;
		root.left = temp01;

		Node temp02 = new Node();
		temp02.data = 3;
		root.right = temp02;

		Node temp03 = new Node();
		temp03.data = 2;
		root.left.left = temp03;

		Node temp04 = new Node();
		temp04.data = 4;
		root.left.right = temp04;

		Node temp05 = new Node();
		temp05.data = 8;
		root.right.left = temp05;

		Node temp06 = new Node();
		temp06.data = 6;
		root.left.left.left = temp06;

		Node temp07 = new Node();
		temp07.data = 7;
		root.left.left.right = temp07;

		System.out.println("--------先序遍历----------");
		SelectTree1(root);
		System.out.println();
		System.out.println("---------中序遍历---------");
		SelectTree(root);
		System.out.println();
		System.out.println("---------后序遍历---------");
		SelectTree2(root);
		System.out.println();
		System.out.println("----------叶节点个数-----------");
		int i = leafNum(root);
		System.out.println(i);
		System.out.println("----------层次遍历二叉树-----------------");
		levelOrder(root);

		System.out.println();
		int j = deep(root);
		System.out.println("---------高度---------");
		System.out.println(j);
	}

	
	
	
}

class Node {
	boolean visited = false;
	int data = 0;
	Node left = null;
	Node right = null;
}