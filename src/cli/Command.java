package cli;

public enum Command {
    CREATE(":c", "Change to insert mode"),
    VIEW(":r", "Change to view mode"),
    DELETE(":d","Change to delete mode" ),
    UPDATE(":u", "Change to update mode"),
    PERSISTENCE(":p","Change to persistence mode");

    private String key;
    private String description;

    Command(String key, String description) {
        this.key = key;
        this.description = description;
    }

    @Override
    public String toString() {
        return key + ' ' + description;
    }

    public String getKey() {
        return key;
    }
}
