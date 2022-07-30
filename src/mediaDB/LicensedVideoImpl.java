package mediaDB;

import java.util.Collection;

public class LicensedVideoImpl extends VideoImpl implements LicensedVideo {
    private String holder;
    private String mediaType;


    public LicensedVideoImpl(String title) {
        super(title);
    }

    public LicensedVideoImpl(int bitrate, int length, String resolution, Collection<Tag> tags, Uploader uploader,String holder) {
        super(bitrate, length, resolution, tags, uploader);
        this.holder = holder;
        this.mediaType = "LicensedVideo";
    }


    @Override
    public String getHolder() {
        return holder;
    }

    public void setHolder(String holder) {
        this.holder = holder;
    }

    @Override
    public String getMediaType() {
        return mediaType;
    }

    @Override
    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }

}
