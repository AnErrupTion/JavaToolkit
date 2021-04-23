package open.java.toolkit.arrays.lists;

import open.java.toolkit.Errors;

import java.util.Arrays;
import java.util.List;

public class EfficientList<E> implements InterfaceList<E>
{
    private int size;
    private transient E[] data;

    /**
     * Constructor for this class. Initiates list with capacity.
     * @param capacity The initial capacity of the list.
     */
    public EfficientList(int capacity)
    {
        if (capacity < 0)
            Errors.newError(new IllegalArgumentException("Illegal list capacity : " + capacity));

        this.data = (E[]) new Object[capacity];
    }

    /**
     * Constructor for this class. Initiates list with data.
     * @param data The initial data for the list.
     */
    public EfficientList(E[] data)
    {
        this.data = data;
    }

    /**
     * Constructor for this class. Initiates list with data.
     * @param data The initial data for the list.
     */
    public EfficientList(List<E> data)
    {
        this.data = (E[]) data.toArray(Object[]::new);
    }

    /**
     * Constructor for this class. No initiate data or capacity.
     */
    public EfficientList()
    {
        this.data = (E[]) new Object[0];
    }

    /**
     * Gets the current size of the list.
     * @return The size of the list.
     */
    @Override
    public int size()
    {
        return size;
    }

    /**
     * Checks if the list is empty or not.
     * @return Whether or not the list is empty.
     */
    @Override
    public boolean isEmpty()
    {
        return size == 0;
    }

    /**
     * Adds an element to the list.
     * @param element The element to add to the list.
     * @return Whether or not the element has been successfully added to the list.
     */
    @Override
    public boolean add(E element)
    {
        if (contains(element))
            return false;

        data = Arrays.copyOf(data, size == 0 ? size + 2 : size + 1);
        data[size++] = element;

        return true;
    }

    /**
     * Clears the list.
     */
    @Override
    public void clear()
    {
        for (int i = 0; i < size; i++)
            data[i] = null;

        size = 0;
    }

    /**
     * Gets an element from the list.
     * @param index The index of the element in the list.
     * @return The element from the list.
     */
    @Override
    public E get(int index)
    {
        if (index < 0 || index >= size)
            Errors.newError(new ArrayIndexOutOfBoundsException("Index " + index + " out of bounds for array."));

        return data[index];
    }

    /**
     * Sets an existing element in the list to something else.
     * @param index The index of the existing element in the list.
     * @param element The new element to replace the existing one with.
     * @return Whether or not the element has been successfully set.
     */
    @Override
    public boolean set(int index, E element)
    {
        if (isEmpty() || index >= size)
            return false;

        data[index] = element;
        return true;
    }

    /**
     * Removes an existing element from the list.
     * @param element The existing element from the list.
     * @return Whether or not the element has been successfully removed.
     */
    @Override
    public boolean remove(E element)
    {
        int index = indexOf(element);
        if (index == -1)
            return false;

        size--;

        int num = size - index;
        if (num > 0)
            System.arraycopy(data, index + 1, data, index, num);

        data[size] = null;
        return true;
    }

    /**
     * Gets the index of an element in the list.
     * @param element The element to look for in the list.
     * @return The index of the element in the list.
     */
    @Override
    public int indexOf(E element)
    {
        for (int i = 0; i < size; i++)
            if (data[i] == element)
                return i;

        return -1;
    }

    /**
     * Checks if an element is in the list or not.
     * @param element The element to check.
     * @return Whether or not the element is in the list.
     */
    @Override
    public boolean contains(E element)
    {
        return indexOf(element) > -1;
    }
}
