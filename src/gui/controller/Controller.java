package gui.controller;

import gl.Admin;
import io.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import mediaDB.*;
import util.Parser;


import java.net.URL;
import java.util.*;
import java.util.ArrayList;


public class Controller implements Initializable {
    Alert errorAlert = new Alert(Alert.AlertType.ERROR);
    ArrayList<String> textList = new ArrayList<String>();

    @FXML private TableView<MediaContentUploadable> MediaFileList;
    @FXML private ListView<String> UploaderList;
    @FXML private TextField MediaFileName;
    @FXML private TableColumn<MediaContentUploadable, String> typeColumn;
    @FXML private TableColumn<MediaContentUploadable, String> producerColumn;
    @FXML private TableColumn<MediaContentUploadable, String> addressColumn;
    @FXML private TableColumn<MediaContentUploadable, Integer> sizeColumn;
    @FXML private TableColumn<MediaContentUploadable, Date> dateColumn;
    @FXML private TableColumn<MediaContentUploadable, Long> accessCountColumn;
    Admin<MediaContentUploadable> admin = new Admin<MediaContentUploadable>(MediaFileList,UploaderList);




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        typeColumn.setCellValueFactory(new PropertyValueFactory<MediaContentUploadable,String>("mediaType"));
        producerColumn.setCellValueFactory(new PropertyValueFactory<MediaContentUploadable,String>("uploader"));
        addressColumn.setCellValueFactory(new PropertyValueFactory<MediaContentUploadable,String>("address"));
        sizeColumn.setCellValueFactory(new PropertyValueFactory<MediaContentUploadable,Integer>("size"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<MediaContentUploadable,Date>("uploadDate"));
        accessCountColumn.setCellValueFactory(new PropertyValueFactory<MediaContentUploadable,Long>("accessCount"));

    }

    //Create Media Button
    @FXML
    void createMedia(MouseEvent event) {
        try {
            String text = MediaFileName.getText();
            textList.add(text);
            String[] parsed = text.split(" ");
            String producer = parsed[1];
            UploaderImpl uploader = new UploaderImpl(producer);
            MediaContentUploadable item = Parser.parse(text);
            ObservableList<MediaContentUploadable> items = MediaFileList.getItems();
            items.add(item);
            System.out.println(items);
            MediaFileList.setItems(items);
            admin.createMedia(item);

            if (UploaderList.getItems().contains(producer)) {
                //user already exists
            } else {
                UploaderList.getItems().add(producer);
                admin.createUploader(uploader);
            }
            System.out.println("Media added: " + MediaFileName.getText() + " by " + parsed[1]);
        } catch (IllegalArgumentException | IndexOutOfBoundsException  e) {
            errorAlert.setWidth(500);
            errorAlert.setHeight(300);
            errorAlert.setTitle("Error");
            errorAlert.setHeaderText("Error");
            errorAlert.setContentText("Please enter a valid media file following the format: " +
                    "\nMediaType Producer Tags Bitrate Length SamplingRate(If Audio) Resolution(If Video) Type(If InteractiveVideo) Holder(If LicensedMedia)");
            errorAlert.showAndWait();
        }

    }
    @FXML void updateMedia(MouseEvent event) {
        try {
            int selectedID = MediaFileList.getSelectionModel().getSelectedIndex();
            MediaContentUploadable item = MediaFileList.getItems().get(selectedID);
            item.setAccessCount(item.getAccessCount());
            admin.updateMedia(item, item.getAccessCount());
            System.out.println("Media with the address " + MediaFileList.getItems().get(selectedID).getAddress()
                    + " was accessed " + item.getAccessCount() + " times");
            MediaFileList.refresh();
        } catch (IndexOutOfBoundsException e) {
            errorAlert.setWidth(500);
            errorAlert.setHeight(300);
            errorAlert.setTitle("Error");
            errorAlert.setHeaderText("Error");
            errorAlert.setContentText("Please select a media file to update");
            errorAlert.showAndWait();
        }

    }
    @FXML
    void deleteMedia(MouseEvent event) {
        if (MediaFileList.getItems().size() == 0) {
            System.out.println("Media List is empty.");
            errorAlert.setContentText("Can't use this button.\nReason: Media List is empty.");
            errorAlert.showAndWait();
        } else {
            try {
                int selectedID = MediaFileList.getSelectionModel().getSelectedIndex();
                if (selectedID == -1) {
                    errorAlert.setContentText("Please select a media file to delete");
                    errorAlert.showAndWait();
                } else {
                    MediaContentUploadable item = MediaFileList.getItems().get(selectedID);
                    admin.deleteMedia(item);
                    System.out.println("Media with the address " + MediaFileList.getItems().get(selectedID).getAddress()
                            + " was deleted");
                    MediaFileList.getItems().remove(selectedID);
                }
            } catch (IndexOutOfBoundsException ignored) {
            }

        }
    }
    @FXML
    void deleteRandomMedia(MouseEvent event) {
        Random rand = new Random();
        if(MediaFileList.getItems().size() ==0){
            System.out.println("Media List is empty.");
            errorAlert.setWidth(500);
            errorAlert.setHeight(300);
            errorAlert.setTitle("Error");
            errorAlert.setHeaderText("Error");
            errorAlert.setContentText("Can't use this button.\nReason: Media List is empty.");
            errorAlert.showAndWait();
        }
        else{
            try {
                int randomID = rand.nextInt(MediaFileList.getItems().size());
                MediaContentUploadable item = MediaFileList.getItems().get(randomID);
                admin.deleteMedia(item);
                MediaFileList.getItems().remove(randomID);
                System.out.println("Media with the address " + MediaFileList.getItems().get(randomID).getAddress() + " was deleted");
            } catch ( IndexOutOfBoundsException ignored) {
            }


    }}



    @FXML
    void saveData(MouseEvent event) {
        SaveData data = new SaveData();
        SaveData data1 = new SaveData();

        data.mediaList = textList;
        data1.list = new ArrayList<String>(UploaderList.getItems());

        try {
            ResourceManager.save(data, "MediaFileList.txt");
            ResourceManager.save(data1, "UploadFileList.txt");


        }
        catch (Exception e) {
            System.out.println("Couldn't save: " + e.getMessage());
        }
    }

    @FXML
    void loadData(MouseEvent event) {
        try {
            // Load media list
            SaveData data = (SaveData) ResourceManager.load("MediaFileList.txt");
            System.out.println(data.mediaList);
            MediaFileList.getItems().clear();
            ObservableList<MediaContentUploadable> items = FXCollections.observableArrayList();
            for (String text : data.mediaList) {
                MediaContentUploadable item = Parser.parse(text);
                items.add(item);
                MediaFileList.setItems(items);
                admin.createMedia(item);
            }

            // Load uploader list
            SaveData data1 = (SaveData) ResourceManager.load("UploadFileList.txt");
            System.out.println(data1.list);
            ObservableList<String> readUploadList = FXCollections.observableArrayList(data1.list);
            UploaderList.setItems(readUploadList);


        }
        catch (Exception e) {
            System.out.println("Couldn't load save data: " + e.getMessage());
        }
    }


}