package mediaDB;


import java.util.Date;

public interface MediaContentUploadable extends MediaContent, Uploadable {
    void setAddress(String address);
    long setAccessCount(long accessCount);
    void setUploadDate(Date date);

}
