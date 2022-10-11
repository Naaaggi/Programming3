package sim;
import gl.*;
import mediaDB.MediaContentUploadable;
import util.Parser;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;


public class ThreadUpdater<T> extends Thread {
    String name;
    ArrayList<MediaContentUploadable> MediaFileList = new ArrayList<MediaContentUploadable>();
    AdminCRUD<MediaContentUploadable> adminCRUD = new AdminCRUD(MediaFileList);
    Random random = new Random();
    List<String> randomFiles = Arrays.asList("InteractiveVideo Produzent1 Lifestyle,News 5000 3600 " +
                    "Abstimmung 1080", "LicensedAudioVideo Produzent1 News 8000 600 44100 720 EdBangerRecords",
            "LicensedVideo Produzent1 News 1000 1800 720 Dragon");

    public ThreadUpdater(String s, ArrayList<MediaContentUploadable> MediaFileList) {
        this.name = s;
        this.MediaFileList = MediaFileList;
    }

    public void run() {
        synchronized (MediaFileList) {
            for(int index=0; index<randomFiles.size(); index++) {
                String title = randomFiles.get(index);
                MediaContentUploadable item = Parser.parse(title);
                System.out.println("Thread adder started " + name);
                adminCRUD.createMedia(item);
                System.out.println("The MediaFile " + item + " has been added to the list");
            }
            int randomIndex = random.nextInt(randomFiles.size());
            String title = randomFiles.get(randomIndex);
            MediaContentUploadable item = Parser.parse(title);
            adminCRUD.updateMedia(item, item.getAccessCount());
            System.out.println("The MediaFile " + item + " has been updated");

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            System.out.println("Thread adder ended " + name);
        }
    }
}
