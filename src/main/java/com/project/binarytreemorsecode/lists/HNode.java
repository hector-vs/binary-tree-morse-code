package com.project.binarytreemorsecode.lists;

public class HNode<H> {
    protected H element;
    protected HNode<H> right;
    protected HNode<H> left;

    public HNode(H element) {
        this.element = element;
    }

    public H getElement () {
        return this.element;
    }

    public HNode<H> getRight () {
        return this.right;
    }

    public void setRight (HNode<H> right) {
        this.right = right;
    }

    public HNode<H> getLeft () {
        return this.left;
    }

    public void setLeft (HNode<H> left) {
        this.left = left;
    }
}