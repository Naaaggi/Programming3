package gui.controller;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import java.util.Random;

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

}