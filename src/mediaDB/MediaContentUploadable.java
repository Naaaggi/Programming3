package mediaDB;


import java.util.Date;

public interface MediaContentUploadable extends MediaContent, Uploadable {
    void setAddress(String address);
    void setAccessCount(int accessCount);
    void setUploadDate(Date date);
    String getMediaType();

}
