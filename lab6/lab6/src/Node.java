class Node<T extends Comparable<T>> {
    T data;
    Node<T> left;
    Node<T> right;
    Node<T> parent;
    Color color;

    Node(T data) {
        this.data = data;

        this.color = Color.RED;
        this.left = null;
        this.right = null;
        this.parent = null;
    }
}
