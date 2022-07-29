package mediaDB;

import java.util.Collection;
import java.util.Date;

public class VideoImpl implements Video{

    private int accessCount;
    private int bitrate;
    private int length;
    private int size;
    private String address;
    private String resolution;
    private Collection<Tag> tags;
    private Uploader uploader;
    private Date uploadDate;

    public VideoImpl(int accessCount, int bitrate, int length, int size,
                     String address, String resolution, Collection<Tag> tags,
                     Uploader uploader, Date uploadDate) {
        this.accessCount = accessCount;
        this.bitrate = bitrate;
        this.length = length;
        this.size = size;
        this.address = address;
        this.resolution = resolution;
        this.tags = tags;
        this.uploader = uploader;
        this.uploadDate = uploadDate;
    }


    @Override
    public String getAddress() {
        return address;
    }

    @Override
    public Collection<Tag> getTags() {
        return tags;
    }

    @Override
    public int getAccessCount() {
        return accessCount;
    }

    @Override
    public int getBitrate() {
        return bitrate;
    }

    @Override
    public int getLength() {
        return length;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public void setAddress(String address) {


    }

    @Override
    public void setAccessCount(int accessCount) {
        this.accessCount = accessCount++;
    }



    @Override
    public void setUploadDate(Date date) {

    }

    @Override
    public Uploader getUploader() {
        return null;
    }

    @Override
    public Date getUploadDate() {
        return null;
    }

    @Override
    public String getResolution() {
        return null;
    }
}
