/**
 * StacksQueue
 *
 * Implementation of a queue using
 * two stacks as the backing store.
 *
 * @author Jacqueline Zhu
 * @version 1.0
 */
public class StacksQueue<T> implements QueueInterface<T> {

    // TODO Add any private variables you need.
    // HINT Use one stack as the "enqueue" stack
    //      and one stack as the "dequeue" stack.
	
	private ArrayStack<T> in;
	private ArrayStack<T> out;
	
	
	public StacksQueue() {
        in = new ArrayStack<T>();
        out = new ArrayStack<T>();
    }
	
	
    @SuppressWarnings("unchecked")
	@Override
    public void enqueue(Object o) {
    	in.push((T) o);
    }

    @Override
    public T dequeue() {
        if (out.isEmpty()) {
        	if (in.isEmpty()) {
        		return null;
        	}
        	while (!in.isEmpty()) {
        		out.push(in.pop());
        	}
        }
        return out.pop();
    }

    @Override
    public boolean isEmpty() {
        return in.isEmpty() && out.isEmpty();
    }
}
