package alternative_cli.commands.Impl;

import alternative_cli.commands.ICommand;
import alternative_cli.console.IConsole;
import gl.AdminCRUD;
import mediaDB.Uploader;
import mediaDB.UploaderImpl;

import java.util.ArrayList;

public class AddProducerCmd<T> implements ICommand {
    final private IConsole console;
    private ArrayList<T> UploaderList = new ArrayList<>();
    AdminCRUD admin = new AdminCRUD(UploaderList);;


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