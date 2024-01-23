import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Folder implements Component {
    String name;
    List<Component> componentList;
    Date creationTime;
    String directory;

    Folder(String name) {
        this.name = name;
        this.componentList = new ArrayList<>();
        this.creationTime = new Date();
    }
    @Override
    public List<Component> getComponentList() {
        return componentList;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getSize() {
        int folderSize = 0;
        for (Component c : componentList) {
            folderSize += c.getSize();
        }
        return folderSize;
    }

    @Override
    public String getType() {
        return "Folder";
    }
    @Override
    public void setDirectory(String parentDirectory) {
        if (parentDirectory.endsWith("\\")) {
            this.directory = parentDirectory + name;
        } else {
            this.directory = parentDirectory + "\\" + name;
        }
    }


    @Override
    public String getDirectory() {
        return directory;
    }

    @Override
    public int getComponentCount() {
        return componentList.size();
    }

    @Override
    public Date getCreationTime() {
        return creationTime;
    }

    @Override
    public void showDetails() {
        System.out.println("Name: " + name);
        System.out.println("Type: "+getType());
        System.out.println("Size: " + getSize());
        System.out.println("Directory :\"" + getDirectory() + "\"");
        System.out.println("Component Count: " + getComponentCount());
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMM,yyyy hh:mm a");
        System.out.println("Creation time: " + dateFormat.format(creationTime));

    }

    @Override
    public void delete() {
        for (Component c : componentList) {
            c.delete();
        }
        System.out.println("Deleting folder: " + name+"......");
    }
}


