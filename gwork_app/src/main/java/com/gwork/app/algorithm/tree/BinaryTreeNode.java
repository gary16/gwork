package com.gwork.app.algorithm.tree;

import java.util.LinkedList;

import com.gwork.app.algorithm.entity.BasicNode;

public class BinaryTreeNode<T> extends BasicNode<T>{
	
	public BinaryTreeNode(T t) {
		super(t);
	}

	private BinaryTreeNode<T> leftNode;
	
	private BinaryTreeNode<T> rightNode;

	public BinaryTreeNode<T> getLeftNode() {
		return leftNode;
	}

	public void setLeftNode(BinaryTreeNode<T> leftNode) {
		this.leftNode = leftNode;
	}

	public BinaryTreeNode<T> getRightNode() {
		return rightNode;
	}

	public void setRightNode(BinaryTreeNode<T> rightNode) {
		this.rightNode = rightNode;
	}
	
	
	
	
}
