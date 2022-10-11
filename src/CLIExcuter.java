import cli.*;
import gl.AdminCRUD;
import mediaDB.MediaContentUploadable;
import mediaDB.Uploader;
import observer.MediaStorageObserver;
import storage.MediaStorage;

import java.util.ArrayList;

public class CLIExcuter {
    public static void main(String[] args) {
        Console console = new Console();
        long diskSize = console.readLongFromStdin("Enter Disk size in gigabyte: ");
        MediaStorage mediaStorage = new MediaStorage(diskSize * 1000);
        MediaStorageObserver observer = new MediaStorageObserver(mediaStorage);
        ArrayList<MediaContentUploadable> MediaFileList = new ArrayList<>();
        ArrayList<Uploader> UploaderList = new ArrayList<>();

        AdminCRUD<MediaContentUploadable> adminCRUD = new AdminCRUD(MediaFileList, UploaderList);
        MediaView mediaView = new CLIMediaView(console);
        MediaController mediaController = new MediaCLIController(mediaView, mediaStorage, adminCRUD);
        mediaController.start();


    }
}
