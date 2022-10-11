import alternative_cli.commands.CommandFactory;
import alternative_cli.commands.ICommand;
import alternative_cli.console.Console;
import alternative_cli.console.IConsole;
import mediaDB.AudioVideo;
import mediaDB.AudioVideoImpl;

import java.util.LinkedList;

public class AlternativeCLIExcuter {

    public static boolean exitSubMenu;

    public static void main(String[] args) {
        System.out.println("Programming 3 2nd Exercise           <Mohamed Neji> <Ghazouani> <579181>\n");

        AudioVideo MediaFileList = new AudioVideoImpl("CLI");
        IConsole console = new Console();;
        CommandFactory factory = new CommandFactory(console, MediaFileList);
        LinkedList<ICommand> cmds = factory.returnsCommands();
        cli(cmds, console);
    }
    public static void setExitSubMenu(boolean exitSubMenu) {
        AlternativeCLIExcuter.exitSubMenu = exitSubMenu;
    }
    private static void cli(LinkedList<ICommand> cmds, IConsole console) {
        AlternativeCLIExcuter.setExitSubMenu(false);
        do {
            printCommandLineMenu(cmds);
            ICommand cmd = selectAnOption(cmds, console);
            cmd.execute();
        } while (!AlternativeCLIExcuter.exitSubMenu);
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
