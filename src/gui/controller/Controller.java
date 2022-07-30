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

    @FXML private TableView<MediaContentUploadable> MediaFileList;
    @FXML private ListView<String> UploaderList;
    @FXML private TextField MediaFileName;
    @FXML private TableColumn<MediaContentUploadable, String> typeColumn;
    @FXML private TableColumn<MediaContentUploadable, String> producerColumn;
    @FXML private TableColumn<MediaContentUploadable, String> addressColumn;
    @FXML private TableColumn<MediaContentUploadable, Integer> sizeColumn;
    @FXML private TableColumn<MediaContentUploadable, Date> dateColumn;
    @FXML private TableColumn<MediaContentUploadable, Long> accessCountColumn;
    Admin admin = new Admin(MediaFileList,UploaderList);




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
            String[] parsed = text.split(" ");
            String producer = parsed[1];
            UploaderImpl uploader = new UploaderImpl(producer);
            MediaContentUploadable item = Parser.parse(text);
            System.out.println(item);
            ObservableList<MediaContentUploadable> items = MediaFileList.getItems();
            items.add(item);
            MediaFileList.setItems(items);
            admin.createMedia(item);

            if (UploaderList.getItems().contains(producer)) {
                //user already exists
            } else {
                UploaderList.getItems().add(producer);
                admin.createUploader(uploader);
            }
            System.out.println("Media added: " + MediaFileName.getText() + " by " + parsed[1]);
        } catch (IllegalArgumentException | ArrayIndexOutOfBoundsException e) {
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
        int selectedID = MediaFileList.getSelectionModel().getSelectedIndex();
        MediaContentUploadable item = MediaFileList.getItems().get(selectedID);
        item.setAccessCount(item.getAccessCount());
        admin.updateMedia(item, item.getAccessCount());
        System.out.println("Media with the address " + MediaFileList.getItems().get(selectedID).getAddress()
                + " was accessed " + item.getAccessCount() + " times");
        MediaFileList.refresh();

    }
    @FXML
    void deleteMedia(MouseEvent event) {
        if (MediaFileList.getItems().size() == 0) {
            System.out.println("Media List is empty.");
            errorAlert.setContentText("Can't use this button.\nReason: Media List is empty.");
            errorAlert.showAndWait();
        } else {
            int selectedID = MediaFileList.getSelectionModel().getSelectedIndex();
            MediaFileList.getItems().remove(selectedID);
            admin.deleteMedia(MediaFileList.getItems().get(selectedID));
            System.out.println("Media with the address " + MediaFileList.getItems().get(selectedID).getAddress() + " removed.");
        }
    }
    @FXML
    void deleteRandomMedia(MouseEvent event) {
        Random rand = new Random();
        if(MediaFileList.getItems().size() ==0){
            System.out.println("Media List is empty.");
            errorAlert.setContentText("Can't use this button.\nReason: Media List is empty.");
            errorAlert.showAndWait();
        }
        else{
        int randomID = rand.nextInt(MediaFileList.getItems().size());
        MediaFileList.getItems().remove(randomID);
            admin.deleteMedia(MediaFileList.getItems().get(randomID));
            System.out.println("Media with the address " + MediaFileList.getItems().get(randomID).getAddress() + " removed.");

    }}



    @FXML
    void saveData(MouseEvent event) {
        SaveData data = new SaveData();
        SaveData data1 = new SaveData();
        ArrayList<MediaContentUploadable> writeMediaList = new ArrayList<MediaContentUploadable>();
        ArrayList<String> writeUploaderList = new ArrayList<String>();

        for (int i = 0; i < MediaFileList.getItems().size(); i++) {
            MediaContentUploadable item = MediaFileList.getItems().get(i);
            writeMediaList.add(item);
            data.address=item.getAddress();
            data.accessCount=item.getAccessCount();
            data.mediaType=item.getMediaType();
            data.size=item.getSize();
            data.uploadDate=item.getUploadDate();
            System.out.println(item);
        }

        for (int i =0; i<UploaderList.getItems().size(); i++){
            writeUploaderList.add(UploaderList.getItems().get(i));
        }

        data1.name = writeUploaderList;

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

            SaveData data = (SaveData) ResourceManager.load("MediaFileList.txt");
            System.out.println(data.accessCount);
            ObservableList<MediaContentUploadable> readMediaList = FXCollections.observableArrayList();
            MediaFileList.setItems(readMediaList);

            SaveData data1 = (SaveData) ResourceManager.load("UploadFileList.txt");
            ObservableList<String> readUploadList = FXCollections.observableArrayList(data1.name);
            UploaderList.setItems(readUploadList);


        }
        catch (Exception e) {
            System.out.println("Couldn't load save data: " + e.getMessage());
        }
    }


}