package gl;

import mediaDB.AudioVideo;
import mediaDB.AudioVideoImpl;

import java.util.ArrayList;

public class Admin<T>{
    private ArrayList<T> MediaFileList = new ArrayList<>();
    private ArrayList<T> UploaderList = new ArrayList<>();
    public Admin(ArrayList<T> MediaFileList) {
        this.MediaFileList = MediaFileList;
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

    public void createMedia(T item){
        MediaFileList.add(item);
    }

    public void createUploader(T name){
        this.UploaderList.add(name);
    }
    public ArrayList<T> readUploader(){
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
}
