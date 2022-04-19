package AdminImpl;

import mediaDB.AudioVideo;

import java.util.ArrayList;

public class Admin {
    private ArrayList<AudioVideo> List = new ArrayList<AudioVideo>();
    //CRUD OPERATIONS
    public void create(AudioVideo item){
        List.add(item);
    }
    public ArrayList<AudioVideo> read(){
        return List;
    }
    public void delete(AudioVideo item){
        List.remove(item);
    }
    public void update(AudioVideo item, AudioVideo newItem){
        List.set(List.indexOf(item), newItem);
    }
}
