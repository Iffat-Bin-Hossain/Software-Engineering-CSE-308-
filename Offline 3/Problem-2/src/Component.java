import java.util.Date;
import java.util.List;

public interface Component {
    String getName();
    int getSize();
    String getType();
    void setDirectory(String parentDirectory);
    String getDirectory();
    int getComponentCount();
    Date getCreationTime();
    List<Component> getComponentList();
    void showDetails();
    void delete();
}
