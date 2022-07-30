package io;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import mediaDB.*;

public class SaveData implements Serializable {
    private static final long serialVersionUID = 1L;
    public ArrayList<String> name;
    public String address;
    public String mediaType;
    public Uploader uploader;
    public int size;
    public int accessCount;
    public Date uploadDate;

}

