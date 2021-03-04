package open.java.toolkit.diagnostics;

import java.util.Optional;
import java.util.stream.Stream;

public class Process
{
    public static ProcessBuilder createProcess(String cmd, boolean inheritIO)
    {
        ProcessBuilder builder = new ProcessBuilder(cmd.split(" "));
        if (inheritIO) builder.inheritIO();
        
        return builder;
    }

    public static Stream<ProcessHandle> getProcesses()
    {
        return ProcessHandle.allProcesses();
    }

    public static ProcessHandle getProcess(String name)
    {
        Stream<ProcessHandle> filtered = getProcesses().filter(process ->
        {
            Optional<String> cmd = process.info().command();
            return cmd.isPresent() && cmd.get().contains(name);
        });
        
        Optional<ProcessHandle> handle = filtered.findFirst();
        return handle.orElse(null);
    }

    public static void stopProcess(String name, boolean kill)
    {
        ProcessHandle handle = getProcess(name);
        if (kill)
            handle.destroyForcibly();
        else
            handle.destroy();
    }
}
