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
    public void deleteMedia(MediaContent item){
        MediaFileList.remove(item);
    }
    public void updateMedia(MediaContent item, int accessCount){
       item.setAccessCount(accessCount+1);
    }
    public MediaContent getMediaByAddress(String address){
        for (MediaContent mediaContent : MediaFileList) {
            if (mediaContent.getAddress().equals(address)) {
                return mediaContent;
            }
        }
        return null;
    }
}
