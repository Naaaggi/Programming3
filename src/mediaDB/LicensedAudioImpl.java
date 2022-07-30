package mediaDB;

import java.util.Collection;

public class LicensedAudioImpl extends AudioImpl implements LicensedAudio {
    private String holder;
    private String mediaType;

    public LicensedAudioImpl(String title) {
        super(title);
    }

    public LicensedAudioImpl(int bitrate, int length, int samplingRate, Collection<Tag> tags, Uploader uploader, String holder) {
        super(bitrate, length, samplingRate, tags, uploader);
        this.holder = holder;
        this.mediaType = "LicensedAudio";
    }

    @Override
    public String getMediaType() {
        return mediaType;
    }

    @Override
    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }

    public String getHolder() {
        return holder;
    }

    public void setHolder(String holder) {
        this.holder = holder;
    }
}

