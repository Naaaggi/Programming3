package cli.commands.Impl;

import cli.commands.ICommand;
import cli.console.IConsole;
import gl.Admin;
import mediaDB.Video;

import java.util.ArrayList;

public class AddVideoCmd<T> implements ICommand {
    final private IConsole console;
    private ArrayList<T> videoList = new ArrayList<>();
    Admin admin = new Admin(videoList);;



    public AddVideoCmd(IConsole console, ArrayList<T> videoList) {
        this.console = console;
        this.videoList = videoList;
    }

    @Override
    public void execute() {
        String video = console.readString("Please enter audioVideo File: ");
        admin.createMedia((T) video);
        System.out.println("The video " + video + " got added to the list.");
        ArrayList<Video> result= admin.readMedia(videoList);
        System.out.println("The list contains currently the following files:\n" + result);

    }

    @Override
    public String toString() {
        return "Add a video Media File to the Video list.";
    }
}