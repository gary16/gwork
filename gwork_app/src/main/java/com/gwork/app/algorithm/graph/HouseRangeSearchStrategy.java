package com.gwork.app.algorithm.graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import org.apache.commons.collections.CollectionUtils;

import com.gwork.app.algorithm.entity.House;
import com.gwork.app.algorithm.graph.Graph.GraphNode;

public class HouseRangeSearchStrategy extends SearchStrategy<House> {

	@Override
	public String getBestPath(Graph<House> directGraph, House srcObj,House destObj) {
		GraphNode<House> srcNode = directGraph.getNode(srcObj);
		GraphNode<House> destNode = directGraph.getNode(destObj);

		if (null == srcNode || null == destNode) {
			return "";
		}
		String bestPath = "";
		Queue<GraphNode> needHandleList = new LinkedList<>();
		List<GraphNode> needRemoveList = new ArrayList<>();
		needHandleList.add(srcNode);
		needRemoveList.add(srcNode);
		while (!needHandleList.isEmpty()) {
			GraphNode<House> rootNode = needHandleList.poll();
			List<GraphNode<House>> children = rootNode.getRelatedNodes();
			if (rootNode.getT().equals(destObj)) {
				bestPath = this.handleBestPathString(rootNode);
				break;
			}
			if (CollectionUtils.isNotEmpty(children)) {
				for (GraphNode tmpNode : children) {
					if (this.checkIfExist(rootNode, tmpNode)) {
						continue;
					} else {
						tmpNode.setPreNode(rootNode);
						needHandleList.add(tmpNode);
						needRemoveList.add(tmpNode);
					}
				}
			}
		}

		this.removeThreadLocal(needRemoveList);
		return bestPath;
	}

	private void removeThreadLocal(List<GraphNode> srcList) {
		if (!CollectionUtils.isEmpty(srcList)) {
			for (GraphNode tmpNode : srcList) {
				tmpNode.removePreNode();
			}
		}
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

	private String handleBestPathString(GraphNode<House> oriNode) {
		StringBuffer sb = new StringBuffer();
		while (null != oriNode) {
			sb.append(oriNode.getT().getName() + "<-");
			oriNode = oriNode.getPreNode();
		}
		System.out.println(sb.toString().substring(0, sb.length() - 2));
		return sb.toString().substring(0, sb.length() - 2);
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

		graph.connect(a, b);
		graph.connect(a, e);
		graph.connect(b, a);
		graph.connect(b, g);
		graph.connect(b, e);
		graph.connect(c, f);
		graph.connect(c, d);
		graph.connect(g, f);

		graph.getBestPath(new HouseRangeSearchStrategy(), a, f);

	}

}
