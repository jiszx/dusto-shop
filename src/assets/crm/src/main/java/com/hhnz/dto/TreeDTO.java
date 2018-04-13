package com.hhnz.dto;

import java.util.List;

/**
 * Created by yang on 2016-6-29.
 */
public class TreeDTO<T> {
    private T node;
    private List<T> nodes;

    public List<T> getNodes() {
        return nodes;
    }

    public void setNodes(List<T> nodes) {
        this.nodes = nodes;
    }

    public T getNode() {
        return node;
    }

    public void setNode(T node) {
        this.node = node;
    }
}
