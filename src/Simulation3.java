import mediaDB.MediaContentUploadable;
import sim.*;

import java.util.ArrayList;

public class Simulation3<T> {
    public static void main(String[] args) {
        ArrayList<MediaContentUploadable> MediaFileList = new ArrayList<MediaContentUploadable>();
        ThreadRandomAdder t1 = new ThreadRandomAdder("Simulation3", MediaFileList);
        ThreadUpdater t2 = new ThreadUpdater("Simulation3", MediaFileList);
        ThreadRemover t3 = new ThreadRemover("Simulation3", MediaFileList);
        while (true) {
            t1.run();
            t2.run();
            t3.run();
        }
    }
}