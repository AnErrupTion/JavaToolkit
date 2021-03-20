package open.java.toolkit.arrays.maps;

public class UtilInfiniteValues
{
    private final Object[] values;

    public UtilInfiniteValues(Object... values)
    {
        this.values = values;
    }

    public Object get(int index)
    {
        return values[index];
    }
}
