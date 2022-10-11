package alternative_cli.commands.Impl;

import alternative_cli.commands.ICommand;
import alternative_cli.console.IConsole;
import gl.AdminCRUD;
import mediaDB.AudioVideo;
import mediaDB.AudioVideoImpl;

import java.util.ArrayList;

public class AddAudioVideoCmd<T> implements ICommand {
    final private IConsole console;
    private ArrayList<T> audioVideoList = new ArrayList<>();
    AdminCRUD admin = new AdminCRUD(audioVideoList);;

    public AddAudioVideoCmd(IConsole console, ArrayList<T> audioVideoList) {
        this.console = console;
        this.audioVideoList = audioVideoList;
    }

    @Override
    public void execute() {
        String audioVideoRead = console.readString("Please enter audioVideo File: ");
        AudioVideo audioVideo = new AudioVideoImpl(audioVideoRead);
        admin.createMedia(audioVideo);
        System.out.println("The File " + audioVideo + " got added to the list.");
        ArrayList<AudioVideo> result= admin.readMedia(audioVideoList);
        System.out.println("The list contains currently the following files:\n" + result);
    }

    @Override
    public String toString() {
        return "Add an AudioVideo Media File to the AudioVideo list.";
    }
}