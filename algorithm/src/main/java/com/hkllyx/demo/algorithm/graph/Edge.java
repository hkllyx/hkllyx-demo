package com.hkllyx.demo.algorithm.graph;

/**
 * @author xiaoyong3
 * @date 2023/02/16
 */
public class Edge {
    final Vertex vertex1;
    final Vertex vertex2;
    final int weight;

    public Edge(Vertex vertex1, Vertex vertex2, int weight) {
        this.vertex1 = vertex1;
        this.vertex2 = vertex2;
        this.weight = weight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Edge edge = (Edge) o;
        if (!vertex1.equals(edge.vertex1)) {
            return false;
        }
        return vertex2.equals(edge.vertex2);
    }

    @Override
    public int hashCode() {
        int result = vertex1.hashCode();
        result = 31 * result + vertex2.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "E(" + vertex1.id + "," + vertex2.id + ')';
    }
}
