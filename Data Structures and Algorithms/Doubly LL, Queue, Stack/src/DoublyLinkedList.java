/**
 * Doubly linked list implementation
 *
 * @author Jacqueline Zhu
 * @version 1.0
 */
public class DoublyLinkedList<T> implements LinkedListInterface<T> {

    private int size;
    private Node<T> head;
    private Node<T> tail;

    @Override
    public void addAtIndex(int index, T data) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        } else if (index == 0) {
            addToFront(data);
        } else if (index == size) {
            addToBack(data);
        } else {
            Node<T> newNode = new Node<T>(data);
            Node<T> current = head;
            for (int i = 0; i < index - 1; i++) {
                current = current.getNext();
            }
            newNode.setNext(current.getNext());
            current.getNext().setPrevious(newNode);
            newNode.setPrevious(current);
            current.setNext(newNode);
            size++;
        }
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        if (index == 0) {
            return head.getData();
        } else if (index == size - 1) {
            return tail.getData();
        } else {
            Node<T> current = head;
            for (int i = 0; i < index; i++) {
                current = current.getNext();
            }
            return current.getData();
        }
    }

    @Override
    public T removeAtIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        } else if (size == 0) {
            return null;
        } else if (index == 0) {
            return removeFromFront();
        } else if (index == size - 1) {
            return removeFromBack();
        } else {
            Node<T> current = head;
            for (int i = 0; i < index; i++) {
                current = current.getNext();
            }
            T removed = current.getData();
            Node<T> temp = current.getNext();
            temp.setPrevious(current.getPrevious());
            current.getPrevious().setNext(temp);

            size--;
            return removed;
        }
    }

    @Override
    public void addToFront(T t) {
        Node<T> newNode = new Node<T>(t);
        if (size == 0) {
            tail = newNode;
        } else {
            head.setPrevious(newNode);
        }
        newNode.setNext(head);
        head = newNode;
        size++;
    }

    @Override
    public void addToBack(T t) {
        Node<T> newNode = new Node<T>(t);
        if (size == 0) {
            head = newNode;
        } else {
            tail.setNext(newNode);
            newNode.setPrevious(tail);
        }
        tail = newNode;
        size++;
    }

    @Override
    public T removeFromFront() {
        if (size == 0) {
            return null;
        }
        T removed = head.getData();
        if (size == 1) {
            head = null;
            tail = null;
        } else {
            head = head.getNext();
            head.setPrevious(null);
        }
        size--;
        return removed;
    }

    @Override
    public T removeFromBack() {
        if (size == 0) {
            return null;
        }
        T removed = tail.getData();
        if (size == 1) {
            head = null;
            tail = null;
        } else {
            tail = tail.getPrevious();
            tail.setNext(null);
        }
        size--;
        return removed;
    }

    @Override
    public Object[] toArray() {
        Object[] array;
        if (size == 0) {
            array = new Object[0];
        } else {
            array = new Object[size];
            Node<T> current = head;
            for (int i = 0; i < size; i++) {
                array[i] = current.getData();
                current = current.getNext();
            }
        }
        return array;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        head = null;
        tail = null;
        size = 0;
    }

    /**
     * Reference to the head node of the linked list. Normally, you would not do
     * this, but we need it for grading your work.
     *
     * You will get a 0 if you do not implement this method.
     *
     * @return Node representing the head of the linked list
     */
    @Override
    public Node<T> getHead() {
        return head;
    }

    /**
     * Reference to the tail node of the linked list. Normally, you would not do
     * this, but we need it for grading your work.
     *
     * You will get a 0 if you do not implement this method.
     *
     * @return Node representing the tail of the linked list
     */
    @Override
    public Node<T> getTail() {
        return tail;
    }
}
