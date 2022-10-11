package gl;

import javafx.scene.control.ListView;
import javafx.scene.control.TableView;
import mediaDB.*;

import java.util.ArrayList;

public class AdminCRUD<T>{
    private ArrayList<MediaContent> MediaFileList = new ArrayList<>();
    private ArrayList<Uploader> UploaderList = new ArrayList<>();

    public AdminCRUD(ArrayList<MediaContent> MediaFileList) {
        this.MediaFileList = MediaFileList;
    }
    public AdminCRUD(ArrayList<MediaContent> MediaFileList, ArrayList<Uploader> UploaderList) {
        this.MediaFileList = MediaFileList;
        this.UploaderList = UploaderList;
    }
    public AdminCRUD(TableView<MediaContentUploadable> mediaFileList, ListView<String> uploaderList) {
    }

    //CRUD OPERATIONS
    public void createMedia(MediaContent item){
        MediaFileList.add(item);
    }
    public void createUploader(Uploader name){
        this.UploaderList.add(name);
    }
    public ArrayList<Uploader> readUploader(){
        return UploaderList;
    }
    public ArrayList<MediaContent> readMedia(){
        return MediaFileList;
    }
    public void deleteMedia(T item){
        MediaFileList.remove(item);
    }
    public void updateMedia(MediaContentUploadable item, int accessCount){
       item.setAccessCount(accessCount+1);
    }
}
