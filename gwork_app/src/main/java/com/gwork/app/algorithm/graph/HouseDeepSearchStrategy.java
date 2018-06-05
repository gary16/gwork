package com.gwork.app.algorithm.graph;

import java.util.List;
import java.util.Map.Entry;
import java.util.SortedMap;
import java.util.TreeMap;

import com.gwork.app.algorithm.entity.House;
import com.gwork.app.algorithm.graph.Graph.GraphNode;

public class HouseDeepSearchStrategy extends SearchStrategy<House> {

	@Override
	public String getBestPath(Graph<House> directGraph, House srcObj, House destObj) {
		SortedMap<Integer, String> recordMap = new TreeMap<Integer, String>();
		this.doSearchBestPath(directGraph, srcObj, destObj, recordMap);
		for(Entry<Integer, String> tmp : recordMap.entrySet()){
			System.out.println(" the choosen one " + tmp.getValue());
			return tmp.getValue();
		}
		return "";
	}
	
	public String doSearchBestPath(Graph<House> directGraph, House srcObj, House destObj,SortedMap<Integer, String> recordMap){

		GraphNode<House> srcNode = directGraph.getNode(srcObj);
		GraphNode<House> destNode = directGraph.getNode(destObj);

		if (null == srcNode || null == destNode) {
			return "";
		}
		String bestPath = "";

		if (srcNode.equals(destNode)) {
			this.handleBestPathString(srcNode,recordMap);
		}

		List<GraphNode<House>> connList = srcNode.getRelatedNodes();
		for (GraphNode<House> tmp : connList) {
			if (checkIfExist(srcNode, tmp)) {
				continue;
			}
			tmp.setPreNode(srcNode);
			this.doSearchBestPath(directGraph, tmp.getT(), destObj,recordMap);
			tmp.removePreNode();
		}
		return bestPath;
	
	}

	private void handleBestPathString(GraphNode<House> oriNode, SortedMap<Integer, String> recordMap) {
		StringBuffer sb = new StringBuffer();
		int sum = 0;
		GraphNode<House> lastNode =null;
		while (null != oriNode) {
			sb.append(oriNode.getT().getName() + "<-");
			sum += oriNode.getPriority(lastNode);
			lastNode=oriNode;
			oriNode = oriNode.getPreNode();
		}
		String result = sb.toString().substring(0, sb.length() - 2);
		recordMap.put(sum, result);
		System.out.println(sum + " , " + result);
	}

	private boolean checkIfExist(GraphNode<House> oriNode, GraphNode<House> compareNode) {
		while (null != oriNode) {
			if (oriNode.equals(compareNode)) {
				return true;
			}
			oriNode = oriNode.getPreNode();
		}
		return false;
	}

	public static void main(String args[]) {
		Graph<House> graph = new Graph<House>();

		House a = new House("a", 12);
		House b = new House("b", 34);
		House c = new House("c", 56);
		House d = new House("d", 78);
		House e = new House("e", 11);
		House f = new House("f", 22);
		House g = new House("g", 33);
		House h = new House("h", 44);

		graph.addNode(a);
		graph.addNode(b);
		graph.addNode(c);
		graph.addNode(d);
		graph.addNode(e);
		graph.addNode(f);
		graph.addNode(g);
		graph.addNode(h);

		graph.connect(a, b, 10);
		graph.connect(a, e, 1);
		graph.connect(a, f, 10);

		graph.connect(b, a, 2);
		graph.connect(b, g, 3);
		graph.connect(b, e, 100);

		graph.connect(c, f, 78);
		graph.connect(c, d, 82);

		graph.connect(g, f, 67);

		graph.getBestPath(new HouseDeepSearchStrategy(), a, f);

	}
}
