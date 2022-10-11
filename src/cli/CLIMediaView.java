package cli;

import cli.events.InputEvent;
import cli.events.InputEventHandler;

import java.util.Date;
import java.util.List;

public class CLIMediaView implements MediaView {

    private final Console console;
    private InputEventHandler handler;

    public CLIMediaView(Console console) {
        this.console = console;
    }

    @Override
    public void displayCommands(String headLine, List<Command> commands) {
        System.out.println(headLine);
        for (Command command : commands) {
            System.out.println(command);
        }
    }

    @Override
    public void displayError(String error) {
        System.out.println(error);
    }

    @Override
    public void displayMessage(String message) {
        System.out.println(message);
    }

    @Override
    public void readInput(String title) {
        String input = console.readStringFromStdin(title);
        InputEvent e = new InputEvent(this, input);
        if (handler != null) {
            handler.handle(e);
        }
    }

    @Override
    public void setHandler(InputEventHandler handler) {
        this.handler = handler;
    }

    @Override
    public void displayMedia(String[] types, String[] Address, Date[] uploadDate, int[] accessCount) {
        for (int i = 0; i < types.length; i++) {
            System.out.println("Type: " + types[i]);
            System.out.println("Address: " + Address[i]);
            System.out.println("Upload date: " + uploadDate[i]);
            System.out.println("Access count: " + accessCount[i]);
            System.out.println("===================================");
        }
    }
}
