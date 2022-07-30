package mediaDB;

import java.util.Collection;
import java.util.Date;

public class AudioVideoImpl extends AudioImpl implements AudioVideo {
    private String resolution;
    private String mediaType;

    public AudioVideoImpl(String title) {
        super(title);
    }
    public AudioVideoImpl(int bitrate, int length, int samplingRate, Collection<Tag> tags, Uploader uploader, String resolution) {
        super(bitrate, length, samplingRate, tags, uploader);
        this.resolution = resolution;
        this.mediaType = "AudioVideo";
    }


    public String getMediaType() {
        return mediaType;
    }

    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }

    @Override
    public String getResolution() {
        return resolution;
    }

    @Override
    public void setResolution(String resolution) {
        this.resolution = resolution;
    }


    @Override
    public String toString() {
        return "AudioVideo " +
                getUploader() + " " + getTags() + " "+
                getBitrate() + " " + getLength() + " " + resolution
                + " " + getAddress() + " " + getUploadDate();
    }
}
