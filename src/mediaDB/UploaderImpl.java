package mediaDB;

public class UploaderImpl implements Uploader {
    private String name;
    public  UploaderImpl(String name) {this.name = name;}
    public String getName() {return name;}
    public void setName(String name) {this.name = name;}

    @Override
    public String toString() {
        return name;
    }
}
