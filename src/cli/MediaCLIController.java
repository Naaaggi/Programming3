package cli;

import cli.events.ExitEventListener;
import cli.events.InputEvent;
import cli.events.InputEventHandler;
import cli.events.InputEventListener;
import gl.AdminCRUD;
import gl.io.ResourceManager;
import gl.io.SaveData;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import mediaDB.MediaContent;
import mediaDB.MediaContentUploadable;
import mediaDB.Uploader;
import mediaDB.UploaderImpl;
import observer.MediaStorageObserver;
import storage.MediaStorage;
import storage.NoStorageException;
import util.Parser;

import java.io.IOException;
import java.util.*;

public class MediaCLIController implements MediaController{

    private final MediaView mediaView;
    private final MediaStorage mediaStorage;
    private final AdminCRUD adminCRUD;
    private final List<Command> commands = Arrays.asList(Command.values());
    private MediaStorageObserver mediaStorageObserver;
    private Command currentCommand = null;
    private ArrayList<Uploader> uploaderList = new ArrayList<Uploader>();
    private ArrayList<MediaContentUploadable> mediaList = new ArrayList<MediaContentUploadable>();
    ArrayList<String> textList = new ArrayList<String>();

    public MediaCLIController(MediaView mediaView, MediaStorage mediaStorage, AdminCRUD adminCRUD) {
        this.mediaView = mediaView;
        this.mediaStorage = mediaStorage;
        this.adminCRUD = adminCRUD;

        InputEventHandler handler = new InputEventHandler();
        handler.add(new ChangeInputModeListener());
        handler.add(new CreateInputListener());
        handler.add(new ReadInputListener());
        handler.add(new UpdateInputListener());
        handler.add(new DeleteInputListener());
        handler.add(new PersistanceInputListener());
        handler.add(new ExitEventListener());
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
        if (currentCommand == null || event.getText().startsWith(":")) {
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

    // create uploader or media
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
            String text = event.getText();
            textList.add(text);
            //add uploader
            if(parsed.length == 1){
                String producer = parsed[0];
                uploaderList = adminCRUD.readUploader();
                Uploader uploader = new UploaderImpl(producer);
                for(Uploader u : uploaderList){
                    if(u.getName().equals(uploader.getName())){
                        mediaView.displayError("Uploader already exists!");
                        return;
                    }
                }
                adminCRUD.createUploader(uploader);
                return;
            }
        //add media
            try {
                String producer = parsed[1];
                uploaderList = adminCRUD.readUploader();
                Uploader uploader = new UploaderImpl(producer);
                for(Uploader u : uploaderList){
                    if(u.getName().equals(uploader.getName())){
                        MediaContentUploadable item = Parser.parse(event.getText());
                        adminCRUD.createMedia(item);
                        System.out.println(item);
                        mediaStorage.addMediaInStorage(item.getSize());
                        mediaView.displayMessage("Media got uploaded successfully!");
                        return;
                    }
                }
                mediaView.displayError("Uploader does not exist! Add an uploader to the uploader list first!");
                return;
            } catch (IllegalArgumentException | NoStorageException e) {
               if (e instanceof IllegalArgumentException) {
                   mediaView.displayError("Please enter a valid media file following the format: " +
                           "\nMediaType Producer Tags Bitrate Length SamplingRate(If Audio) Resolution(If Video) Type(If InteractiveVideo) Holder(If LicensedMedia)");
               } else {
                   mediaView.displayError(e.getMessage());
               }
            }

        }
    }
    // show uploader list or media list
    class ReadInputListener implements InputEventListener {

        @Override
        public void onInputEvent(InputEvent event){
            if(event.getText() == null || event.getText().isEmpty()){
                mediaView.displayError("Invalid input");
                return;
            }
            if (currentCommand != null && !event.getText().startsWith(":") && currentCommand == Command.READ){
                handleReadEvent(event);
            }
        }

        private void handleReadEvent(InputEvent event) {
            String[] parsed = event.getText().split(" ");
            //read uploader
            if (parsed[0].equalsIgnoreCase("uploader")){
                if (adminCRUD.readUploader().size() == 0){
                    mediaView.displayError("No uploader found!");
                    return;
                }
                else mediaView.displayMessage(adminCRUD.readUploader().toString());
            }
            //read media
            else if (parsed[0].equalsIgnoreCase("content")){
                if (adminCRUD.readMedia().size() == 0){
                    mediaView.displayError("No Media Content found!");
                }
                else {
                    mediaList = adminCRUD.readMedia();
                    String[] types = new String[mediaList.size()];
                    String[] addresses = new String[mediaList.size()];
                    Date[] uploadDates = new Date[mediaList.size()];
                    int[] accessCounts = new int[mediaList.size()];
                    for (int i = 0; i < mediaList.size(); i++){
                        types[i] = mediaList.get(i).getMediaType();
                        addresses[i] = mediaList.get(i).getAddress();
                        uploadDates[i] = mediaList.get(i).getUploadDate();
                        accessCounts[i] = mediaList.get(i).getAccessCount();
                    }
                    mediaView.displayMedia(types, addresses, uploadDates, accessCounts);
                    return;
                }

            }
            //tags no implemented ://
            else mediaView.displayError("Please enter a valid command to read! (uploader or content)");
        }
    }

