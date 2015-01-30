/**
 * CircularLinkedList implementation
 * @author Jacqueline Zhu
 * @version 1.0
 */

public class CircularLinkedList<T> implements LinkedListInterface<T> {
	private Node<T> head;
	private int size;
	private Node<T> tail;
	
	public CircularLinkedList() {
		head = null;
		tail = null;
		size = 0;
	}

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
	        Node<T> temp = current.getNext();
	        current.setNext(newNode);
	        newNode.setNext(temp); 
	        size++;
        }
    }

    @Override
    public T get(int index) {
    	if (index < 0 || index >= size) {
    		throw new IndexOutOfBoundsException();
	    } else if (index == 0) {
	    	return head.getData();
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
        } else if (size == 1 && index == 0) {
    		T temp = head.getData();
    		head = null;
    		tail = null;
    		size = 0;
    		return temp;
    	} else if (index == 0) {
    		return removeFromFront();
//    		T temp = head.getdata();
//    		head = head.getnext();
//    		tail.setnext(head);
//    		size--;
//    		return temp;
    	} else if (index == (size - 1)) {
    		return removeFromBack();
//    		Node<T> tempH = head;
//    		Node<T> tempT = head;
//    		while (tempH != tail) {
//    			tempT = tempH;
//    			tempH = tempH.getNext();
//    		}
//    		T temp = tail.getData();
//    		tail = tempT;
//    		tail.setNext(head);
//    		size--;
//    		return temp;
    	} else {
    		Node<T> current = head;
    		for (int i = 0; i < index - 1; i++) {
    			current = current.getNext();
    		}
    		Node<T> removed = current.getNext();
    		current.setNext(removed.getNext());
    		size--;
    		return removed.getData();
    	}
    }

    @Override
    public void addToFront(T t) {
        Node<T> newNode = new Node<T>(t);
        if (null == head) {
        	head = newNode;
        	newNode.setNext(head);
        	tail = head;
        } else {
        	newNode.setNext(head);
        	head = newNode;
        	tail.setNext(head);
        }
        size++;
    }

    @Override
    public void addToBack(T t) {
        Node<T> newNode = new Node<T>(t, head);
        if (null == head) {
        	head = newNode;
        	newNode.setNext(head);
        	tail = head;
        } else {
        	tail.setNext(newNode);
        	tail = newNode;
        }
        size++;
    }

    @Override
    public T removeFromFront() {
    	if (size == 0) {
    		return null;
    	} else {
    	Node<T> removed = head;
        tail.setNext(head.getNext());
        head = head.getNext();
        size--;
        return removed.getData();
    	}
    }

    @Override
    public T removeFromBack() {
    	if (size == 0) {
    		return null;
    	} else {
    	Node<T> current = head;
    	for (int i = 0; i < size - 2; i++) {
    		current = current.getNext();
    	}
    	T removed = current.getNext().getData();
    	tail = current;
    	tail.setNext(head);
    	size--;
    	return removed;
    	}
    }

    @SuppressWarnings("unchecked")
	@Override
    public T[] toList() {
    	Object[] contents;
        if (size == 0) {
        	contents = new Object[0];
        } else {
        	Node<T> current = head;
        	contents = new Object[size];
        	for (int i = 0; i < size; i++) {
        		contents[i] = current.getData();
        		current = current.getNext();
        	}
        }
        return (T[]) contents;
    }

    @Override
    public boolean isEmpty() {
        if (size == 0) {
        	return true;
        }
        return false;
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
     * Reference to the head node of the linked list.
     * Normally, you would not do this, but we need it
     * for grading your work.
     *
     * @return Node representing the head of the linked list
     */
    public Node<T> getHead() {
        return head;
    }

    /**
     * Reference to the tail node of the linked list.
     * Normally, you would not do this, but we need it
     * for grading your work.
     *
     * @return Node representing the tail of the linked list
     */
    public Node<T> getTail() {
        return tail;
    }

    /**
     * This method is for your testing purposes.
     * You may choose to implement it if you wish.
     */
    @Override
    public String toString() {
    	
        return "";
    }
}

