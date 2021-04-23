package open.java.toolkit.diagnostics;

import java.util.Optional;
import java.util.stream.Stream;

public class Process
{
    /**
     * Spawns a new process.
     * @param cmd The command line to use.
     * @param inheritIO Whether or not to inherit the input and output of the process.
     * @return The ProcessBuilder of the newly spawned process.
     */
    public static ProcessBuilder createProcess(String cmd, boolean inheritIO)
    {
        ProcessBuilder builder = new ProcessBuilder(cmd.split(" "));
        if (inheritIO) builder.inheritIO();

        return builder;
    }

    /**
     * Gets all current running processes.
     * @return All current running processes, as a stream.
     */
    public static Stream<ProcessHandle> getProcesses()
    {
        return ProcessHandle.allProcesses();
    }

    /**
     * Gets a specific process from all current running processes.
     * @param name The name of the process to look for.
     * @return The ProcessHandle of the specific process, if not null.
     */
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

    /**
     * Stops a specific process.
     * @param name The name of the process to stop.
     * @param kill Whether or not to kill the process.
     */
    public static void stopProcess(String name, boolean kill)
    {
        ProcessHandle handle = getProcess(name);
        if (kill)
            handle.destroyForcibly();
        else
            handle.destroy();
    }
}
