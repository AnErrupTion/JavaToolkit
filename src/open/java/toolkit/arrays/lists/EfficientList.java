package open.java.toolkit.arrays.lists;

import open.java.toolkit.Errors;
import java.util.Arrays;

public class EfficientList<E> implements InterfaceList<E>
{
    private int size;
    private transient Object[] data;
    
    public EfficientList(int capacity)
    {
        if (capacity < 0)
            Errors.newError(new IllegalArgumentException("Illegal list capacity : " + capacity));
            
        this.data = new Object[capacity];
    }

    public EfficientList()
    {
        this.data = new Object[0];
    }

    @Override
    public int size()
    {
        return size;
    }

    @Override
    public boolean isEmpty()
    {
        return size == 0;
    }

    @Override
    public boolean add(E element)
    {
        if (contains(element))
            return false;
            
        data = Arrays.copyOf(data, size == 0 ? size + 2 : size + 1);
        data[size++] = element;
        
        return true;
    }

    @Override
    public void clear()
    {
        for (int i = 0; i < size; i++)
            data[i] = null;
            
        size = 0;
    }

    @Override
    public E get(int index)
    {
        if (index < 0 || index >= size)
            Errors.newError(new ArrayIndexOutOfBoundsException("Index " + index + " out of bounds for array."));
            
        return (E) data[index];
    }

    @Override
    public boolean set(int index, E element)
    {
        if (isEmpty())
            return false;
            
        data[index] = element;
        return true;
    }

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

    @Override
    public int indexOf(E element)
    {
        for (int i = 0; i < size; i++)
            if (data[i] == element)
                return i;
                
        return -1;
    }

    @Override
    public boolean contains(E element)
    {
        return indexOf(element) > -1;
    }
}
