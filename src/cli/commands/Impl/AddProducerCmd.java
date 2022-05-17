package cli.commands.Impl;

import cli.commands.ICommand;
import cli.console.IConsole;
import gl.Admin;

import java.util.ArrayList;

public class AddProducerCmd<T> implements ICommand {
    Admin admin = new Admin();;
    final private IConsole console;
    private final ArrayList<T> UploaderList;

    public AddProducerCmd(IConsole console, ArrayList<T> uploaderList) {
        this.console = console;
        this.UploaderList = uploaderList;
    }

    @Override
    public void execute() {
        String uploader = console.readString("Please enter producer: ");
        admin.createUploader((T) uploader);
        System.out.println("The Uploader " + uploader + " got added to the list.");

    }

    @Override
    public String toString() {
        return "Add an Uploader to the Uploader list.";
    }
}