package observer;

import storage.MediaStorage;

public class MediaStorageObserver implements Observer {

    private MediaStorage mediaStorage;

    public MediaStorageObserver(MediaStorage mediaStorage) {
        this.mediaStorage = mediaStorage;
    }

    @Override
    public void updateObserver() {
        float freeSizePercent = mediaStorage.getAvailableMediaStorageInMB()/(mediaStorage.getDiskSize()) * 100;

        if (freeSizePercent <= 10) {
            System.out.println("\u26A0 Warning: " + (100 - freeSizePercent) + "% of Storage is Used ");
        }
    }


}
