package mediaDB;

import java.util.Collection;

public interface Content {
    void setAccessCount(int accessCount);
    String getAddress();
    void setAddress(String address);
    Collection<Tag> getTags();
    int getAccessCount();

}
