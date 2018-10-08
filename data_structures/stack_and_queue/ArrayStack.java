import java.util.NoSuchElementException;

/**
 * Your implementation of an array-backed stack.
 *
 * @author
 * @version 1.0
 */
public class ArrayStack<T> implements StackInterface<T> {

    // Do not add new instance variables.
    private T[] backingArray;
    private int size;

    /**
     * Constructs a new ArrayStack.
     */
    public ArrayStack() {
        backingArray = (T[]) new Object[INITIAL_CAPACITY];
        size = 0;
    }

    @Override
    public boolean isEmpty() {
        return (size < 1);
    }

    /**
     * Pop from the stack.
     *
     * Do not shrink the backing array.
     *
     * @see StackInterface#pop()
     */
    @Override
    public T pop() throws NoSuchElementException {
        if (size == 0) {
            throw new NoSuchElementException("The stack is empty!");
        }
        T popping = backingArray[size - 1];
        backingArray[size - 1] = null;
        size--;
        return popping;
    }

    /**
     * Push the given data onto the stack.
     *
     * If sufficient space is not available in the backing array, you should
     * regrow it to 1.5 times the current backing array length, rounding down
     * if necessary.
     *
     * @see StackInterface#push()
     */
    @Override
    public void push(T data) throws IllegalArgumentException {
        if (data == null) {
            throw new IllegalArgumentException(
                "Data cannot be pushed onto a stack!");
        }
        if (size >= backingArray.length) {
            int resize = (int) (backingArray.length * 1.5);
            T[] help = (T[]) new Object[resize];
            for (int i = 0; i < backingArray.length; i++) {
                help[i] = backingArray[i];
            }
            backingArray = help;
        }
        backingArray[size] = data;
        size++;
    }

    @Override
    public int size() {
        return size;
    }

    /**
     * Returns the backing array of this stack.
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
