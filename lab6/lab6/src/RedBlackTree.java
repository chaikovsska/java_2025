@SuppressWarnings("ALL")
public class RedBlackTree<T extends Comparable<T>> {
    public Node<T> root;
    public final Node<T> TNULL;

    public RedBlackTree() {
        TNULL = new Node<>(null);
        TNULL.color = Color.BLACK;
        root = TNULL;
    }

    private void inOrderHelper(Node<T> node) {
        if (node != TNULL) {
            inOrderHelper(node.left);
            System.out.print(node.data + " ");
            inOrderHelper(node.right);
        }
    }

    public void inorder() {
        inOrderHelper(this.root);
    }

    private void leftRotate(Node<T> x) {
        Node<T> y = x.right;
        x.right = y.left;
        if (y.left != TNULL) {
            y.left.parent = x;
        }
        y.parent = x.parent;
        if (x.parent == null) {
            this.root = y;
        } else if (x == x.parent.left) {
            x.parent.left = y;
        } else {
            x.parent.right = y;
        }
        y.left = x;
        x.parent = y;
    }

    private void rightRotate(Node<T> x) {
        Node<T> y = x.left;
        x.left = y.right;
        if (y.right != TNULL) {
            y.right.parent = x;
        }
        y.parent = x.parent;
        if (x.parent == null) {
            this.root = y;
        } else if (x == x.parent.right) {
            x.parent.right = y;
        } else {
            x.parent.left = y;
        }
        y.right = x;
        x.parent = y;
    }

    public void insert(T key) {
        Node<T> node = new Node<>(key);
        node.left = TNULL;
        node.right = TNULL;
        node.color = Color.RED;

        Node<T> y = null;
        Node<T> x = this.root;

        while (x != TNULL) {
            y = x;
            if (node.data.compareTo(x.data) < 0) {
                x = x.left;
            } else {
                x = x.right;
            }
        }

        node.parent = y;
        if (y == null) {
            root = node;
        } else if (node.data.compareTo(y.data) < 0) {
            y.left = node;
        } else {
            y.right = node;
        }

        if (node.parent == null) {
            node.color = Color.BLACK;
            return;
        }

        if (node.parent.parent == null) {
            return;
        }

        fixInsert(node);
    }

    private void deleteNodeHelper(Node<T> node, T key) throws Exception {
        Node<T> z = TNULL;
        Node<T> x, y;
        while (node != TNULL) {
            if (node.data.equals(key)) {
                z = node;
            }
            if (node.data.compareTo(key) <= 0) {
                node = node.right;
            } else {
                node = node.left;
            }
        }

        if (z == TNULL) {
            throw new Exception("Element not found");
        }

        y = z;
        Color yOriginalColor = y.color;
        if (z.left == TNULL) {
            x = z.right;
            rbTransplant(z, z.right);
        } else if (z.right == TNULL) {
            x = z.left;
            rbTransplant(z, z.left);
        } else {
            y = minimum(z.right);
            yOriginalColor = y.color;
            x = y.right;
            if (y.parent == z) {
                x.parent = y;
            } else {
                rbTransplant(y, y.right);
                y.right = z.right;
                y.right.parent = y;
            }

            rbTransplant(z, y);
            y.left = z.left;
            y.left.parent = y;
            y.color = z.color;
        }
        if (yOriginalColor == Color.BLACK) {
            fixDelete(x);
        }
    }

    private void rbTransplant(Node<T> u, Node<T> v) {
        if (u.parent == null) {
            root = v;
        } else if (u == u.parent.left) {
            u.parent.left = v;
        } else {
            u.parent.right = v;
        }
        v.parent = u.parent;
    }

    private void fixDelete(Node<T> x) {
        Node<T> s;
        while (x != root && x.color == Color.BLACK) {
            if (x == x.parent.left) {
                s = x.parent.right;
                if (s.color == Color.RED) {
                    s.color = Color.BLACK;
                    x.parent.color = Color.RED;
                    leftRotate(x.parent);
                    s = x.parent.right;
                }

                if (s.left.color == Color.BLACK && s.right.color == Color.BLACK) {
                    s.color = Color.RED;
                    x = x.parent;
                } else {
                    if (s.right.color == Color.BLACK) {
                        s.left.color = Color.BLACK;
                        s.color = Color.RED;
                        rightRotate(s);
                        s = x.parent.right;
                    }

                    s.color = x.parent.color;
                    x.parent.color = Color.BLACK;
                    s.right.color = Color.BLACK;
                    leftRotate(x.parent);
                    x = root;
                }
            } else {
                s = x.parent.left;
                if (s.color == Color.RED) {
                    s.color = Color.BLACK;
                    x.parent.color = Color.RED;
                    rightRotate(x.parent);
                    s = x.parent.left;
                }

                if (s.right.color == Color.BLACK && s.left.color == Color.BLACK) {
                    s.color = Color.RED;
                    x = x.parent;
                } else {
                    if (s.left.color == Color.BLACK) {
                        s.right.color = Color.BLACK;
                        s.color = Color.RED;
                        leftRotate(s);
                        s = x.parent.left;
                    }

                    s.color = x.parent.color;
                    x.parent.color = Color.BLACK;
                    s.left.color = Color.BLACK;
                    rightRotate(x.parent);
                    x = root;
                }
            }
        }
        x.color = Color.BLACK;
    }

    private Node<T> minimum(Node<T> node) {
        while (node.left != TNULL) {
            node = node.left;
        }
        return node;
    }

    public void delete(T key) throws Exception {
        deleteNodeHelper(this.root, key);
    }

    private void fixInsert(Node<T> k) {
        Node<T> u;
        while (k.parent.color == Color.RED) {
            if (k.parent == k.parent.parent.right) {
                u = k.parent.parent.left;
                if (u.color == Color.RED) {
                    u.color = Color.BLACK;
                    k.parent.color = Color.BLACK;
                    k.parent.parent.color = Color.RED;
                    k = k.parent.parent;
                } else {
                    if (k == k.parent.left) {
                        k = k.parent;
                        rightRotate(k);
                    }
                    k.parent.color = Color.BLACK;
                    k.parent.parent.color = Color.RED;
                    leftRotate(k.parent.parent);
                }
            } else {
                u = k.parent.parent.right;

                if (u.color == Color.RED) {
                    u.color = Color.BLACK;
                    k.parent.color = Color.BLACK;
                    k.parent.parent.color = Color.RED;
                    k = k.parent.parent;
                } else {
                    if (k == k.parent.right) {
                        k = k.parent;
                        leftRotate(k);
                    }
                    k.parent.color = Color.BLACK;
                    k.parent.parent.color = Color.RED;
                    rightRotate(k.parent.parent);
                }
            }
            if (k == root) {
                break;
            }
        }
        root.color = Color.BLACK;
    }

    public void displayTree() {
        displayHelper(root, "", true);
    }

    private void displayHelper(Node<T> node, String indent, boolean isRight) {
        if (node != TNULL) {
            char right = node == root ? '*' : isRight ? 'R' : 'L';

            char color = node.color == Color.RED ? 'R' : 'B';
            System.out.println(indent + (right + " --- ") + node.data + "(" + color + ")");
            displayHelper(node.left, indent + (isRight ? "     " : "|    "), false);
            displayHelper(node.right, indent + (isRight ? "     " : "|    "), true);
        }
    }
}

