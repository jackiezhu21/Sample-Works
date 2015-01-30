import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * This is a class that will allow you to iterate through the first n
 * Fibonacci elements
 * @author kushagramansingh
 *
 */
public class FibonacciIterator implements Iterator<Integer> {
	private Integer n;
	private Integer current;
	private Integer runningValue = 1;
	private Integer previousValue = 0;

	public FibonacciIterator(Integer n) {
	    this.n = n;
	    current = 1;
	}

	@Override
	public boolean hasNext() {
		return current < n;
	}

	@Override
	public Integer next() {
	    if (!hasNext()) {
	        throw new NoSuchElementException();
	    }

	    int result = runningValue + previousValue;
	    previousValue = runningValue;
	    runningValue = result;
	    current++;
		return runningValue;
	}
//    public static void main (String[]args) {
//        FibonacciIterator test = new FibonacciIterator(7);
//        while (test.hasNext()) {
//            System.out.println(test.next());
//        }
//    }
}
