package gl;


import gl.Admin;
import mediaDB.AudioVideo;
import mediaDB.AudioVideoImpl;
import mediaDB.Uploader;
import mediaDB.UploaderImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AdminTest {
    //Testing create and delete and update operations using read method
    private Admin admin;
    private AudioVideo item;
    private Uploader uploader;
    private ArrayList<AudioVideo> MediaFileList;
    //Setting up the variables to use in the tests
    @BeforeEach
    void setUp() {
        admin = new Admin();
        item = new AudioVideoImpl("AdminTest");
        uploader = new UploaderImpl();
        MediaFileList = new ArrayList<>();
    }
//    @Test void createTest(){
//        admin.createMedia(item);
//        System.out.println(MediaFileList);
//        ArrayList<AudioVideo> result = admin.readMedia(MediaFileList);
//        assertEquals(1, result.size());
//    }
    @Test void deleteTest(){
        admin.createMedia(item);
        admin.deleteMedia(item);
        ArrayList<AudioVideo> result = admin.readMedia(MediaFileList);
        assertEquals(0, result.size());
    }
    @Test void updateTest(){
        admin.createMedia(item);
        long accessCount = 0;
        admin.updateMedia(item, accessCount);
        assertEquals(1, item.getAccessCount());
    }
    @Test void createUploaderTest(){
        admin.createUploader(uploader);
        ArrayList<Uploader> result = admin.readUploader();
        assertEquals(1, result.size());
    }


}
