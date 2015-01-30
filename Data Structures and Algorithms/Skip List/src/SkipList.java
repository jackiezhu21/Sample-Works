import java.util.HashSet;
import java.util.Set;

public class SkipList<T extends Comparable<? super T>>
    implements SkipListInterface<T> {
    private CoinFlipper coinFlipper;
    private int size;
    private Node<T> head;


    /**
     * constructs a SkipList object that stores data in ascending order
     * when an item is inserted, the flipper is called until it returns a tails
     * if for an item the flipper returns n heads, the corresponding node has
     * n + 1 levels
     *
     * @param coinFlipper the source of randomness
     */
    public SkipList(CoinFlipper coinFlipper) {
        this.coinFlipper = coinFlipper;
        size = 0;
        head = new Node<T>(null, 1);
    }

    @Override
    public T first() {
        if (size == 0) {
            return null;
        }
        Node<T> current = head;
        for (int i = 1; i < head.getLevel(); i++) {
            current = current.getDown();
        }
        return current.getNext().getData();
    }

    @Override
    public T last() {
        if (size == 0) {
            return null;
        }
        Node<T> current = head;
        for (int i = 1; i < head.getLevel(); i++) {
            current = current.getDown();
        }
        while (current.getNext() != null) {
            current = current.getNext();
        }
        return current.getData();
    }

    @Override
    public boolean contains(T data) {
        if (data == null) {
            throw new IllegalArgumentException();
        }
        Node<T> probe = head;
        while (probe != null) {
            if (probe.getNext() != null
                    && probe.getNext().getData() != null
                    && data.compareTo(probe.getNext().getData()) > 0) {
                probe = probe.getNext();
            } else if (probe.getNext() != null
                    && probe.getNext().getData() != null
                    && data.compareTo(probe.getNext().getData()) == 0) {
                return true;
            } else {
                probe = probe.getDown();
            }
        }
        return false;
    }

    @Override
    public void put(T data) {
        if (data == null) {
            throw new IllegalArgumentException();
        }
        int level = 1;
        while (coinFlipper.flipCoin() != CoinFlipper.Coin.TAILS) {
            level++;
        }
        Node<T> probe = head;
        Node<T> newNode = new Node<T>(data, level);
        if (level > head.getLevel()) {
            for (int i = 1; i <= level - head.getLevel(); i++) {
                Node<T> newHead = new Node<T>(null, probe.getLevel() + 1);
                newHead.setDown(probe);
                probe.setUp(newHead);
                probe = probe.getUp();
            }
            head = probe;

        } else {
            for (int i = 0; i < head.getLevel() - level; i++) {
                probe = probe.getDown();
            }
        }
        Node<T> current = newNode;


        for (int i = level; i >= 1; i--) {

            while (probe.getNext() != null
                    && probe.getNext().getData() != null
                    && data.compareTo(probe.getNext().getData()) > 0) {
                probe = probe.getNext();
            }
            Node<T> probeOldNext = probe.getNext();
            probe.setNext(current);
            current.setNext(probeOldNext);
            if (i > 1) {
                Node<T> nextDown = new Node<T>(data, i - 1,
                        null, current, null);
                current.setDown(nextDown);
                current = current.getDown();
                probe = probe.getDown();
            }
        }
        size++;
    }

    @Override
    public T get(T data) {
        if (data == null) {
            throw new IllegalArgumentException();
        }
        Node<T> probe = head;
        while (probe != null) {
            if (probe.getNext() != null
                    && probe.getNext().getData() != null
                    && data.compareTo(probe.getNext().getData()) > 0) {
                probe = probe.getNext();
            } else if (probe.getNext() != null
                    && probe.getNext().getData() != null
                    && data.compareTo(probe.getNext().getData()) == 0) {
                return probe.getNext().getData();
            } else {
                probe = probe.getDown();
            }
        }
        return null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        size = 0;
        head = new Node<T>(null, 1);
    }

    @Override
    public Set<T> dataSet() {
        HashSet<T> data = new HashSet<T>();
        if (size == 0) {
            return data;
        } else {
            Node<T> probe = head;
            for (int i = probe.getLevel(); i > 1; i--) {
                probe = probe.getDown();
            }

            while (probe.getNext() != null) {
                data.add(probe.getNext().getData());
                probe = probe.getNext();
            }
        }
        return data;
    }

    @Override
    public T remove(T data) {
        if (data == null) {
            throw new IllegalArgumentException();
        }
        if (size == 0) {
            return null;
        }
        Node<T> probe = head;

        T returned = null;
        while (probe != null) {
            Node<T> tbd = probe.getNext();
            if (probe.getNext() != null
                    && probe.getNext().getData() != null) {
                if (data.compareTo(probe.getNext().getData()) == 0) {

                    returned = probe.getNext().getData();
                    probe.setNext(tbd.getNext());
                    probe = probe.getDown();
                } else if (data.compareTo(probe.getNext().getData()) > 0) {
                    probe = probe.getNext();
                } else if (data.compareTo(probe.getNext().getData()) < 0) {
                    probe = probe.getDown();
                }
            } else {
                probe = probe.getDown();
            }
        }
        if (returned != null) {
            size--;
        }
        while (head.getNext() == null && head.getDown() != null) {
            head = head.getDown();
            head.setUp(null);
        }
        return returned;
    }




}


