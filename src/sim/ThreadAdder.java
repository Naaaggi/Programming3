package sim;
import gl.*;
import mediaDB.AudioVideo;
import mediaDB.AudioVideoImpl;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;


public class ThreadAdder<T> extends Thread {
    String name;
    ArrayList<T> MediaFileList;
    Admin admin = new Admin(MediaFileList);
    Random random = new Random();
    List<String> randomFiles = Arrays.asList("InteractiveVideo Produzent1 Lifestyle,News 5000 3600 " +
            "Abstimmung 1080", "LicensedAudioVideo Produzent1 , 8000 600 EdBangerRecords 44100 720",
            "LicensedVideo Produzent1 News 1000 1800");

    public ThreadAdder(String s, ArrayList<T> MediaFileList) {
        this.name = s;
        this.MediaFileList = MediaFileList;
    }

    public void run() {
        synchronized (MediaFileList) {
            int randomIndex = random.nextInt(randomFiles.size());
            String title = randomFiles.get(randomIndex);
            AudioVideo item = new AudioVideoImpl(title);
            System.out.println("Thread adder started " + name);
            admin.createMedia(item);
            System.out.println("The MediaFile " + title + " has been added to the list");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Thread adder ended " + name);
        }
    }
}
