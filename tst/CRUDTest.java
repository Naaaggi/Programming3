import gl.AdminCRUD;
import mediaDB.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import util.Parser;

import java.util.ArrayList;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;


public class CRUDTest {
    //Testing create and delete and update operations using read method
    private AdminCRUD admin;
    private MediaContentUploadable item;
    private Uploader uploader;
    private ArrayList<MediaContentUploadable> MediaFileList;
    //Setting up the variables to use in the tests

    @BeforeEach
    void setUp() {
        uploader = new UploaderImpl("Producer1");
        String itemToCreateAsText = "AudioVideo Nagi News 1024 800 5000 1080x720";
        item = Parser.parse(itemToCreateAsText);
        MediaFileList = new ArrayList<>();
        admin = new AdminCRUD(MediaFileList);

    }
    @Test void createTest(){
        admin.createMedia(item);
        System.out.println(MediaFileList);
        ArrayList<MediaContentUploadable> result = admin.readMedia();
        assertEquals(1, result.size());
    }
    @Test void deleteTest(){
        admin.createMedia(item);
        admin.deleteMedia(item);
        ArrayList<MediaContentUploadable> result = admin.readMedia();
        assertEquals(0, result.size());
    }
    @Test void updateTest(){
        admin.createMedia(item);
        admin.updateMedia(item, item.getAccessCount());
        assertEquals(1, item.getAccessCount());
    }
    @Test void createUploaderTest(){
        admin.createUploader(uploader);
        ArrayList<MediaContentUploadable> result = admin.readUploader();
        assertEquals(1, result.size());
    }
    public  AudioVideo getMockAudioVideo() {
        String itemToCreateAsText = "AudioVideo Nagi News 1024 800 5000 1080x720";
        AudioVideo audioVideo = Mockito.mock(AudioVideo.class);
        when(audioVideo.getAddress()).thenReturn("InteractiveVideo@" + System.currentTimeMillis());
        when(audioVideo.getUploadDate()).thenReturn(new Date());
        return audioVideo;
    }
}
