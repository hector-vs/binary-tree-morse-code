package lists;

import java.util.Objects;

public class Treee {
    HNode<String> root;

    char left = '.';
    char right = '_';

    private boolean stringEquals(String a, String b) {
        return a == null ? b == null : a.equals(b);
    }

    private HNode<String> search(HNode<String> root, String element) {
        if (stringEquals(root.getElement(), element)) {
            return root;
        }
        if (root.getLeft() != null) {
            return search(root.getLeft(), element);
        }
        if (root.getRight() != null) {
            return search(root.getRight(), element);
        }
        return null;
    }

    private String search(HNode<String> root, String element, String code) {
        if (root == null) return null;
        if (stringEquals(root.getElement(), element)) {
            return code;
        }
        String leftCode = search(root.getLeft(), element, code + left);
        if (leftCode != null) return leftCode;
        return search(root.getRight(), element, code + right);
    }

    private boolean erase(String element) {
        HNode<String> node = search(this.root, element);
        if (node != null) {
            node.setElement(null);
            return true;
        }
        return false;
    }

    public void insert(String letter, String code) throws Exception {
        if (this.isEmpty()) {
            this.root = new HNode<String>(null);
        }

        this.erase(letter);

        this.insert(letter, code, this.root);
    }

    private void insert(String letter, String code, HNode<String> node) throws Exception {
        char firstCode = code.charAt(0);
        String restCode = code.length() > 1 ? code.substring(1) : "";

        if (firstCode != left && firstCode != right) {
            throw new Exception("Invalid code");
        }

        if (firstCode == right) {
            if (node.getRight() == null) {
                node.setRight(new HNode<>());
            }

            if (restCode.isEmpty()) {
                node.getRight().setElement(letter);
            } else {
                insert(letter, restCode, node.getRight());
            }
        } else {
            if (node.getLeft() == null) {
                node.setLeft(new HNode<>());
            }

            if (restCode.isEmpty()) {
                node.getLeft().setElement(letter);
            } else {
                insert(letter, restCode, node.getLeft());
            }
        }
    }

    public String encode(String text) throws Exception {
        if (this.isEmpty()) {
            throw new Exception("THE ARVORE IS EMPTY");
        }

        char[] letters = text.toCharArray();
        String finalCode = "";

        for (char letter : letters) {
            String code = search(this.root, String.valueOf(letter), "");
            finalCode += code + " ";
        }

        return finalCode;
    }

    public void clear() {
        this.root = null;
    }

    private boolean isEmpty() {
        return this.root == null;
    }

}

