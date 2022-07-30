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
    private String title;
    private Date uploadDate;


    public VideoImpl(String title) {
        this.title = title;
    }
    public VideoImpl(int accessCount, int bitrate, int length, int size,
                     String address, String resolution, Collection<Tag> tags,
                     Uploader uploader) {
        this.accessCount = accessCount;
        this.bitrate = bitrate;
        this.length = length;
        this.size = size;
        this.address = address;
        this.resolution = resolution;
        this.tags = tags;
        this.uploader = uploader;
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
        this.address = address;
    }

    @Override
    public void setAccessCount(int accessCount) {
        this.accessCount = accessCount;
    }

    @Override
    public void setUploadDate(Date date) {
        this.uploadDate = date;
    }

    @Override
    public Uploader getUploader() {
            return uploader;
    }

    @Override
    public Date getUploadDate() {
        return uploadDate;
    }

    @Override
    public String getResolution() {
        return resolution;
    }

    @Override
    public String toString() {
        return "Video " +
                uploader + " " + tags + " "+
                bitrate + " " + length + " " + resolution
                + " " + address + " " + uploadDate;
    }
}
