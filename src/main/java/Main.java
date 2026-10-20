import java.util.Scanner;
import java.util.Set;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.File;
import java.nio.file.Files;
import java.io.IOException;
import java.util.Arrays;
public class Main {
    public static void main(String[] args) throws Exception {
        //TODO: Uncomment the code below to pass the first stage
        Scanner scanner = new Scanner(System.in);
        Set<String> builtIns = Set.of("type", "echo", "exit");
        while (true) {
            System.out.print("$ ");
            String input = scanner.nextLine();
            String cleanInput = input.trim();
            if (cle  anInput.isEmpty()) {
                continue;
            }
            String[] parts = cleanInput.split(" +");
            String part = parts[0];
            if (part.equals("exit")) {
                break;
            } else if (part.equals("echo")) {
                System.out.println(input.substring(5));
            } else if (part.equals("type")) {
                String target = parts[1];
                if (builtIns.contains(target)) {
                    System.out.println(target + " is a shell builtin");
                } else {
                    String executablePath = findInPath(target);
                    if (executablePath == null) {
                        System.out.println(target + ": not found");
                    } else {
                        System.out.println(target + " is " + executablePath);
                    }
                }
            } else {
                runExternalCommand(parts);
            }
        }
    }
            //Helper method
            private static String findInPath(String target) {
                String PathEnv = System.getenv("PATH");
                if (PathEnv == null) {
                    return null;
                }
                String[] directories = PathEnv.split(File.pathSeparator);
                for (String dir : directories) {
                    Path fullPath = Paths.get(dir, target);
                    if (Files.exists(fullPath) && Files.isExecutable(fullPath)) {
                        return fullPath.toAbsolutePath().toString();
                    }
                }
                return null;
            }
            public static void runExternalCommand(String [] args){
                String executablePath=findInPath(args[0]);
                if(executablePath ==null){
                    System.out.println(args[0] + ": command not found");
                    return;
                }
                try {
                    ProcessBuilder pb = new ProcessBuilder(args);
                    pb.inheritIO();
                    Process process = pb.start();
                    process.waitFor();
                } catch (IOException | InterruptedException e){
                    System.out.println(args[0] + ": error executing command");

                }
                }

        }


