package client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

import utils.Utils;

class Client{
    private static Socket cs;
    private static DataInputStream dis;
    private static DataOutputStream dos;

    private static Scanner s;

    private static boolean status;
    private static boolean menuStatus;

    private static void setupLogin(String username, String password){
    }

    private static void setupSignUp(String username, String password){
    }

    private static void setupLogout(){
    }

    private static void setupExecute(){
    }

    private static void mainMenu(){
        System.out.println(Utils.mainMenu);
        menuStatus = true;
        while(menuStatus){
            String option = s.nextLine();

            if(option.equals("1")){
                setupExecute();
            }
            else if(option.equals("2")){
                setupLogout();
                menuStatus = false;
            }
            else{
                setupLogout();
                menuStatus = false;
                status = false;
            }
        }
    }

    private static void introMenu(){
        System.out.println(Utils.mainIntro);
    }

    private static void loginMenu(){
        System.out.println(Utils.loginMenu);
        while(status){
            String option = s.nextLine();

            if(option.equals("1")){
                System.out.println("Username:");
                String username = s.nextLine();

                System.out.println("Password:");
                String password = s.nextLine();

                setupLogin(username, password); // TODO: This
            }
            else if(option.equals("2")){
                System.out.println("Username:");
                String username = s.nextLine();

                System.out.println("Password:");
                String password = s.nextLine();

                setupSignUp(username, password); // TODO: This
            }
            else{
                status = false;
            }
        }
    }

    private static void initClient(){
        try{
            dis = new DataInputStream(cs.getInputStream());
            dos = new DataOutputStream(cs.getOutputStream());

            s = new Scanner(System.in);

            status = true;

            introMenu();
        }
        catch(IOException initException){
            System.out.println("Error initializing client...");
            closeClient();
        }
    }

    private static void closeClient(){
        try{
            s.close();

            dis.close();
            dos.close();
            cs.close();
        }
        catch(IOException | NullPointerException closeException) {
            System.out.println("Error closing the client, forcing exit...");
            System.exit(1);
        }
    }

    public static void main(String[] args) {
        try{
            cs = new Socket("localhost", Utils.DEFAULT_PORT);
            initClient();

            loginMenu(); // The program is going to stay here until receives a signal to exit. Allows to switch accounts.

            closeClient();

            System.exit(0);
        }
        catch(IOException e){
            System.out.println("Error conecting to the server. Closing the client...");
            closeClient();

            System.exit(1);
        }
    }
}