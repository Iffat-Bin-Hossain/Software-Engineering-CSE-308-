import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class File implements Component{
    String name;
    int size;
    Date creationTime;
    String directory;
    File(String name,int size){
        this.name=name;
        this.size=size;
        this.creationTime=new Date();
    }
    @Override
    public String getName(){
        return name;
    }
    @Override
    public int getSize(){
        return size;
    }

    @Override
    public String getType() {
        return "File";
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
        return 0;
    }


    @Override
    public Date getCreationTime() {
        return creationTime;
    }


    @Override
    public List<Component> getComponentList(){
        return null;
    }
    @Override
    public void showDetails(){
        System.out.println("Name: " + name);
        System.out.println("Type: "+getType());
        System.out.println("Size: " + size + " kB");
        System.out.println("Directory :\"" + getDirectory() + "\"");
        System.out.println("Component Count: "+getComponentCount());
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMM,yyyy hh:mm a");
        System.out.println("Creation time: " + dateFormat.format(creationTime));
    }
    @Override
    public void delete(){
        System.out.println("Deleting file: " + name);
    }

}
