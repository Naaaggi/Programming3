package mediaDB;

import java.util.Collection;

public class LicensedAudioVideoImpl extends AudioVideoImpl implements LicensedAudioVideo {
    private String mediaType;
    private String holder;

    public LicensedAudioVideoImpl(String title) {
        super(title);
    }

    public LicensedAudioVideoImpl(int bitrate, int length, int samplingRate, Collection<Tag> tags, Uploader uploader, String resolution, String holder) {
        super(bitrate, length, samplingRate, tags, uploader, resolution);
        this.mediaType = "LicensedAudioVideo";
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

    @Override
    public String getHolder() {
        return holder;
    }

    public void setHolder(String holder) {
        this.holder = holder;
    }
}
