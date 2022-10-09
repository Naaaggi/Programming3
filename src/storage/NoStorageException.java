package storage;

public class NoStorageException extends Exception {

    @Override
    public String getMessage() {
        return "No Storage";
    }
}

