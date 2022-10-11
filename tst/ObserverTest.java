import mediaDB.InteractiveVideo;
import observer.Observer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import storage.MediaStorage;
import storage.NoStorageException;

import static org.mockito.Mockito.*;

public class ObserverTest {
    private MediaStorage mediaStorage;

    @BeforeEach
    void setup() {
        this.mediaStorage = new MediaStorage(1 * 1024);
    }

    @Test
    void testMediaStorageNotifiesObserver() throws NoStorageException {
        Observer observer = Mockito.mock(Observer.class);
        InteractiveVideo interactiveVideo = Mockito.mock(InteractiveVideo.class);
        long storage = mediaStorage.getAvailableMediaStorageInMB();
        when(interactiveVideo.getSize()).thenReturn((int) storage);
        mediaStorage.addMediaInStorage(interactiveVideo.getSize());
        verify(observer, times(1)).updateObserver();
    }


}
