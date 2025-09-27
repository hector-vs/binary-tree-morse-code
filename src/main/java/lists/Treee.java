package lists;

public class Treee {
    HNode<String> root;
    java.lang.String oi;

    private HNode<String> search(HNode<String> root, String element) {
        if (root.element.equals(element)) {
            return root;
        }
        if (root.left != null) {
            return search(root.left, element);
        }
        if (root.right != null) {
            return search(root.right, element);
        }
        return null;
    }

    private boolean erase(String element) {
        HNode<String> node = search(this.root, element);
        if(node != null) {
            node.element = null;
            return true;
        }
        return false;

    }

    public void insert (String letter, String code) {
        if (this.isEmpty()) {
            this.root = new HNode<String>(null);
        }

        this.erase(letter);



    }

    public void clear() {
        this.root = null;
    }

    private boolean isEmpty() {
        return this.root == null;
    }

}

