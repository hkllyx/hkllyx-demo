package com.hkllyx.demo.algorithm.graph.mst;

import com.hkllyx.demo.algorithm.graph.Edge;
import com.hkllyx.demo.algorithm.graph.Vertex;

import java.util.Set;

/**
 * @author xiaoyong3
 * @date 2023/02/16
 */
public abstract class MinimumSpanningTree {
    private final Set<Vertex> vertices;
    private final Set<Edge> edges;

    public MinimumSpanningTree(Set<Vertex> vertices, Set<Edge> edges) {
        this.vertices = vertices;
        this.edges = edges;
    }

    public abstract Set<Edge> mst();
}
