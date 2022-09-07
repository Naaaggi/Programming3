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
    ArrayList<Video> MediaList = new ArrayList<Video>();

    public CommandFactory(IConsole console, AudioVideo item) {
        super();
        this.console = console;
        this.item = item;
    }
    public LinkedList<ICommand> returnsCommands() {
        LinkedList<ICommand> cmds = new LinkedList<ICommand>();

        cmds.add(new ExitProgramCmd());
        cmds.add(new AddProducerCmd(console, MediaList));
        cmds.add(new AddAudioVideoCmd(console,  MediaList));
        cmds.add(new AddVideoCmd(console, MediaList));
        cmds.add(new deleteMediaCmd(console, MediaList));

        return cmds;
    }



}
