package sim;

import gl.AdminCRUD;
import mediaDB.AudioVideo;
import mediaDB.AudioVideoImpl;

import java.util.ArrayList;
import java.util.Random;

public class ThreadRemover<T> extends Thread {
    String name;
    ArrayList<T> MediaFileList;
    AdminCRUD admin = new AdminCRUD(MediaFileList);
    AudioVideo item = new AudioVideoImpl("ThreadRemove");
    Random random = new Random();
    public ThreadRemover(String s, ArrayList<T> MediaFileList) {
        this.name = s;
        this.MediaFileList = MediaFileList;
    }

    public void run() {
        synchronized (MediaFileList) {
            System.out.println("Thread remover started " + name);
            ArrayList<AudioVideo> result = admin.readMedia(MediaFileList);

                try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            if (result.size() == 0) {
                System.out.println("No media files left");
            }
            else {
                int randomIndex = random.nextInt(result.size());
                admin.deleteMedia(result.get(randomIndex));
                System.out.println(item + " removed from the List");
            }


            System.out.println("Thread remover ended " + name);

        }

    }
}

