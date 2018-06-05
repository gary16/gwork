package com.gwork.app.algorithm.tree;

import java.util.LinkedList;
import java.util.List;

import com.gwork.app.algorithm.entity.House;

public class HouseNode extends BinaryTreeNode<House> {

	
	public HouseNode(House t) {
		super(t);
	}

	private int hittedSumFortune;
	
	private List<String> hittedChoiceList = new LinkedList<>();
	
	private List<String> unHittedChoiceList = new LinkedList<>();
	
	private int unhittedSumFortune;
	

	
	public void setLeftAndRight(HouseNode left,HouseNode right){
		this.setLeftNode(left);
		this.setRightNode(right);
	}
	
	public HouseNode getLeftNode() {
		return (HouseNode)super.getLeftNode();
	}

	public void setLeftNode(HouseNode leftNode) {
		super.setLeftNode(leftNode);
	}

	public HouseNode getRightNode() {
		return (HouseNode)super.getRightNode();
	}

	public void setRightNode(HouseNode rightNode) {
		super.setRightNode(rightNode);
	} 
	
	public int getHittedSumFortune() {
		return hittedSumFortune;
	}

	public void setHittedSumFortune(int hittedSumFortune) {
		this.hittedSumFortune = hittedSumFortune;
	}

	public int getUnhittedSumFortune() {
		return unhittedSumFortune;
	}

	public void setUnhittedSumFortune(int unhittedSumFortune) {
		this.unhittedSumFortune = unhittedSumFortune;
	}

	public List<String> getHittedChoiceList() {
		return hittedChoiceList;
	}

	public void setHittedChoiceList(List<String> hittedChoiceList) {
		this.hittedChoiceList = hittedChoiceList;
	}

	public List<String> getUnHittedChoiceList() {
		return unHittedChoiceList;
	}

	public void setUnHittedChoiceList(List<String> unHittedChoiceList) {
		this.unHittedChoiceList = unHittedChoiceList;
	}

	 
	
}
