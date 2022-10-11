

import TCP.console.Console;
import TCP.console.IConsole;
import gl.AdminCRUD;
import mediaDB.AudioVideo;
import mediaDB.AudioVideoImpl;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;


public class Client {
    public static void main(String[] args) {
        Socket socket = null;
        InputStreamReader isr = null;
        OutputStreamWriter osw = null;
        BufferedReader br = null;
        BufferedWriter bw = null;
        try {
            socket = new Socket("localhost", 9999);
            ArrayList<String> audioVideoList = new ArrayList<String>();
            isr = new InputStreamReader(socket.getInputStream());
            osw = new OutputStreamWriter(socket.getOutputStream());
            br = new BufferedReader(isr);
            bw = new BufferedWriter(osw);
            final IConsole console = new Console();


            Scanner sc = new Scanner(System.in);
            System.out.print("Enter your name: ");
            String name = sc.nextLine();
            bw.write(name);
            bw.newLine();
            bw.flush();
            while (true) {
                System.out.println("\n0. Exit program.\n" +
                        "\n" +
                        "1. Add an AudioVideo Media File to the AudioVideo list.\n" +
                        "2. Show the AudioVideo list.\n" +
                        "Please enter an option:");
                String commandNumber = sc.nextLine();
                bw.write(commandNumber);
                bw.newLine();
                bw.flush();
                System.out.println(br.readLine());
                AdminCRUD admin = new AdminCRUD(audioVideoList);
                if(commandNumber.equals("0")){
                    break;
                }
                switch(commandNumber) {
                    case "1":
                        String audioVideoRead = console.readString("Please enter audioVideo File: ");
                        bw.write(audioVideoRead);
                        bw.newLine();
                        bw.flush();
                        AudioVideo audioVideo = new AudioVideoImpl(audioVideoRead);
                        admin.createMedia(audioVideo);
                        System.out.println("The File " + audioVideo + " got added to the list.");
                        break;
                    case "2":
                        ArrayList<AudioVideo> result= admin.readMedia();
                        bw.write(String.valueOf(result));
                        bw.newLine();
                        bw.flush();
                        System.out.println("The list contains currently the following files: " + result);
                        break;
                    default:
                        break;
                }
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                if(bw != null){
                    bw.close();
                }
                if(br != null){
                    br.close();
                }
                if(osw != null){
                    osw.close();
                }
                if(isr != null){
                    isr.close();
                }
                if(socket != null){
                    socket.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}