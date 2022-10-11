import mediaDB.MediaContentUploadable;
import sim.*;

import java.util.ArrayList;

public class Simulation1<T> {
    public static void main(String[] args) {
        ArrayList<MediaContentUploadable> MediaFileList = new ArrayList<MediaContentUploadable>();
        ThreadRandomAdder t1 = new ThreadRandomAdder("Simulation1", MediaFileList);
        ThreadRemover t2 = new ThreadRemover("Simulation1", MediaFileList);
        while (true) {
            t1.run();
            t2.run();
        }
    }
}
