import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Drive implements Component {
    String name;
    Date creationTime;
    List<Component> componentList;
    String directory;

    Drive(String name) {
        this.name = name;
        this.componentList = new ArrayList<>();
        creationTime=new Date();
        if(name.equalsIgnoreCase("Root")){
            this.directory = "";
        }
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
        int driveSize = 0;
        for (Component c : componentList) {
            driveSize += c.getSize();
        }
        return driveSize;
    }

    @Override
    public String getType() {
        return "Drive";
    }
    @Override
    public void setDirectory(String parentDirectory) {
        if(name.equalsIgnoreCase("Root")){
            this.directory = "";
        }
        else{
                this.directory = parentDirectory+name+":\\";
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
        System.out.println("Deleting drive: " + name+"......");
    }
}
