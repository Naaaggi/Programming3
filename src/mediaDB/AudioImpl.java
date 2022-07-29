package mediaDB;

import java.util.Collection;
import java.util.Date;

public abstract class AudioImpl implements Audio {
    private int accessCount;
    private int bitrate;
    private int length;
    private int size;
    private String address;
    private int samplingRate;
    private Collection<Tag> tags;
    private Uploader uploader;
    private Date uploadDate;
    private String title;

    public AudioImpl(String title) {
        this.title = title;

    }
    public AudioImpl(int bitrate, int length,
                     int samplingRate, Collection<Tag> tags,
                     Uploader uploader, Date uploadDate) {
        this.bitrate = bitrate;
        this.length = length;
        this.size = bitrate*length/8;
        this.samplingRate = samplingRate;
        this.tags = tags;
        this.uploader = uploader;
        this.uploadDate = uploadDate;
    }




    @Override
    public int getSamplingRate() {
        return samplingRate;
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
        this.accessCount = accessCount;
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

    public abstract void setResolution(String resolution);
}
