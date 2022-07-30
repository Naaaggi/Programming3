import gl.Admin;
import mediaDB.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CRUDTest {
    //Testing create and delete and update operations using read method
    private Admin admin;
    private AudioVideo item;
    private Uploader uploader;
    private ArrayList<AudioVideo> MediaFileList;
    //Setting up the variables to use in the tests
    @BeforeEach
    void setUp() {
        uploader = new UploaderImpl("Producer1");
        item = new AudioVideoImpl(5000, 3600, 3000, Collections.singleton(Tag.News), uploader,"1080x720");
        MediaFileList = new ArrayList<>();
        admin = new Admin(MediaFileList);

    }
    @Test void createTest(){
        admin.createMedia(item);
        System.out.println(MediaFileList);
        ArrayList<AudioVideo> result = admin.readMedia(MediaFileList);
        assertEquals(1, result.size());
    }
    @Test void deleteTest(){
        admin.createMedia(item);
        admin.deleteMedia(item);
        ArrayList<AudioVideo> result = admin.readMedia(MediaFileList);
        assertEquals(0, result.size());
    }
    @Test void updateTest(){
        admin.createMedia(item);
        admin.updateMedia(item, item.getAccessCount());
        assertEquals(1, item.getAccessCount());
    }
    @Test void createUploaderTest(){
        admin.createUploader(uploader);
        ArrayList<Uploader> result = admin.readUploader();
        assertEquals(1, result.size());
    }


}
