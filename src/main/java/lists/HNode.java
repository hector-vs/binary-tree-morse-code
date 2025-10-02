package lists;

public class HNode<H> {
    private H element;
    private HNode<H> right;
    private HNode<H> left;

    public HNode(H element) {
        this.element = element;
    }

    public HNode() {}

    public H getElement () {
        return this.element;
    }

    public void setElement(H element) {
        this.element = element;
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
