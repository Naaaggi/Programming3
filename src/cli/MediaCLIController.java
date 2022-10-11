package cli;

import cli.events.InputEvent;
import cli.events.InputEventHandler;
import cli.events.InputEventListener;
import gl.AdminCRUD;
import mediaDB.MediaContentUploadable;
import mediaDB.Uploader;
import mediaDB.UploaderImpl;
import observer.MediaStorageObserver;
import storage.MediaStorage;
import util.Parser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class MediaCLIController implements MediaController{

    private final MediaView mediaView;
    private final MediaStorage mediaStorage;
    private final AdminCRUD adminCRUD;
    private final List<Command> commands = Arrays.asList(Command.values());
    private MediaStorageObserver mediaStorageObserver;
    private Command currentCommand = null;
    private ArrayList<Uploader> uploaderList = new ArrayList<Uploader>();

    public MediaCLIController(MediaView mediaView, MediaStorage mediaStorage, AdminCRUD adminCRUD) {
        this.mediaView = mediaView;
        this.mediaStorage = mediaStorage;
        this.adminCRUD = adminCRUD;

        InputEventHandler handler = new InputEventHandler();
        handler.add(new ChangeInputModeListener());
        handler.add(new CreateInputListener());
        this.mediaView.setHandler(handler);
    }

    @Override
    public void start() {
        mediaView.displayCommands("Welcome to the MediaDB CLI", commands);
        while (true) {
            if (currentCommand != null) {
                mediaView.readInput(">> ");
            } else {
                mediaView.readInput("Please select command: ");
            }
        }}

    class ChangeInputModeListener implements InputEventListener {
        @Override
        public void onInputEvent(InputEvent event) {
            if (event.getText() == null) {
                mediaView.displayError("Invalid input");
                return;
            }
            if (currentCommand == null && event.getText().startsWith(":")) {
                Optional<Command> command = commands.stream()
                        .filter(c -> c.getKey().equalsIgnoreCase(event.getText()))
                        .findFirst();
                if (command.isPresent()) {
                    currentCommand = command.get();
                }
                else mediaView.displayError("Please insert a valid command!");
            }
        }
    }

    class CreateInputListener implements InputEventListener {

        @Override
        public void onInputEvent(InputEvent event){
            if(event.getText() == null || event.getText().isEmpty()){
                mediaView.displayError("Invalid input");
                return;
            }
            if (currentCommand != null && !event.getText().startsWith(":") && currentCommand == Command.CREATE){
                handleCreateEvent(event);
            }
        }

        private void handleCreateEvent(InputEvent event) {
            String[] parsed = event.getText().split(" ");
            if(parsed.length == 1){
                String producer = parsed[0];
                UploaderImpl uploader = new UploaderImpl(producer);
                uploaderList = adminCRUD.readUploader();
                if(uploaderList.contains(uploader)) {
                    System.out.print("User already exists!\n");
                    return;
                }
                else adminCRUD.createUploader(uploader);
                System.out.println(adminCRUD.readUploader());
                return;
            }

            try {
                MediaContentUploadable item = Parser.parse(event.getText());
                adminCRUD.createMedia(item);
                mediaView.displayMessage("Media got uploaded successfully!");
            } catch (Exception e) {
                mediaView.displayError("Invalid input");
            }
        }
    }

}
