package alternative_cli.commands.Impl;

import alternative_cli.commands.ICommand;
import alternative_cli.console.IConsole;
import gl.AdminCRUD;

import java.util.ArrayList;

public class deleteMediaCmd<T> implements ICommand {
    final private IConsole console;
    private ArrayList<T> audioVideoList;
    AdminCRUD admin = new AdminCRUD(audioVideoList);;


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