import cli.CLIMediaView;
import cli.Console;
import cli.MediaCLIController;
import cli.MediaView;
import gl.AdminCRUD;
import mediaDB.LicensedVideo;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import storage.MediaStorage;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


public class ListenersTest {

    @Test
    void testCreateEvent() {

        String createLicensedVideo ="LicensedVideo Produzent1 News 1000 1800 720 Dragon";
        Console console = Mockito.mock(Console.class);
        String readModeTitle = "Mode:";
        when(console.readStringFromStdin(readModeTitle)).thenReturn(":c");
        String readCommandTitle = ">>";
        when(console.readStringFromStdin(readCommandTitle)).thenReturn(createLicensedVideo);

        MediaView mediaView = new CLIMediaView(console);

        AdminCRUD adminCRUD = Mockito.mock(AdminCRUD.class);
        MediaStorage mediaStorage = Mockito.mock(MediaStorage.class);
        new MediaCLIController(mediaView, mediaStorage, adminCRUD);

        mediaView.readInput(readModeTitle);
        mediaView.readInput(readCommandTitle);
        Mockito.verify(adminCRUD).createMedia(any(LicensedVideo.class));
    }

    @Test
    void testDeleteEvent()  {
        String deletedProducterName = "Produzent1";
        Console console = Mockito.mock(Console.class);
        String readModeTitle = "Mode:";
        when(console.readStringFromStdin(readModeTitle)).thenReturn(":d");
        String readCommandTitle = ">>";
        when(console.readStringFromStdin(readCommandTitle)).thenReturn(deletedProducterName);

        MediaView mediaView = new CLIMediaView(console);

        AdminCRUD adminCRUD = Mockito.mock(AdminCRUD.class);
        MediaStorage mediaStorage = Mockito.mock(MediaStorage.class);
        new MediaCLIController(mediaView, mediaStorage, adminCRUD);

        mediaView.readInput(readModeTitle);
        mediaView.readInput(readCommandTitle);
        Mockito.verify(adminCRUD).deleteUploaderUsingName(deletedProducterName);
    }
}
