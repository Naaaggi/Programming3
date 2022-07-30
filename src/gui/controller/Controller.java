package gui.controller;

import io.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;


import java.util.ArrayList;
import java.util.ArrayList;
import java.util.Random;


public class Controller {
    Alert errorAlert = new Alert(Alert.AlertType.ERROR);
    @FXML
    private TableView<String> MediaFileList;
    @FXML
    private ListView<String> UploaderList;
    @FXML
    private TextField MediaFileName;

    @FXML
    void createMedia(MouseEvent event) {
        String text = MediaFileName.getText();
        String[] parsed = text.split(" ");
        String producer = parsed[1];
        MediaFileList.getItems().add(text);
        if (UploaderList.getItems().contains(producer)) {
            errorAlert.setTitle("Error");
            errorAlert.setHeaderText("Uploader already exists");
            errorAlert.showAndWait();
        } else {
            UploaderList.getItems().add(producer);
        }
        System.out.println("Media added: " + MediaFileName.getText() + " by " + parsed[1]);
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
            System.out.println("Media with the index " + selectedID + " removed.");
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
        System.out.println("Media with the index "  + randomID +" removed.");

    }}

    @FXML
    void saveData(MouseEvent event) {
        SaveData data = new SaveData();
        SaveData data1 = new SaveData();
        ArrayList<String> writeMediaList = new ArrayList<String>();
        ArrayList<String> writeUploaderList = new ArrayList<String>();

// Loop through the observable list and load the string array
        for (int i =0; i<MediaFileList.getItems().size(); i++){
            writeMediaList.add(MediaFileList.getItems().get(i));
        }
        data.name = writeMediaList;

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
            ObservableList<String> readMediaList = FXCollections.observableArrayList(data.name);

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