package mediaDB;

import java.util.Collection;

public interface Content {
    void setAccessCount(int accessCount);
    String getAddress();
    Collection<Tag> getTags();
    int getAccessCount();
}
