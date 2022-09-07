package TCP.console;

import java.util.Scanner;

public class Console implements IConsole {
    final private Scanner scanner = new Scanner(System.in);

    @Override
    public String readString(String text) {
        System.out.print(text);
        String input = scanner.nextLine();
        return input;
    }

    @Override
    public int readInteger(String text) {
        System.out.print(text);
        int result = 0;
        while (true) {
            String s = scanner.nextLine();
            try {
                result = Integer.parseInt(s);
            } catch (NumberFormatException e) {
                System.out.print(text);
                continue;
            }
            break;
        }
        return result;
    }

    @Override
    public void write(String s) {
        System.out.println(s);
    }

}
