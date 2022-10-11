import gl.AdminCRUD;
import mediaDB.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import util.Parser;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;


public class ParserTest {
    private MediaContentUploadable item;

    @BeforeEach
    void setUp() {
        String itemToCreateAsText = "AudioVideo Nagi News 1024 800 5000 1080x720";
        item = Parser.parse(itemToCreateAsText);
    }

    @Test
    void parseTest() {
        Assertions.assertEquals("AudioVideo", item.getMediaType());
        assertEquals(100, item.getSize());
        Assertions.assertEquals(800, item.getLength());
        Assertions.assertEquals(1024, item.getBitrate());
    }

}
