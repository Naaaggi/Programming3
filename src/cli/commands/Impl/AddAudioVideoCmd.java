package cli.commands.Impl;

import cli.commands.ICommand;
import cli.console.IConsole;
import gl.Admin;
import mediaDB.AudioVideo;

import java.util.ArrayList;

public class AddAudioVideoCmd<T> implements ICommand {
     Admin admin = new Admin();;
    final private IConsole console;
    private final ArrayList<T> audioVideoList;

    public AddAudioVideoCmd(IConsole console, ArrayList<T> audioVideoList) {
        this.console = console;
        this.audioVideoList = audioVideoList;
    }

    @Override
    public void execute() {
        String audioVideo = console.readString("Please enter audioVideo File: ");
        admin.createMedia((T) audioVideo);
        System.out.println("The File " + audioVideo + " got added to the list.");
        ArrayList<AudioVideo> result= admin.readMedia(audioVideoList);
        System.out.println("The list contains currently the following files:\n" + result);
    }

    @Override
    public String toString() {
        return "Add an AudioVideo Media File to the AudioVideo list.";
    }
}