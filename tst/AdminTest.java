import AdminImpl.Admin;
import AdminImpl.AudioVideoImpl;
import mediaDB.AudioVideo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.testng.AssertJUnit.assertEquals;

public class AdminTest {
    //Testing Create and delete and update operations using read method
    private Admin admin;
    private AudioVideo item;

    //Setting up the variables to use in the tests
    @BeforeEach
    void setUp() {
        admin = new Admin();
        item = new AudioVideoImpl();
    }
    @Test void createTest(){
        admin.create(item);
        ArrayList<AudioVideo> result = admin.read();
        assertEquals(1, result.size());
    }
    @Test void deleteTest(){
        admin.create(item);
        admin.delete(item);
        ArrayList<AudioVideo> result = admin.read();
        assertEquals(0, result.size());
    }
    @Test void updateTest(){
        AudioVideo item2 = new AudioVideoImpl();
        admin.create(item);
        admin.update(item, item2);
        ArrayList<AudioVideo> result = admin.read();
        assertEquals(1, result.size());
    }

}
