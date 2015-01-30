import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BST<T extends Comparable<T>> implements BSTInterface<T> {

    private Node<T> root;
    private int size;
    private List<T> preorder;
    private List<T> postorder;
    private List<T> inorder;
    private List<T> levelorder;

    @Override
    public void add(T data) {
        root = addHelper(data, root);
        size++;
    }

    @Override
    public T remove(T data) {
        Node<T> temp = removeHelper(data, root);
        if (temp != null) {
            size--;
            root = temp;
            return temp.getData();
        }
        return null;
    }

    @Override
    public T get(T data) {
        Node<T> current = root;
        while (current != null) {
            int compare = data.compareTo(current.getData());
            if (compare < 0) {
                current = current.getLeft();
            } else if (compare > 0) {
                current = current.getRight();
            } else {
                return current.getData();
            }
        }
        return null;
    }

    @Override
    public boolean contains(T data) {
        return get(data) != null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public List<T> preorder() {
        preorder = new ArrayList<T>();
        preOrderHelp(root);
        return preorder;
    }

    @Override
    public List<T> postorder() {
        postorder = new ArrayList<T>();
        postOrderHelp(root);
        return postorder;
    }

    @Override
    public List<T> inorder() {
        inorder = new ArrayList<T>();
        inOrderHelp(root);
        return inorder;
    }

    @Override
    public List<T> levelorder() {
        levelorder = new ArrayList<T>();
        levelOrderHelp(root);
        return levelorder;
    }

    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    @Override
    public int height() {
        return findHeight(root);
    }

    @Override
    public Node<T> getRoot() {
        return root;
    }

    private Node<T> addHelper(T data, Node<T> current) {
        if (current == null) {
            return new Node<T>(data);
        } else if (data.compareTo(current.getData()) < 0) {
            current.setLeft(addHelper(data, current.getLeft()));
        } else {
            current.setRight(addHelper(data, current.getRight()));
        }
        return current;
    }

    private Node<T> removeHelper(T data, Node<T> current) {
        if (current == null) {
            return null;
        }
        int compare = data.compareTo(current.getData());
        if (compare == 0) {
            if (current.getRight() == null) {
                return current.getLeft();
            } else if (current.getLeft() == null) {
                return current.getRight();
            } else {
                current.setData(maxLeaf(current.getLeft()).getData());
                current.setLeft(removeHelper(current.getData(),
                        current.getLeft()));
            }
        } else {
            if (compare > 0) {
                current.setRight(removeHelper(data, current.getRight()));
            } else if (compare < 0) {
                current.setLeft(removeHelper(data, current.getLeft()));
            }

        }
        return current;
    }


    private Node<T> maxLeaf(Node<T> current) {
        while (current.getRight() != null) {
            current = current.getRight();
        }
        return current;
    }

    private int findHeight(Node<T> current) {
        if (current == null) {
            return -1;
        }
        int leftHeight = findHeight(current.getLeft());
        int rightHeight = findHeight(current.getRight());
        return (Math.max(leftHeight, rightHeight) + 1);
    }

    private void preOrderHelp(Node<T> current) {
        if (current != null) {
            preorder.add(current.getData());
            preOrderHelp(current.getLeft());
            preOrderHelp(current.getRight());
        }
    }

    private void postOrderHelp(Node<T> current) {
        if (current != null) {
            postOrderHelp(current.getLeft());
            postOrderHelp(current.getRight());
            postorder.add(current.getData());
        }
    }

    private void inOrderHelp(Node<T> current) {
        if (current != null) {
            inOrderHelp(current.getLeft());
            inorder.add(current.getData());
            inOrderHelp(current.getRight());
        }
    }

    private void levelOrderHelp(Node<T> current) {
        Queue<Node<T>> queue = new LinkedList<Node<T>>();
        if (current != null) {
            queue.add(current);
        }
        while (!queue.isEmpty()) {
            Node<T> next = queue.remove();
            levelorder.add(next.getData());
            if (next.getLeft() != null) {
                queue.add(next.getLeft());
            }
            if (next.getRight() != null) {
                queue.add(next.getRight());
            }
        }
    }


}