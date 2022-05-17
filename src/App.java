import cli.commands.CommandFactory;
import cli.commands.ICommand;
import cli.console.Console;
import cli.console.IConsole;
import mediaDB.AudioVideo;
import mediaDB.AudioVideoImpl;

import java.util.LinkedList;

public class App {

    private static boolean exitSubMenu;

    public static void main(String[] args) {
        System.out.println("Programming 3 2nd Exercise           <Mohamed Neji> <Ghazouani> <579181>\n");

        AudioVideo MediaFileList = new AudioVideoImpl("CLI");
        IConsole console = new Console();;
        CommandFactory factory = new CommandFactory(console, MediaFileList);
        LinkedList<ICommand> cmds = factory.returnsCommands();
        cli(cmds, console);
    }
    public static void setExitSubMenu(boolean exitSubMenu) {
        App.exitSubMenu = exitSubMenu;
    }
    private static void cli(LinkedList<ICommand> cmds, IConsole console) {
        App.setExitSubMenu(false);
        do {
            printCommandLineMenu(cmds);
            ICommand cmd = selectAnOption(cmds, console);
            cmd.execute();
        } while (!App.exitSubMenu);
    }

    private static ICommand selectAnOption(LinkedList<ICommand> cmds, IConsole console) {
        do {
            int selectedOption = console.readInteger("Please enter an option: ");
            if (isValidOption(selectedOption, 0, cmds.size())) {
                return cmds.get(selectedOption);
            }
        } while (true);
    }

    private static boolean isValidOption(int index, int min, int max) {
        return index >= min && index < max;
    }

    private static void printCommandLineMenu(LinkedList<ICommand> cmds) {
        System.out.println("\n0. " + cmds.get(0) + "\n");
        for (int i = 1; i < cmds.size(); i++) {
            System.out.println(i + ". " + cmds.get(i));
        }
    }
}
