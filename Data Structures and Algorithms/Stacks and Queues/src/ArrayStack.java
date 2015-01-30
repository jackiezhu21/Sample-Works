/**
 * ArrayStack
 *
 * Implementation of a stack using an array
 * as the backing store.
 *
 * @author Jacqueline Zhu
 * @version 1.0
 */
public class ArrayStack<T> implements StackInterface<T> {

    private static final int STACK_SIZE = 200;
    //TODO Add any private variables you may need.
    //HINT Use an object array and something to keep track
    //     of what the next available slot or last added index is.
    
    private T[] stack;
    private int size;

    @SuppressWarnings("unchecked")
	public ArrayStack() {
        stack = (T[]) new Object[STACK_SIZE];
        size = 0;
    }

    @Override
    public void push(T t) {
    	if (t == null) {
    		throw new IllegalArgumentException();
    	}
    	if (size == STACK_SIZE) {
    		throw new RuntimeException();
    	}
    	stack[size] = t;
    	size++;
    }

    @Override
    public T pop() {
    	if (size == 0) {
    		return null;
    	}
        T removed = stack[size - 1];
        stack[size - 1] = null;
        size--;
        return removed;
    }

    @Override
    public T[] toArray() {
        return stack;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }
}
