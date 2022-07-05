package gui.controller;

import gui.GUI;
import io.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Controller {
    Alert errorAlert = new Alert(Alert.AlertType.ERROR);
    @FXML
    private ListView<String> MediaFileList;

    @FXML
    private TextField MediaFileName;
    @FXML
    private TextField Producer;

    @FXML
    void createMedia(MouseEvent event) {
        MediaFileList.getItems().add(MediaFileName.getText());
        System.out.println("Media added: " + MediaFileName.getText() + " by " + Producer.getText());
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
        ArrayList<String> writeList = new ArrayList<String>();

// Loop through the observable list and load the string array
        for (int i =0; i<MediaFileList.getItems().size(); i++){
            writeList.add(MediaFileList.getItems().get(i));
        }
        data.name = writeList;
        try {
            ResourceManager.save(data, "1.save");
        }
        catch (Exception e) {
            System.out.println("Couldn't save: " + e.getMessage());
        }
    }

    @FXML
    void loadData(MouseEvent event) {
        try {
            SaveData data = (SaveData) ResourceManager.load("1.save");
            ObservableList<String> readList = FXCollections.observableArrayList(data.name);

            MediaFileList.setItems(readList);

        }
        catch (Exception e) {
            System.out.println("Couldn't load save data: " + e.getMessage());
        }
    }

}