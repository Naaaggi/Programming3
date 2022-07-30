package gl;

import mediaDB.AudioVideo;
import mediaDB.AudioVideoImpl;
import mediaDB.MediaContent;
import mediaDB.Uploader;

import java.util.UUID;

import java.util.ArrayList;

public class Admin<T>{
    private ArrayList<MediaContent> MediaFileList = new ArrayList<>();
    private ArrayList<Uploader> UploaderList = new ArrayList<>();

    public Admin(ArrayList<MediaContent> MediaFileList) {
        this.MediaFileList = MediaFileList;
    }
    public Admin(ArrayList<MediaContent> MediaFileList, ArrayList<Uploader> UploaderList) {
        this.MediaFileList = MediaFileList;
        this.UploaderList = UploaderList;
    }
    //CRUD OPERATIONS
    public abstract class Property<T> {
        T value;
        public abstract void setValue(String input);
    }
    public class StringProperty extends Property<String> {
        @Override
        public void setValue(String input) {
            this.value = input;
        }
    }

    public void createMedia(MediaContent item){
        item.setAddress(getAddress());
        MediaFileList.add(item);
    }

    public void createUploader(Uploader name){
        this.UploaderList.add(name);
    }
    public ArrayList<Uploader> readUploader(){
        return UploaderList;
    }
    public ArrayList<T> readMedia(ArrayList<T> MediaFileList){
        return MediaFileList;
    }
    public void deleteMedia(T item){
        MediaFileList.remove(item);
    }
    public void updateMedia(AudioVideo item, int accessCount){
        accessCount += 1;
       item.setAccessCount(accessCount);
    }
    private String getAddress() {
        return UUID.randomUUID().toString();
    }
}
