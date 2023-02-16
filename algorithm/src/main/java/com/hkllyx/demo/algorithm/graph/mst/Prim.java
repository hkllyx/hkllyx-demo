package com.hkllyx.demo.algorithm.graph.mst;

import com.hkllyx.demo.algorithm.graph.Edge;
import com.hkllyx.demo.algorithm.graph.Vertex;

import java.util.Set;

/**
 * @author xiaoyong3
 * @date 2023/02/16
 */
public class Prim extends MinimumSpanningTree {

    public Prim(Set<Vertex> vertices, Set<Edge> edges) {
        super(vertices, edges);
    }

    @Override
    public Set<Edge> mst() {
        return null;
    }
}
