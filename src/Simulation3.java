import gl.Admin;
import mediaDB.AudioVideo;
import mediaDB.AudioVideoImpl;
import sim.*;

import java.util.ArrayList;

public class Simulation3<T> {
    public static void main(String[] args) {
        ArrayList MediaFileList = new ArrayList<>();
        ThreadAdder t1 = new ThreadAdder("Simulation3", MediaFileList);
        ThreadRemover t2 = new ThreadRemover("Simulation3", MediaFileList);
        while (true) {
            t1.run();
            t2.run();
        }
    }
}