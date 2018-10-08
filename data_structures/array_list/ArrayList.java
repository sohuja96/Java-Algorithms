/**
 * Your implementation of an ArrayList.
 *
 * @author Joshua Santillo
 * @version 1
 */
public class ArrayList<T> implements ArrayListInterface<T> {

    // Do not add new instance variables.
    private T[] backingArray;
    private int size;

    /**
     * Constructs a new ArrayList.
     *
     * You may add statements to this method.
     */
    public ArrayList() {
        this.backingArray =
                (T[]) new Object[ArrayListInterface.INITIAL_CAPACITY];
    }
    @Override
    public void addAtIndex(int index, T data) throws IndexOutOfBoundsException {
        if (index > this.size() || index < 0) {
            throw new IndexOutOfBoundsException(
                    "The index is not connected to the current ArrayList.");
        }
        if (data == null) {
            throw new IllegalArgumentException(
                    "The data specified is null: no data exists");
        }
        if (this.size() != backingArray.length) { //part empty
            for (int i = this.size(); i > index; i--) {
                backingArray[i]
                        = backingArray[i - 1]; //each position is the previous
            }
            backingArray[index] = data;
        } else { //total full
            T[] tempArray = (T[]) new Object[backingArray.length * 2];
            for (int i = backingArray.length; i > index; i--) {
                tempArray[i]
                    = backingArray[i - 1];
                //each position is the position before it
            }
            tempArray[index] = data;
            for (int i = 0; i < index; i++) {
                tempArray[i] = backingArray[i];
            }
            backingArray = tempArray;
        }
        size++;
    }

    @Override
    public void addToFront(T data) {
        if (data == null) {
            throw new IllegalArgumentException(
                    "The data specified is null: no data exists");
        }
        if (backingArray.length != this.size()) {
            for (int i = this.size() - 1; i >= 0; i--) {
                backingArray[i + 1]
                    = backingArray[i];
                //each position is the position before it
            }
            backingArray[0] = data;
        } else {
            T[] tempArray = (T[]) new Object[backingArray.length * 2];
            for (int i = this.size() - 1; i >= 0; i--) {
                tempArray[i + 1]
                    = backingArray[i];
                //each position is the position before it
            }
            tempArray[0] = data;
            backingArray = tempArray;
        }
        size++;
    }

    @Override
    public void addToBack(T data) {
        if (data == null) {
            throw new IllegalArgumentException(
                    "The data specified is null: no data exists");
        }
        if (this.size() == backingArray.length) {
            T[] tempArray = (T[]) new Object[backingArray.length * 2];
            for (int i = 0; i < this.size(); i++) {
                tempArray[i] = backingArray[i];
            }
            tempArray[this.size()] = data;
            backingArray = tempArray;
        } else {
            backingArray[this.size()] = data;
        }
        size++;
    }

    @Override
    public T removeAtIndex(int index) {
        if (index < 0 || index > this.size() - 1) {
            throw new IndexOutOfBoundsException(
                    "The specified index is not part of the ArrayList.");
        } else {
            T removing = this.get(index);
            for (int i = index; i < this.size(); i++) {
                backingArray[i] = backingArray[i + 1];
            }
            backingArray[backingArray.length - 1] = null;
            size--;
            return removing;
        }
    }


    @Override
    public T removeFromFront() {
        if (!this.isEmpty()) {
            T removing = backingArray[0];
            for (int i = 0; i < backingArray.length - 1; i++) {
                backingArray[i] = backingArray[i + 1];
            }
            backingArray[backingArray.length - 1] = null;
            size--;
            return removing;
        } else {
            return null;
        }
    }

    @Override
    public T removeFromBack() {
        if (!this.isEmpty()) {
            T removing = backingArray[this.size() - 1];
            backingArray[this.size() - 1] = null;
            size--;
            return removing;
        } else {
            return null;
        }
    }

    @Override
    public T get(int index) {
        if (!(index < 0 || index >= this.size())) {
            return backingArray[index];
        } else {
            throw new IndexOutOfBoundsException(
                    "The specified index is not part of the ArrayList.");
        }
    }

    @Override
    public boolean isEmpty() {
        return (this.size() == 0);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        T[] tempArray = (T[]) new Object[ArrayListInterface.INITIAL_CAPACITY];
        backingArray = tempArray;
        size = 0;
    }

    @Override
    public Object[] getBackingArray() {
        // DO NOT MODIFY.
        return backingArray;
    }
}
