package com.hkllyx.demo.algorithm.graph;

import java.util.Objects;

/**
 * @author xiaoyong3
 * @date 2023/02/16
 */
public class Vertex {
    final String id;

    public Vertex(String id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Vertex vertex = (Vertex) o;
        return Objects.equals(id, vertex.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "V(" + id + ')';
    }
}
