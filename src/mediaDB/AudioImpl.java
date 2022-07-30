package mediaDB;

import java.util.Collection;
import java.util.Date;

public class AudioImpl implements Audio {
    private int accessCount;
    private int bitrate;
    private int length;
    private int size;
    private String address;
    private int samplingRate;
    private Collection<Tag> tags;
    private Uploader uploader;
    private String title;
    private Date uploadDate;




    public AudioImpl(String title) {
        this.title = title;

    }
    public AudioImpl(int bitrate, int length,
                     int samplingRate, Collection<Tag> tags,
                     Uploader uploader) {
        this.bitrate = bitrate;
        this.length = length;
        this.size = bitrate*length/8;
        this.samplingRate = samplingRate;
        this.tags = tags;
        this.uploader = uploader;
        this.uploadDate = new Date();
    }




    @Override
    public int getSamplingRate() {
        return samplingRate;
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
    public String getAddress() {
        return address;
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
    public String toString() {
        return "Audio " +
                uploader + " " + tags + " "+
                bitrate + " " + length + " " + samplingRate
                + " " + address + " " + uploadDate;
    }
}
