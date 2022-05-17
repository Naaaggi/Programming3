package cli.commands.Impl;

import cli.commands.ICommand;
import cli.console.IConsole;
import gl.Admin;
import mediaDB.AudioVideo;

import java.util.ArrayList;

public class deleteMediaCmd<T> implements ICommand {
    Admin admin = new Admin();;
    final private IConsole console;
    private final ArrayList<T> audioVideoList;

    public deleteMediaCmd(IConsole console, ArrayList<T> audioVideoList) {
        this.console = console;
        this.audioVideoList = audioVideoList;
    }

    @Override
    public void execute() {
        String audioVideo = console.readString("Please pick audioVideo File to be deleted: ");
        admin.deleteMedia((T) audioVideo);
        System.out.println("The File " + audioVideo + " got deleted from the list.");
        ArrayList<T> result= admin.readMedia(audioVideoList);
        System.out.println("The list contains currently the following files:\n" + result);

    }

    @Override
    public String toString() {
        return "Delete an AudioVideo Media File from the AudioVideo list.";
    }
}