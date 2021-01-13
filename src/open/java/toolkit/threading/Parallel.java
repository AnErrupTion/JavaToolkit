package open.java.toolkit.threading;

import java.io.IOException;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Parallel
{
    public interface Operation<T>
    {
        void perform(T parameter) throws IOException, InterruptedException;
    }

    public static ExecutorService pool;
    public static int threads = 25;

    public static <T> void forEach(final T[] elements, final Operation<T> operation) throws InterruptedException
    {
        pool = Executors.newFixedThreadPool(threads);
        pool.invokeAll(createCallables(elements, operation));
    }

    private static <T> Collection<Callable<Void>> createCallables(final T[] elements, final Operation<T> operation)
    {
        List<Callable<Void>> callables = new LinkedList<>();
        for (int i = 0; i < elements.length; i++)
        {
            T element = elements[i];
            callables.add(() ->
            {
               operation.perform(element);
               return null;
            });
        }

        return callables;
    }
}
