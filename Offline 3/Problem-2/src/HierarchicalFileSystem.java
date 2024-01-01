import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Scanner;

public class HierarchicalFileSystem {
    Component currentDirectory;

    HierarchicalFileSystem(Component root) {
        currentDirectory = root;
    }

    void changeDirectory(String name) {
        List<Component> componentList = currentDirectory.getComponentList();
        for (Component c : componentList) {
            if (c instanceof File && c.getName().equals(name)) {
                System.out.println("Error: This is a file.Select a folder/drive.");
                return;
            } else if ((c instanceof Folder  && c.getName().equals(name)) || (c instanceof Drive && (c.getName()+":\\").equals(name))) {
                c.setDirectory(currentDirectory.getDirectory());
                currentDirectory = c;
                System.out.println("Current Directory changed to "+c.getName()+".....");
                return;
            }
        }
        System.out.println("Error: Folder/drive not found");
    }
    void details(){
        currentDirectory.showDetails();
    }

    void listing() {
        List<Component> componentList = currentDirectory.getComponentList();
        if (componentList != null) {
            for (Component c : componentList) {
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
                System.out.println(c.getName() + "       " + c.getSize() + "kB       " + dateFormat.format(c.getCreationTime()));
            }
        }
    }

    void delete(String name) {
        List<Component> componentList = currentDirectory.getComponentList();
        for (Component c : componentList) {
            if ((c instanceof Folder || c instanceof Drive)  && c.getName().equals(name)) {
                if(c.getComponentList().isEmpty()) {
                    c.delete();
                    componentList.remove(c);
                    return;
                }
                else{
                    System.out.println("This "+c.getType()+" is not empty.\nIt can not be deleted directly");
                    return;
                }
            } else if (c instanceof File && c.getName().equals(name)) {
                c.delete();
                componentList.remove(c);
                return;
            }
        }
        System.out.println("Error: Drive/Folder/file not found");
    }

    void recursiveDelete(String name) {
        List<Component> componentList = currentDirectory.getComponentList();
        for (Component c : componentList) {
            if ((c instanceof Folder|| c instanceof Drive) && c.getName().equals(name)) {
                c.delete();
                componentList.remove(c);
                return;
            } else if (c instanceof File && c.getName().equals(name)) {
                System.out.println("Warning: Do you want to delete " + name + "?");
                System.out.println("1.Yes\n2.No");
                Scanner scn=new Scanner(System.in);
                String choice=scn.nextLine();
                if(choice.equalsIgnoreCase("1")){
                    c.delete();
                    componentList.remove(c);
                }
                return;
            }
        }
        System.out.println("Error: Drive/Folder/file not found");
    }

    void jumpToRoot() {
        currentDirectory = (Component) Main.root;
        System.out.println("Current Directory changed to Root.....");
    }

    void makeDirectory(String name) {
        List<Component> componentList = currentDirectory.getComponentList();
        for (Component c : componentList) {
            if (c instanceof Folder && c.getName().equals(name)) {
                System.out.println("Error: Folder already exists");
                return;
            }
        }
        Folder newFolder = new Folder(name);
        newFolder.setDirectory(currentDirectory.getDirectory());
        componentList.add(newFolder);
        System.out.println("New Folder "+name+" created.....");
    }

    void touch(String name, int size) {
        List<Component> componentList = currentDirectory.getComponentList();
        for (Component c : componentList) {
            if (c instanceof File && c.getName().equals(name)) {
                System.out.println("Error: File already exists");
                return;
            }
        }
        File newFile = new File(name, size);
        newFile.setDirectory(currentDirectory.getDirectory());
        componentList.add(newFile);
        System.out.println("New File "+name+" created.....");
    }

    void makeDrive(String name) {
        List<Component> componentList = currentDirectory.getComponentList();
        for (Component c : componentList) {
            if (c instanceof Drive && c.getName().equals(name)) {
                System.out.println("Error: Drive already exists");
                return;
            }
        }
        Drive newDrive = new Drive(name);
        newDrive.setDirectory(currentDirectory.getDirectory());
        componentList.add(newDrive);
        System.out.println("New Drive "+name+" created.....");
    }
}