    //update media
    class UpdateInputListener implements InputEventListener {

        @Override
        public void onInputEvent(InputEvent event) {
            if (event.getText() == null || event.getText().isEmpty()) {
                mediaView.displayError("Invalid input");
                return;
            }
            if (currentCommand != null && !event.getText().startsWith(":") && currentCommand == Command.UPDATE) {
                handleUpdateEvent(event);
            }
        }

        private void handleUpdateEvent(InputEvent event) {
            MediaContent mediaContent = adminCRUD.getMediaByAddress(event.getText());
            if (mediaContent == null) {
                mediaView.displayError("Media with this address is not found! Please enter a valid address!");
            }
            else {
                adminCRUD.updateMedia(mediaContent, mediaContent.getAccessCount());
                mediaView.displayMessage("The media got updated successfully! The new access count is: " + mediaContent.getAccessCount());
            }
        }
    }
    //delete media
    class DeleteInputListener implements InputEventListener {

        @Override
        public void onInputEvent(InputEvent event) {
            if (event.getText() == null || event.getText().isEmpty()) {
                mediaView.displayError("Invalid input");
                return;
            }
            if (currentCommand != null && !event.getText().startsWith(":") && currentCommand == Command.DELETE) {
                handleDeleteEvent(event);
            }
        }

        private void handleDeleteEvent(InputEvent event) {
            MediaContent mediaContent = adminCRUD.getMediaByAddress(event.getText());
            if (mediaContent == null) {
                mediaView.displayError("Media with this address is not found! Please enter a valid address!");
            }
            else {
                adminCRUD.deleteMedia(mediaContent);
                mediaStorage.deletedMediaFromStorage(mediaContent.getSize());
                mediaView.displayMessage("The media got deleted successfully!");
            }
        }
    }

    //save or load media or uploader list
    class PersistanceInputListener implements InputEventListener {

        @Override
        public void onInputEvent(InputEvent event) {
            if (event.getText() == null || event.getText().isEmpty()) {
                mediaView.displayError("Invalid input");
                return;
            }
            if (currentCommand != null && !event.getText().startsWith(":") && currentCommand == Command.PERSISTENCE) {
                handlePersistanceEvent(event);
            }
        }

        private void handlePersistanceEvent(InputEvent event) {
            String[] parsed = event.getText().split(" ");
            try {
                if (parsed[0].equalsIgnoreCase("save")) {
                    if (parsed[1].equalsIgnoreCase("uploader")) {
                        //save uploader
                        SaveData data1 = new SaveData();
                        ArrayList<Uploader> uploaderList = adminCRUD.readUploader();
                        ArrayList<String> uploaderNames = new ArrayList<>();
                        for (Uploader u : uploaderList) {
                            uploaderNames.add(u.getName());
                        }
                        data1.list = uploaderNames;
                        try {
                            ResourceManager.saveJOS(data1, "UploadFileList.txt");
                            mediaView.displayMessage("Uploader list got saved successfully!");
                        } catch (Exception e) {
                            mediaView.displayError(e.getMessage());
                        }
                    } else if (parsed[1].equalsIgnoreCase("content")) {
                        //save media
                        SaveData data = new SaveData();
                        data.mediaList = textList;
                        try {
                            ResourceManager.saveJOS(data, "MediaFileList.txt");
                            mediaView.displayMessage("Media list got saved successfully!");
                        } catch (Exception e) {
                            mediaView.displayError("Error saving media list!");
                        }
                    } else mediaView.displayError("Please enter a valid command to save! (uploader or content)");
                }
            } catch (Exception e) {
                mediaView.displayError("Please enter a valid command to save! (uploader or content)");
            }

            try {
                if (parsed[0].equalsIgnoreCase("load")) {
                    if (parsed[1].equalsIgnoreCase("uploader")) {
                        //load uploader
                        try {
                            SaveData data1 = (SaveData) ResourceManager.loadJOS("UploadFileList.txt");
                            for (String s : data1.list) {
                                Uploader uploader = new UploaderImpl(s);
                                adminCRUD.createUploader(uploader);
                            }
                            mediaView.displayMessage("Uploader list got loaded successfully!" + data1.list);
                        } catch (Exception e) {
                            mediaView.displayError("Error loading uploader list!");
                        }
                    } else if (parsed[1].equalsIgnoreCase("content")) {
                        try {
                            SaveData data = (SaveData) ResourceManager.loadJOS("MediaFileList.txt");
                            mediaList.clear();
                            for (String text : data.mediaList) {
                                MediaContentUploadable item = Parser.parse(text);
                                adminCRUD.createMedia(item);
                            }
                            mediaView.displayMessage("Media list got loaded successfully!\n");

                        } catch (Exception e) {
                            mediaView.displayError(e.getMessage());
                        }
                    } else mediaView.displayError("Please enter a valid command to load! (uploader or content)");
                }
            } catch (Exception e) {
               mediaView.displayError("Please enter a valid command to save or load! (save or load)");
            }
        }
    }
}
