/**
 * Implementation of a doubly-linked list. I have optimised it as best I can.
 *
 * @author Joshua Santillo
 * @version 1.0
 */
public class DoublyLinkedList<T> implements LinkedListInterface<T> {
    // Do not add new instance variables.
    private LinkedListNode<T> head;
    private LinkedListNode<T> tail;
    private int size;

    @Override
    public void addAtIndex(int index, T data) throws IllegalArgumentException,
        IndexOutOfBoundsException {
        if (size < index || 0 > index) {
            throw new IndexOutOfBoundsException(
                "The index chosen is detached from the Linked List");
        }
        if (data == null) {
            throw new IllegalArgumentException(
                "The data specified is null:"
                + " null data in a node is disallowed.");
        }
        if (index == 0) {
            addToFront(data);
        } else if (index == size) {
            addToBack(data);
        } else {
            LinkedListNode<T> adding = new LinkedListNode(data);
            LinkedListNode<T> atIndex = head;
            for (int i = 0; i < index; i++) {
                atIndex = atIndex.getNext();
            }
            adding.setNext(atIndex);
            adding.setPrevious(atIndex.getPrevious());
            atIndex.setPrevious(adding);
            adding.getPrevious().setNext(adding);
            size++;
        }
    }

    @Override
    public void addToFront(T data) throws IllegalArgumentException {
        if (data == null) {
            throw new IllegalArgumentException(
                "The data specified is null: "
                + "null data in a node is disallowed.");
        }
        LinkedListNode<T> adding = new LinkedListNode(data);
        if (size == 0) {
            head = adding;
            tail = adding;
        } else {
            head.setPrevious(adding);
            adding.setNext(head);
            head = adding;
        }
        size++;
    }

    @Override
    public void addToBack(T data) throws IllegalArgumentException {
        if (data == null) {
            throw new IllegalArgumentException(
                "The data specified is null: "
                + "null data in a node is disallowed.");
        }
        LinkedListNode<T> adding = new LinkedListNode(data);
        if (size == 0) {
            head = adding;
            tail = adding;
        } else {
            tail.setNext(adding);
            adding.setPrevious(tail);
            tail = adding;
        }
        size++;
    }

    @Override
    public T removeAtIndex(int index) throws IndexOutOfBoundsException {
        if (size <= index || 0 > index) {
            throw new IndexOutOfBoundsException(
                    "The index chosen is detached from the Linked List");
        } else if (index == 0) {
            return removeFromFront();
        } else if (index == size - 1) {
            return removeFromBack();
        } else {
            LinkedListNode<T> removing = head;
            for (int i = 0; i < index; i++) {
                removing = removing.getNext();
            }

            removing.getPrevious().setNext(removing.getNext());
            removing.getNext().setPrevious(removing.getPrevious());
            size--;
            return removing.getData();
        }
    }

    @Override
    public T removeFromFront() {
        if (size == 0) {
            return null;
        }
        if (head == tail) {
            T temp = head.getData();
            head = null;
            tail = null;
            size = 0;
            return temp;
        }
        LinkedListNode<T> removing = head;
        head = head.getNext();
        head.setPrevious(null);
        size--;
        return removing.getData();
    }

    @Override
    public T removeFromBack() {
        if (size == 0) {
            return null;
        }
        if (head == tail) {
            T temp = head.getData();
            head = null;
            tail = null;
            size = 0;
            return temp;
        }
        LinkedListNode<T> removing = tail;
        tail = tail.getPrevious();
        tail.setNext(null);
        size--;
        return removing.getData();
    }

    @Override
    public boolean removeAllOccurrences(T data)
        throws IllegalArgumentException {
        if (data == null) {
            throw new IllegalArgumentException(
                "The data specified is null: "
                + "null data will never be found in a Linked List Node.");
        }
        boolean anyRemoved = false;
        LinkedListNode<T> tracking = head;
        int i = 0;
        while (i < size) {
            if (tracking.getData().equals(data)) {
                anyRemoved = true;
                tracking.getPrevious().setNext(tracking.getNext());
                tracking.getNext().setPrevious(tracking.getPrevious());
                size--;
            }
            tracking = tracking.getNext();
            i++;
        }
        return anyRemoved;
    }

    @Override
    public T get(int index) throws IndexOutOfBoundsException {
        if (size <= index || 0 > index || size == 0) {
            throw new IndexOutOfBoundsException(
                "The index chosen is detached from the Linked List");
        }
        if (index == 0) {
            return head.getData();
        }
        if (index == size - 1) {
            return tail.getData();
        }
        LinkedListNode<T> getting = head;
        for (int i = 0; i < index; i++) {
            getting = getting.getNext();
        }
        return getting.getData();
    }

    @Override
    public Object[] toArray() {
        Object[] theArray = (T[]) new Object[size];
        LinkedListNode<T> theNode = head;
        for (int i = 0; i < size; i++) {
            theArray[i] = theNode.getData();
            theNode = theNode.getNext();
        }
        return theArray;
    }

    @Override
    public boolean isEmpty() {
        return (size == 0);
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

    @Override
    public LinkedListNode<T> getHead() {
        // DO NOT MODIFY!
        return head;
    }

    @Override
    public LinkedListNode<T> getTail() {
        // DO NOT MODIFY!
        return tail;
    }
}

