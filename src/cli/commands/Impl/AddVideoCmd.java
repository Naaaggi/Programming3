package cli.commands.Impl;

import cli.commands.ICommand;
import cli.console.IConsole;
import gl.Admin;
import mediaDB.MediaContent;
import mediaDB.Video;
import mediaDB.VideoImpl;

import javax.swing.plaf.metal.MetalComboBoxEditor;
import java.util.ArrayList;

public class AddVideoCmd<T> implements ICommand {
    final private IConsole console;
    private ArrayList<Video> videoList = new ArrayList<>();
    Admin admin = new Admin(videoList);;



    public AddVideoCmd(IConsole console, ArrayList<Video> videoList) {
        this.console = console;
        this.videoList = videoList;
    }

    @Override
    public void execute() {
        String videoRead = console.readString("Please enter audioVideo File: ");
        Video video = new VideoImpl(videoRead);
        admin.createMedia(video);
        System.out.println("The video " + video + " got added to the list.");
        ArrayList<Video> result= admin.readMedia(videoList);
        System.out.println("The list contains currently the following files:\n" + result);

    }

    @Override
    public String toString() {
        return "Add a video Media File to the Video list.";
    }
}