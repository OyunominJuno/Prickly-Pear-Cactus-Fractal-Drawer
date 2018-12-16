import java.util.*;
import java.io.Serializable;
/*
 * Class ArrayList<E> can be used to store a list of values of type E.
 * 
 * @author Building Java Programs textBook editors
 * @version undetected
 */
public class ArrayList<E> implements Iterable<E>, Serializable {
    private E[] elementData; // list of values
    private int size;        // current number of elements in the list

    public static final int DEFAULT_CAPACITY = 50;

    /*
     * Constructs an empty list of default capacity
     */ 
    public ArrayList() {
        this(DEFAULT_CAPACITY);
    }

    /* 
     * Constructs empty Arraylist with specified capacity
     * 
     * @param int desired capacity
     * @throws IllegalArgumentException if capacity is equal or below zero
     */
    @SuppressWarnings("unchecked")
    public ArrayList(int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException("capacity: " + capacity);
        }
        elementData = (E[]) new Object[capacity];
        size = 0;
    }

    /*
     * Retrieves the size of the array 
     * 
     * @return the current number of elements in the list
     */
    public int size() {
        return size;
    }

    /*
     * Retrieves the object of the given index
     * 
     * @param int desired index
     * @throws IndexOutOfBoundsException if not 0 <= index < size()
     * @return the value at the given index in the list
     */
    public E get(int index) {
        checkIndex(index);
        return elementData[index];
    }

    /*
     * Creates a comma-separated, bracketed version of the list
     * 
     * @return  string version of the list
     */
    public String toString() {
        if (size == 0) {
            return "[]";
        } else {
            String result = "[" + elementData[0];
            for (int i = 1; i < size; i++) {
                result += ", " + elementData[i];
            }
            result += "]";
            return result;
        }
    }
    
    /*
     * Retrieves the index of the given object type of E
     * 
     * @param  E desired object 
     * @return the position of the first occurrence of the given
     *         value (-1 if not found)
     */
    public int indexOf(E value) {
        for (int i = 0; i < size; i++) {
            if (elementData[i].equals(value)) {
                return i;
            }
        }
        return -1;
    }

    /*
     * Retrieves boolean if list is empty 
     * 
     * @return true if list is empty, false otherwise
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /*
     * Checks if given object type of E is contained in the list
     * 
     * @return true if the given value is contained in the list,
     *         false otherwise
     */
    public boolean contains(E value) {
        return indexOf(value) >= 0;
    }

    /*
     * Appends the given value to the end of the list 
     * 
     * @param  E desired object 
     */
    public void add(E value) {
        ensureCapacity(size + 1);
        elementData[size] = value;
        size++;
    }

    /*
     * Inserts the given value at the given index, shifting subsequent
     *       values right
     * @param int desired index 
     * @param int desired object type of E
     * @throws IndexOutOfBoundsException if index is not between 0 and (size() - 1) - inclusive
     */
    public void add(int index, E value) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("index: " + index);
        }
        ensureCapacity(size + 1);
        for (int i = size; i >= index + 1; i--) {
            elementData[i] = elementData[i - 1];
        }
        elementData[index] = value;
        size++;
    }

    /*
     * Removes value at the given index, shifting subsequent values left
     * 
     * @param int desired index
     * @throws IndexOutOfBoundsException if index is not legal
     */
    public void remove(int index) {
        checkIndex(index);
        for (int i = index; i < size - 1; i++) {
            elementData[i] = elementData[i + 1];
        }
        elementData[size - 1] = null;
        size--;
    }

    /* 
     * replaces the value at the given index with the given value
     * 
     * @param int desired index
     * @param E desired object
     * @throws IndexOutOfBoundsException if index is not legal
     */
    public void set(int index, E value) {
        checkIndex(index);
        elementData[index] = value;
    }

    /*
     * Empties the list
     */
    public void clear() {
        for (int i = 0; i < size; i++) {
            elementData[i] = null;
        }
        size = 0;
    }

    /*
     * Appends all values in the given list to the end of this list
     * 
     * @param ArrayList<E> another deisred Arraylist that to be appended
     */
    public void addAll(ArrayList<E> other) {
        ensureCapacity(size + other.size);
        for (int i = 0; i < other.size; i++) {
            add(other.elementData[i]);
        }
    }

    /*
     * returns an iterator for this list
     * 
     * @return an iterator for this list
     */
    public Iterator<E> iterator() {
        return new ArrayListIterator();
    }

    /*
     * Ensures that the underlying array has the given capacity; if not
     * the size is added by its half (or more if given capacity is even larger)
     * 
     * @param int desired capacity
     */
    public void ensureCapacity(int capacity) {
        if (capacity > elementData.length) {
            int newCapacity = elementData.length * 1 / 2 + 1;
            if (capacity > newCapacity) {
                newCapacity = capacity;
            }
            elementData = Arrays.copyOf(elementData, newCapacity);
        }
    }
    
    /*
     * Checks the given index if it is between 0 and the size() - inclusive;
     * @throws an IndexOutOfBoundsException if the given index is
     *       not a legal index of the current list
     */       
    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("index: " + index);
        }
    }

    /*
     * ArrayList Iterator that iterates throught the list
     */
    private class ArrayListIterator implements Iterator<E>, Serializable {
        private int position;           // current position within the list
        private boolean removeOK;       // whether it's okay to remove now
        private E tempo;
        /* constructs an iterator for the given list */
        public ArrayListIterator() {
            position = 0;
            removeOK = false;
            
            
        }

        public E forEachLoop() {
            for (int index = 0; index < size; index++) {
                tempo = elementData[index];
            }
            return tempo;
        }
        /* returns true if there are more elements left, false otherwise */
        public boolean hasNext() {
            return position < size();
            
        }

        /* hasNext() (throws NoSuchElementException if not)
         * @return the next element in the iteration 
         */
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            E result = elementData[position];
            position++;
            removeOK = true;
            return result;
        }

        /* pre : next() has been called without a call on remove (throws
         *       IllegalStateException if not)
         * post: removes the last element returned by the iterator
         */ 
        public void remove() {
            if (!removeOK) {
                throw new IllegalStateException();
            }
            ArrayList.this.remove(position - 1);
            position--;
            removeOK = false;
        }
    }
}