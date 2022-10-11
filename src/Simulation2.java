import sim.*;

import java.util.ArrayList;

public class Simulation2<T> {
    public static void main(String[] args) {
        ArrayList MediaFileList = new ArrayList<>();
        ThreadAdder t1 = new ThreadAdder("Simulation2", MediaFileList);
        ThreadRemover t2 = new ThreadRemover("Simulation2", MediaFileList);
        while (true) {
            t1.run();
            t2.run();
        }
    }
}