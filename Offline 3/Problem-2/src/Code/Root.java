import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Root implements Component {
    String name;
    List<Component> componentList;
    Date creationTime;
    String directory;

    Root() {
        name = "Root";
        componentList = new ArrayList<>();
        creationTime = new Date();
        directory = "";
    }


    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getSize() {
        int rootSize = 0;
        for (Component c : componentList) {
            rootSize += c.getSize();
        }
        return rootSize;
    }

    @Override
    public String getType() {
        return "Root";
    }

    @Override
    public void setDirectory(String parentDirectory) {
        directory = "";
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
    public List<Component> getComponentList() {
        return componentList;
    }

    @Override
    public void showDetails() {
        System.out.println("Name: " + name);
        System.out.println("Size: " + getSize() + " kB");
        System.out.println("Component Count: " + getComponentCount());
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMM,yyyy hh:mm a");
        System.out.println("Creation time: " + dateFormat.format(creationTime));
    }

    @Override
    public void delete() {

    }
}
