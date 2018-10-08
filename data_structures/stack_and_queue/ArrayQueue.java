import java.util.NoSuchElementException;

/**
 * Your implementation of an array-backed queue.
 *
 * @author Joshua Santillo
 * @version 1.0
 */
public class ArrayQueue<T> implements QueueInterface<T> {

    // Do not add new instance variables.
    private T[] backingArray;
    private int front;
    private int back;
    private int size;

    /**
     * Constructs a new ArrayQueue.
     */
    public ArrayQueue() {
        backingArray = (T[]) new Object[this.INITIAL_CAPACITY];
        size = 0;
        front = 0;
        back = 0;
    }

    /**
     * Dequeue from the front of the queue.
     *
     * Do not shrink the backing array.
     * If the queue becomes empty as a result of this call, you <b>must not</b>
     * explicitly reset front or back to 0.
     *
     * @see QueueInterface#dequeue()
     */
    @Override
    public T dequeue() throws NoSuchElementException {
        if (size == 0) {
            throw new NoSuchElementException("The queue has no elements");
        }
        T removing = backingArray[front];
        backingArray[front] = null;
        if (front < backingArray.length - 1) {
            front++;
        } else {
            front = 0;
        }
        size--;
        return removing;
    }

    /**
     * Add the given data to the queue.
     *
     * If sufficient space is not available in the backing array, you should
     * regrow it to 1.5 times the current backing array length, rounding down
     * if necessary. If a regrow is necessary, you should copy elements to the
     * front of the new array and reset front to 0.
     *
     * @see QueueInterface#enqueue(T)
     */
    @Override
    public void enqueue(T data) throws IllegalArgumentException {
        if (data == null) {
            throw new IllegalArgumentException(
                "Null data is not allowed in a queue");
        }
        if (back == backingArray.length && front > 0) {
            back = 0;
        } else if (back == backingArray.length && front == 0) {
            int resize = (int) (backingArray.length * 1.5);
            T[] help = (T[]) new Object[resize];
            for (int i = front; i < backingArray.length; i++) {
                help[i - front] = backingArray[i];
            }
            backingArray = help;
        } else if (size == backingArray.length && back == front) {
            int resize = (int) (backingArray.length * 1.5);
            T[] help = (T[]) new Object[resize];
            int count = 0;
            for (int i = front; i < backingArray.length; i++) {
                help[i - front] = backingArray[i];
                count++;
            }
            for (int i = 0; i <= back; i++) {
                help[i + count] = backingArray[i];
            }
            front = 0;
            back = size;
            backingArray = help;
        }
        backingArray[back] = data;
        back++;
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
     * Returns the backing array of this queue.
     * Normally, you would not do this, but we need it for grading your work.
     *
     * DO NOT USE THIS METHOD IN YOUR CODE.
     *
     * @return the backing array
     */
    public Object[] getBackingArray() {
        // DO NOT MODIFY!
        return backingArray;
    }
}

