package com.gwork.app.algorithm.tree;

import java.util.LinkedList;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.gwork.app.algorithm.entity.House;

public class BestStealHousePath {

	public void getMaxForTune(HouseNode rootNode) {

		if (null == rootNode) {
			return;
		}
		this.getMaxForTune(rootNode.getLeftNode());
		this.getMaxForTune(rootNode.getRightNode());
		
		int hittedSum,unhittedSum,hittedLeft,unhittedLeft,hittedRight,unhittedRight;
		hittedSum=unhittedSum=0;
		
		hittedLeft =(null!=rootNode.getLeftNode())?rootNode.getLeftNode().getHittedSumFortune():0;
		unhittedLeft=(null!=rootNode.getLeftNode())?rootNode.getLeftNode().getUnhittedSumFortune():0;
		
		hittedRight = (null!=rootNode.getRightNode())?rootNode.getRightNode().getHittedSumFortune():0;
		unhittedRight = (null!=rootNode.getRightNode())?rootNode.getRightNode().getUnhittedSumFortune():0;
		
		// 1 0 0
		hittedSum=rootNode.getT().getFortune()+unhittedLeft+unhittedRight;
		rootNode.setHittedSumFortune(hittedSum);
		rootNode.setHittedChoiceList(this.fillList(rootNode, 1, 0, 0));
		
		
		// 0 1 1
		unhittedSum = hittedLeft+hittedRight;
		List<String>  tmpList = this.fillList(rootNode, 0, 1, 1);
		// 0 0 1
		if((unhittedLeft+hittedRight)>unhittedSum){
			unhittedSum = (unhittedLeft+hittedRight);
			tmpList = this.fillList(rootNode, 0, 0, 1);
		}
		// 0 1 0
		if((hittedLeft+unhittedRight)>unhittedSum){
			unhittedSum = (hittedLeft+unhittedRight);
			tmpList = this.fillList(rootNode, 0, 1,0);
		}
		// 0 0 0
		if((unhittedLeft+unhittedRight)>unhittedSum){
			unhittedSum = (unhittedLeft+unhittedRight);
			tmpList = this.fillList(rootNode, 0,0,0);
		}
		
		rootNode.setUnhittedSumFortune(unhittedSum);
		rootNode.setUnHittedChoiceList(tmpList);
	}
	
	private List<String> fillList(HouseNode rootNode,int currFlag,int leftFlag,int rightFlag){
		List<String> tmpList = new LinkedList<>();
	    if(currFlag==1){
	    	tmpList.add(rootNode.getT().getName());
	    }
	    if(null!=rootNode.getLeftNode()){
	    	if(leftFlag==1){
	    		tmpList.addAll(rootNode.getLeftNode().getHittedChoiceList());
	    	}
	    	else{
	    		tmpList.addAll(rootNode.getLeftNode().getUnHittedChoiceList());
	    	}
	    }
	    if(null!=rootNode.getRightNode()){
	    	if(rightFlag==1){
	    		tmpList.addAll(rootNode.getRightNode().getHittedChoiceList());
	    	}
	    	else{
	    		tmpList.addAll(rootNode.getRightNode().getUnHittedChoiceList());
	    	}
	    }
	    return tmpList;
	}

	public static void main(String args[]) {
		
		//HouseNode tmp =assemble1();
		HouseNode tmp =assemble2();
		BestStealHousePath go = new BestStealHousePath();
		go.getMaxForTune(tmp);
		System.out.println(tmp.getHittedSumFortune() +";"+tmp.getUnhittedSumFortune());
		System.out.println(JSON.toJSONString(tmp.getHittedChoiceList()));
		System.out.println(JSON.toJSONString(tmp.getUnHittedChoiceList()));
	}
	
	public static HouseNode assemble1(){
		HouseNode a = new HouseNode(new House("a",1));
		HouseNode b = new HouseNode(new House("b",9));
		HouseNode c = new HouseNode(new House("c",9));
		HouseNode d = new HouseNode(new House("d",1));
		HouseNode e = new HouseNode(new House("e",1));
		HouseNode f = new HouseNode(new House("f",1));
		HouseNode g = new HouseNode(new House("g",1));
		
		a.setLeftAndRight(b, c);
		b.setLeftAndRight(d, e);
		c.setLeftAndRight(f, g);
		return a;
	}
	
	public static HouseNode assemble2(){
		HouseNode a = new HouseNode(new House("a",1));
		HouseNode b = new HouseNode(new House("b",0));
		HouseNode c = new HouseNode(new House("c",0));
		HouseNode d = new HouseNode(new House("d",0));
		HouseNode e = new HouseNode(new House("e",0));
		HouseNode f = new HouseNode(new House("f",0));
		HouseNode g = new HouseNode(new House("g",0));
		HouseNode h = new HouseNode(new House("h",1));
		HouseNode i = new HouseNode(new House("i",1));
		HouseNode j = new HouseNode(new House("j",1));
		HouseNode k = new HouseNode(new House("k",1));
		HouseNode l = new HouseNode(new House("l",1));
		HouseNode m = new HouseNode(new House("m",1));
		HouseNode n = new HouseNode(new House("n",1));
		HouseNode o = new HouseNode(new House("o",1));
		
		a.setLeftAndRight(b, c);
		b.setLeftAndRight(d, e);
		c.setLeftAndRight(f, g);
		d.setLeftAndRight(h,i);
		e.setLeftAndRight(j, k);
		f.setLeftAndRight(l,m);
		g.setLeftAndRight(n, o);
		return a;
	}

}
