package com.gwork.app.algorithm.graph;

import com.gwork.app.algorithm.graph.Graph.GraphNode;

public abstract class SearchStrategy<T> {

	public abstract String getBestPath(Graph<T> directGraph,T srcObj,T destObj);


}
