package mediaDB;

import java.util.Collection;

public class InteractiveVideoImpl extends VideoImpl implements InteractiveVideo {
    private String mediaType;
    private String type;
    @Override
    public String getMediaType() {
        return mediaType;
    }

    @Override
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }

    public InteractiveVideoImpl(String title) {
        super(title);
        this.mediaType = "InteractiveVideo";
    }

    public InteractiveVideoImpl(int bitrate, int length, String resolution, Collection<Tag> tags, Uploader uploader, String type) {
        super(bitrate, length, resolution, tags, uploader);
        this.mediaType = "InteractiveVideo";

    }

    @Override
    public String toString() {
        return "InteractiveVideo " +
                getUploader() + " " + getTags() + " "+
                getBitrate() + " " + getLength() + " " + getResolution()
                + " " + getAddress() + " " + getUploadDate();
    }

}
