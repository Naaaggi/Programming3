package util;

import mediaDB.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Parser {
    public static MediaContentUploadable parse(String text) {
        String[] parsed = text.split(" ");
        String mediaType = parsed[0];
        UploaderImpl uploader = new UploaderImpl(parsed[1]);
        String Tags = parsed[2];
        ArrayList<Tag> tags = new ArrayList<Tag>();
        for (String tag : Tags.split(",")) {
            tags.add(Tag.valueOf(tag));
        }

        int bitrate = Integer.parseInt(parsed[3]);
        int length = Integer.parseInt(parsed[4]);

        switch(mediaType) {
            case "Audio": {
                int samplingRate = Integer.parseInt(parsed[5]);
                return new AudioImpl(bitrate, length, samplingRate, tags, uploader);
            }
            case "AudioVideo": {
                int samplingRate = Integer.parseInt(parsed[5]);
                String resolution = parsed[6];
                return new AudioVideoImpl(bitrate, length, samplingRate, tags, uploader, resolution);
            }
            case "Video": {
                String resolution = parsed[5];
                return new VideoImpl(bitrate, length, resolution, tags,uploader );
            }
            case "LicensedAudio": {
                int samplingRate = Integer.parseInt(parsed[5]);
                String holder = parsed[6];
                return new LicensedAudioImpl(bitrate, length, samplingRate, tags, uploader, holder);
            }

            case "LicensedVideo": {
                String resolution = parsed[5];
                String holder = parsed[6];
                return new LicensedVideoImpl(bitrate, length, resolution, tags, uploader, holder);
            }

            case "LicensedAudioVideo": {
                int samplingRate = Integer.parseInt(parsed[5]);
                String resolution = parsed[6];
                String holder = parsed[7];
                return new LicensedAudioVideoImpl(bitrate, length, samplingRate, tags, uploader, resolution, holder);
            }



            case "InteractiveVideo": {
                String resolution = parsed[5];
                String type = parsed[6];
                return new InteractiveVideoImpl(bitrate, length, resolution, tags, uploader, type);
            }


            default:
                throw new IllegalArgumentException("Invalid Media type provided");
        }
    }
}