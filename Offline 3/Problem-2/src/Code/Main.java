import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.File;
public class Main {
    static Root root;

    public static void main(String[] args) throws FileNotFoundException {
        int count=0;
        root = new Root();
        File Obj = new File("input.txt");
        HierarchicalFileSystem hfs = new HierarchicalFileSystem(root);
        Scanner scn = new Scanner(Obj);
        while (true) {
            String command = scn.nextLine();
            System.out.println("\n............................................ "+ (++count)+": "+command+" ............................................\n");
            String[] tokens = command.split(" ");
            if (tokens[0].equalsIgnoreCase("mkdrive")) {
                String driveName = tokens[1];
                hfs.makeDrive(driveName);
            } else if (tokens[0].equalsIgnoreCase("mkdir")) {
                String directoryName = tokens[1];
                hfs.makeDirectory(directoryName);
            } else if (tokens[0].equalsIgnoreCase("touch")) {
                String fileName = tokens[1];
                int fileSize = Integer.parseInt(tokens[2]);
                hfs.touch(fileName, fileSize);
            } else if (tokens[0].equalsIgnoreCase("cd") && tokens[1].equalsIgnoreCase("~")) {
                hfs.jumpToRoot();
            } else if (tokens[0].equalsIgnoreCase("cd")) {
                String destination = tokens[1];
                hfs.changeDirectory(destination);
            } else if (tokens[0].equalsIgnoreCase("ls")) {
                hfs.details(tokens[1]);
            }  else if (tokens[0].equalsIgnoreCase("delete") && tokens[1].equalsIgnoreCase("-r")) {
                String toBeDeleted = tokens[2];
                hfs.recursiveDelete(toBeDeleted);
            } else if (tokens[0].equalsIgnoreCase("delete")) {
                String toBeDeleted = tokens[1];
                hfs.delete(toBeDeleted);
            } else if (tokens[0].equalsIgnoreCase("list")) {
                hfs.listing();
            } else {
                System.out.println("Program finished");
                break;
            }

        }
    }
}