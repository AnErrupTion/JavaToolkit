package open.java.toolkit.arrays.maps;

public class UtilInfiniteValues
{
    private final Object[] values;

    /**
     * Constructor for this class.
     * @param values The values to add to the underlying array.
     */
    public UtilInfiniteValues(Object... values)
    {
        this.values = values;
    }

    /**
     * Gets an element from the underlying array.
     * @param index The index of the element in the underlying array.
     * @return The element from the underlying array.
     */
    public Object get(int index)
    {
        return values[index];
    }
}
