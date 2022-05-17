package mediaDB;

import java.util.Collection;

public interface Content {
    long setAccessCount(long accessCount);
    String getAddress();
    Collection<Tag> getTags();
    long getAccessCount();
}
