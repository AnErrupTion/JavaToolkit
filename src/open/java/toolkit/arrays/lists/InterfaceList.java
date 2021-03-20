package open.java.toolkit.arrays.lists;

public interface InterfaceList<E>
{
    boolean add(E element);
    boolean remove(E element);
    boolean set(int index, E element);
    boolean isEmpty();
    boolean contains(E element);

    E get(int index);
    void clear();

    int size();
    int indexOf(E element);
}
