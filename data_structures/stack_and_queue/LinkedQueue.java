import java.util.NoSuchElementException;

/**
 * Your implementation of a linked queue.
 *
 * @author Joshua Santillo
 * @version 1.0
 */
public class LinkedQueue<T> implements QueueInterface<T> {

    // Do not add new instance variables.
    private LinkedNode<T> head;
    private LinkedNode<T> tail;
    private int size;

    @Override
    public T dequeue() throws NoSuchElementException {
        if (size == 0) {
            throw new NoSuchElementException("The queue is empty!");
        }
        T removing = head.getData();
        head = head.getNext();
        if (head == null) {
            tail = null;
        }
        size--;
        return removing;
    }

    @Override
    public void enqueue(T data) throws IllegalArgumentException {
        if (data == null) {
            throw new IllegalArgumentException("Null data cannot be stored.");
        }
        if (size != 0) {
            tail.setNext(new LinkedNode(data, null));
            tail = tail.getNext();
        } else {
            head = new LinkedNode(data, null);
            tail = head;
        }
        size++;
    }

    @Override
    public boolean isEmpty() {
        return (size == 0);
    }

    @Override
    public int size() {
        return size;
    }

    /**
     * Returns the head of this queue.
     * Normally, you would not do this, but we need it for grading your work.
     *
     * DO NOT USE THIS METHOD IN YOUR CODE.
     *
     * @return the head node
     */
    public LinkedNode<T> getHead() {
        // DO NOT MODIFY!
        return head;
    }

    /**
     * Returns the tail of this queue.
     * Normally, you would not do this, but we need it for grading your work.
     *
     * DO NOT USE THIS METHOD IN YOUR CODE.
     *
     * @return the tail node
     */
    public LinkedNode<T> getTail() {
        // DO NOT MODIFY!
        return tail;
    }
}

