package com.gwork.app.algorithm.graph;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import com.gwork.app.algorithm.entity.BasicNode;

public class Graph<T> {

	private Map<GraphNode<T>, Integer> nodeMap = new ConcurrentHashMap<GraphNode<T>, Integer>();
	private ReadWriteLock myLock = new ReentrantReadWriteLock(false);

	private boolean isDirection = false;
	
	public Graph(){
	}
	
	public Graph(boolean isDirection){
		this.isDirection=isDirection;
	}
	
	public void addNode(T t) {
		try {
			myLock.writeLock().lock();
			this.nodeMap.put(new GraphNode<T>(t), 0);
		} finally {
			myLock.writeLock().unlock();
		}
	}

	public GraphNode<T> getNode(T t) {
		try {
			myLock.readLock().lock();
			for (GraphNode<T> tmp : this.nodeMap.keySet()) {
				if (tmp.getT().equals(t)) {
					return tmp;
				}
			}
			return null;
		} finally {
			myLock.readLock().unlock();
		}

	}

	public List<GraphNode<T>> getConnectionOfNode(T t) {
		try {
			myLock.readLock().lock();
			if (null == this.getNode(t)) {
				return null;
			}
			return this.getNode(t).getRelatedNodes();
		} finally {
			myLock.readLock().unlock();
		}
	}

	public void delNode(T t) {
		try {
			myLock.writeLock().lock();
			GraphNode<T> delNode = this.getNode(t);
			if (null == delNode) {
				return;
			}
			for (GraphNode<T> tmp : nodeMap.keySet()) {
				tmp.removeConnection(delNode);
			}
			this.nodeMap.remove(delNode);
		} finally {
			myLock.writeLock().unlock();
		}

	}

	public void connect(T srcObj, T destObj) {
		this.connect(srcObj, destObj, 0);
	}

	public void connect(T srcObj, T destObj, int priority) {
		try {
			myLock.writeLock().lock();
			GraphNode<T> src = this.getNode(srcObj);
			GraphNode<T> dest = this.getNode(destObj);
			if (null == src || null == dest) {
				return;
			}
			src.addConnection(dest, priority);
			if(!this.isDirection){
				dest.addConnection(src, priority);
			}
		} finally {
			myLock.writeLock().unlock();
		}
	}

	public void disconnect(T srcObj, T destObj) {
		try {
			myLock.writeLock().lock();
			GraphNode<T> src = this.getNode(srcObj);
			GraphNode<T> dest = this.getNode(destObj);
			if (null == src || null == dest) {
				return;
			}
			src.removeConnection(dest);
			if(!this.isDirection){
				dest.removeConnection(src);
			}
		} finally {
			myLock.writeLock().unlock();
		}

	}

	public String getBestPath(SearchStrategy<T> searchStrategy, T srcObj, T destObj) {
		try {
			myLock.readLock().lock();
			return searchStrategy.getBestPath(this, srcObj, destObj);
		} finally {
			myLock.readLock().unlock();
		}
	}

	public static class GraphNode<T> extends BasicNode<T> {

		private ThreadLocal<GraphNode<T>> preNodeThreadLocal = new ThreadLocal<GraphNode<T>>();

		private ConcurrentHashMap<GraphNode<T>, Integer> relatedNodes = new ConcurrentHashMap<GraphNode<T>, Integer>();

		public GraphNode<T> getPreNode() {
			return this.preNodeThreadLocal.get();
		}

		public void setPreNode(GraphNode graphNode) {
			this.preNodeThreadLocal.set(graphNode);
		}

		public void removePreNode() {
			this.preNodeThreadLocal.remove();
		}

		public int getPriority(GraphNode<T> srcNode) {
			if (null == srcNode) {
				return 0;
			}
			return this.relatedNodes.get(srcNode);
		}

		public GraphNode(T t) {
			super(t);
		}

		private void addConnection(GraphNode<T> srcNode, int priority) {
			relatedNodes.put(srcNode, priority);
		}

		private void removeConnection(GraphNode<T> srcNode) {
			relatedNodes.remove(srcNode);
		}

		public List<GraphNode<T>> getRelatedNodes() {
			return new ArrayList<GraphNode<T>>(relatedNodes.keySet());
		}

		@Override
		public int hashCode() {
			return this.getT().hashCode();
		}

		@Override
		public boolean equals(Object obj) {
			if (obj instanceof GraphNode) {
				GraphNode tmp = (GraphNode) obj;
				return this.getT().equals(tmp.getT());
			}
			return false;
		}

	}

}
