package cli.commands;

import cli.commands.Impl.*;
import cli.console.IConsole;
import mediaDB.AudioVideo;
import mediaDB.Video;

import java.util.ArrayList;
import java.util.LinkedList;

public class CommandFactory<T> {

    final private IConsole console;
    private final AudioVideo item;
    ArrayList<T> uploaderlist = new ArrayList<T>();

    public CommandFactory(IConsole console, AudioVideo item) {
        super();
        this.console = console;
        this.item = item;
    }
    public LinkedList<ICommand> returnsCommands() {
        LinkedList<ICommand> cmds = new LinkedList<ICommand>();

        cmds.add(new ExitProgramCmd());
        cmds.add(new AddProducerCmd<T>(console, uploaderlist));
        cmds.add(new AddAudioVideoCmd<T>(console, (ArrayList<T>) uploaderlist));
        cmds.add(new AddVideoCmd<T>(console, (ArrayList<T>)uploaderlist));
        cmds.add(new deleteMediaCmd<T>(console, (ArrayList<T>)uploaderlist));

        return cmds;
    }



}
