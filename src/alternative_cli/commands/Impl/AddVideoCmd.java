package alternative_cli.commands.Impl;

import alternative_cli.commands.ICommand;
import alternative_cli.console.IConsole;
import gl.AdminCRUD;
import mediaDB.Video;
import mediaDB.VideoImpl;

import java.util.ArrayList;

public class AddVideoCmd<T> implements ICommand {
    final private IConsole console;
    private ArrayList<Video> videoList = new ArrayList<>();
    AdminCRUD admin = new AdminCRUD(videoList);;



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