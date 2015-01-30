import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * My AVL implementation.
 *
 * @author Jacqueline Zhu
 */
public class AVL<T extends Comparable<T>> implements AVLInterface<T>,
       Gradable<T> {

    // Do not add additional instance variables
    private Node<T> root;
    private int size;

    @Override
    public void add(T data) {
        if (data == null) {
            throw new IllegalArgumentException();
        }
        root = addHelper(data, root);
        size++;
    }

    @Override
    public T remove(T data) {
        if (data == null) {
            throw new IllegalArgumentException();
        }
        Node<T> newNode = new Node<T>(null);
        root = remove(root, data, newNode);
        return newNode.getData();
    }


    @Override
    public T get(T data) {
        if (data == null) {
            throw new IllegalArgumentException();
        }
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
        if (data == null) {
            throw new IllegalArgumentException();
        }
        return get(data) != null;
    }

    @Override
    public boolean isEmpty() {
        return root == null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public List<T> preorder() {
        List<T> preorder = new ArrayList<T>();
        preOrderHelp(root, preorder);
        return preorder;

    }

    @Override
    public List<T> postorder() {
        List<T> postorder = new ArrayList<T>();
        postOrderHelp(root, postorder);
        return postorder;
    }

    @Override
    public List<T> inorder() {
        List<T> inorder = new ArrayList<T>();
        inOrderHelp(root, inorder);
        return inorder;
    }

    @Override
    public List<T> levelorder() {
        List<T> levelorder = new ArrayList<T>();
        Queue<Node<T>> queue = new LinkedList<Node<T>>();
        if (root != null) {
            queue.add(root);
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

    /*
     * Compares the data to the current node, and if it's
     * greater than or less than, it calls the method
     * again on the right or left node, respectively.
     * @param data the data to compare
     * @param current the current Node the data is being compared to
     * @return new node to compare to
     */
    private Node<T> addHelper(T data, Node<T> current) {
        if (current == null) {
            return new Node<T>(data);
        }
        int compare = data.compareTo(current.getData());
        if (compare < 0) {
            current.setLeft(addHelper(data, current.getLeft()));
        } else if (compare > 0) {
            current.setRight(addHelper(data, current.getRight()));
        }
        return balance(current);
    }

    /*
     * Finds node and removes it
     * @param current node
     * @param data to remove
     * @param toReturn removed node
     * @return node to be removed if found, null if not found,
     *      or next node to compare data to
     */
    private Node<T> remove(Node<T> current, T data, Node<T> toReturn) {
        if (current == null) {
            return null;
        }
        hAndBF(current);
        if (data.compareTo(current.getData()) < 0) {
            current.setLeft(remove(current.getLeft(), data, toReturn));
        } else if (data.compareTo(current.getData()) > 0) {
            current.setRight(remove(current.getRight(), data, toReturn));
        } else {
            size--;
            toReturn.setData(current.getData());
            if (current.getLeft() != null && current.getRight() != null) {
                current.setData(twoChildren(current));
            } else if (current.getLeft() == null) {
                current = current.getRight();
            } else {
                current = current.getLeft();
            }

        }
        if (current == null) {
            return current;
        } else {
            return balance(current);
        }
    }

    /*
     * Calculates successor children
     * @param node that has a successor
     * @return removed data
     */
    private T twoChildren(Node<T> current) {
        Node<T> succ = current.getRight();
        Node<T> succChild = null;
        while (succ.getLeft() != null) {
            succChild = succ;
            succ = succ.getLeft();
        }
        T removed = succ.getData();
        if (succChild == null) {
            current.setRight(succ.getRight());
        } else {
            succChild.setLeft(succ.getRight());
        }
        return removed;
    }

    /*
     * Calculates and sets height and balance factor of given node
     * @param node to set height and balance factor of
     */
    private void hAndBF(Node<T> current) {
        int h = Math.max(findHeight(current.getLeft()),
                findHeight(current.getRight())) + 1;
        current.setHeight(h);
        int bf = findHeight(current.getLeft()) - findHeight(current.getRight());
        current.setBalanceFactor(bf);
    }

    /*
     * Finds height of node, accounts for node possibly being null
     * @param current node to find height of
     * @return int height, -1 if node is null
     */
    private int findHeight(Node<T> current) {
        if (current == null) {
            return -1;
        }
        return current.getHeight();
    }

    /*
     * Does a single rotation on left child.
     * @param b the unbalanced parent node
     * @return new balanced parent node
     */
    private Node<T> rotateWithLeft(Node<T> b) {
        Node<T> a = b.getLeft();
        b.setLeft(a.getRight());
        a.setRight(b);
        int bH = Math.max(findHeight(b.getLeft()),
                findHeight(b.getRight())) + 1;
        b.setHeight(bH);
        int aH = Math.max(findHeight(a.getLeft()), bH) + 1;
        a.setHeight(aH);
        hAndBF(a);
        hAndBF(b);
        return a;
    }

    /*
     * Does a double rotation on left child.
     * @param c the unbalanced parent node
     * @return new balanced parent node
     */
    private Node<T> doubleWithLeft(Node<T> c) {
        c.setLeft(rotateWithRight(c.getLeft()));
        hAndBF(c.getLeft());
        return rotateWithLeft(c);
    }

     /*
      * Does a single rotation on right child.
      * @param a the unbalanced parent node
      * @return new balanced parent node
      */
    private Node<T> rotateWithRight(Node<T> a) {
        Node<T> b = a.getRight();
        a.setRight(b.getLeft());
        b.setLeft(a);
        int aH = Math.max(findHeight(a.getLeft()),
                findHeight(a.getRight())) + 1;
        a.setHeight(aH);
        int bH = Math.max(findHeight(b.getRight()), aH) + 1;
        b.setHeight(bH);
        hAndBF(a);
        hAndBF(b);
        return b;
    }

     /*
      * Does a double rotation on right child.
      * @param a the unbalanced parent node
      * @return new balanced parent node
      */
    private Node<T> doubleWithRight(Node<T> a) {
        a.setRight(rotateWithLeft(a.getRight()));
        hAndBF(a.getRight());
        return rotateWithRight(a);
    }

     /*
      * Balances the tree if unbalanced using rotations
      * @param current the unbalanced parent node
      * @return new balanced parent node
      */
    private Node<T> balance(Node<T> current) {
        hAndBF(current);
        int balance = current.getBalanceFactor();
        if (balance > 1) {
            hAndBF(current.getLeft());
            if (current.getLeft().getBalanceFactor() < 0) {
                return doubleWithLeft(current);
            } else {
                return rotateWithLeft(current);
            }
        }
        if (balance < -1) {
            hAndBF(current.getRight());
            if (current.getRight().getBalanceFactor() <= 0) {
                return rotateWithRight(current);
            } else {
                return doubleWithRight(current);
            }
        }
        return current;
    }

     /*
      * Traverses through the tree in preorder, calls itself on
      * the left and right children of each node
      * @param current current node to traverse
      * @param list list order of traversal
      */
    private void preOrderHelp(Node<T> current, List<T> list) {
        if (current != null) {
            list.add(current.getData());
            preOrderHelp(current.getLeft(), list);
            preOrderHelp(current.getRight(), list);
        }
    }

     /*
      * Traverses through the tree in postorder, calls itself on
      * the left and right children of each node
      * @param current current node to traverse
      * @param list list order of traversal
      */
    private void postOrderHelp(Node<T> current, List<T> list) {
        if (current != null) {
            postOrderHelp(current.getLeft(), list);
            postOrderHelp(current.getRight(), list);
            list.add(current.getData());
        }
    }

     /*
      * Traverses through the tree in inorder, calls itself on
      * the left and right children of each node
      * @param current current node to traverse
      * @param list list order of traversal
      */
    private void inOrderHelp(Node<T> current, List<T> list) {
        if (current != null) {
            inOrderHelp(current.getLeft(), list);
            list.add(current.getData());
            inOrderHelp(current.getRight(), list);
        }
    }

}
