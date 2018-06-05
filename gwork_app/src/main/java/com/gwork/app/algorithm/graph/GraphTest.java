package com.gwork.app.algorithm.graph;

import com.alibaba.fastjson.JSON;
import com.gwork.app.algorithm.entity.House;
import com.gwork.app.algorithm.graph.Graph.GraphNode;

public class GraphTest {
	
	public static void main(String args[]){
		Graph<House> graph = new Graph<House>();
		House a = new House("a", 12);
		House b = new House("b", 34);
		House c = new House("c", 56);
		House d = new House("d", 78);
		
		
		graph.addNode(a);
		graph.addNode(b);
		graph.addNode(c);
		graph.addNode(d);
		
		graph.connect(a,c);
		graph.connect(a,b);
		
		
		GraphNode tmp = graph.getNode(d);
		graph.getConnectionOfNode(a).add(tmp);
		
		System.out.println(JSON.toJSONString(graph.getNode(a)));
		System.out.println("\n");
		graph.connect(a,d);
		System.out.println(JSON.toJSONString(graph.getNode(a)));
		
	}
	
}
