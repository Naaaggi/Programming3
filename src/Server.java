import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args) throws IOException {
        Socket socket = null;
        InputStreamReader isr = null;
        OutputStreamWriter osw = null;
        BufferedReader br = null;
        BufferedWriter bw = null;
        ServerSocket serverSocket = null;

        serverSocket = new ServerSocket(9999);
        System.out.println("Server is running...");

        while(true){
            try {
                socket = serverSocket.accept();
                isr = new InputStreamReader(socket.getInputStream());
                osw = new OutputStreamWriter(socket.getOutputStream());
                br = new BufferedReader(isr);
                bw = new BufferedWriter(osw);
                String name = br.readLine();
                System.out.println("Client " + name + " is connected.");

                while (true) {
                    String commandNumber = br.readLine();

                    if (commandNumber.equals("0")) {
                        System.out.println("Client " + name + " is disconnected.");
                        break;
                    } else if (commandNumber.equals("1")) {
                        bw.write("Command number from server: " + commandNumber + " will be now executed.");
                        bw.newLine();
                        bw.flush();
                        String audioVideo = br.readLine();
                        System.out.println("AudioVideo added from client: " + audioVideo);


                    } else if (commandNumber.equals("2")) {
                        bw.write("Command number from server: " + commandNumber + " will be now executed.");
                        bw.newLine();
                        bw.flush();
                        System.out.println("The list contains currently the following files: " + br.readLine());
                    } else {
                        bw.write("Invalid command number.");
                        bw.newLine();
                        bw.flush();
                    }

                }
                socket.close();
                isr.close();
                osw.close();
                br.close();
                bw.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}