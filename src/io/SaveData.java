package io;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import mediaDB.*;

public class SaveData implements Serializable {
    private static final long serialVersionUID = 1L;
    public ArrayList<String> list;
    public ArrayList<String> mediaList;

}

