package cli.commands.Impl;

import cli.commands.ICommand;
import cli.console.IConsole;
import gl.Admin;
import mediaDB.Uploader;
import mediaDB.UploaderImpl;

import java.util.ArrayList;

public class AddProducerCmd<T> implements ICommand {
    final private IConsole console;
    private ArrayList<T> UploaderList = new ArrayList<>();
    Admin admin = new Admin(UploaderList);;


    public AddProducerCmd(IConsole console, ArrayList<T> uploaderList) {
        this.console = console;
        this.UploaderList = uploaderList;
    }

    @Override
    public void execute() {
        String uploaderRead = console.readString("Please enter producer: ");
        Uploader uploader = new UploaderImpl(uploaderRead);
        admin.createUploader(uploader);
        System.out.println("The Uploader " + uploader + " got added to the list.");

    }

    @Override
    public String toString() {
        return "Add an Uploader to the Uploader list.";
    }
}