package client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

import utils.Utils;
import utils.Messages;

class Client{
    private static Socket cs;
    private static DataInputStream dis;
    private static DataOutputStream dos;

    private static Scanner s;

    private static boolean status;
    private static boolean menuStatus;

    private static boolean setupLogin(String username, String password){
        String loginMessage = Messages.generateLoginMessage(username, password);
        try{
            dos.writeUTF(loginMessage);
            dos.flush();

            int result = dis.readInt();
            return result == 1; // Retorna o valor em boolean (recebido como inteiro a partir do socket)
        }
        catch(IOException e){
            System.out.println("Error sending login request");
        }
        return false;
    }

    private static boolean setupSignUp(String username, String password){
        String signupMessage = Messages.generateSignupMessage(username, password);
        try{
            dos.writeUTF(signupMessage);
            dos.flush();

            int result = dis.readInt();
            return result == 1; // Retorna o valor em boolean (recebido como inteiro a partir do socket)
        }
        catch(IOException e){
            System.out.println("Error sending signup request");
        }
        return false;
    }

    private static boolean setupLogout(){
        String logoutMessage = Messages.generateLogoutMessage();
        try{
            dos.writeUTF(logoutMessage);
            dos.flush();

            int result = dis.readInt();
            return result == 1; // Retorna o valor em boolean (recebido como inteiro a partir do socket)
        }
        catch(IOException e){
            System.out.println("Error sending logout request");
        }
        return false;
    }

    private static int setupExecute(){
        return 0;
    }

    private static void mainMenu(){
        menuStatus = true;
        while(menuStatus){
            System.out.println(Utils.mainMenu);
            String option = s.nextLine();

            if(option.equals("1")){
                int taskID = setupExecute(); // Get the data
                System.out.println("Submitted task " + taskID + ".");
            }
            else if(option.equals("2")){
                if(!setupLogout()){ // Caso para se o logout der erro, fecha a conexão e força o servidor a parar o worker associado
                    System.out.println("Error trying to logout, the program will close...");
                    status = false;
                }
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
        while(status){
            System.out.println(Utils.loginMenu);
            String option = s.nextLine();

            if(option.equals("1")){
                System.out.println("Username:");
                String username = s.nextLine();

                System.out.println("Password:");
                String password = s.nextLine();

                if(setupLogin(username, password)){
                    System.out.println("Logado na conta " + username);
                    
                    mainMenu();
                }
            }
            else if(option.equals("2")){
                System.out.println("Username:");
                String username = s.nextLine();

                System.out.println("Password:");
                String password = s.nextLine();

                if(setupSignUp(username, password)) System.out.println("Account created successfully\n");
                else System.out.println("There was an error creating your account\n");
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
            dos.writeUTF("EXIT"); // Specific message to stop the dedicated server worker
            dos.flush();
            
            s.close();

            dis.close();
            dos.close();
            cs.close();

            System.exit(0);
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
        }
        catch(IOException e){
            System.out.println("Error conecting to the server. Closing the client...");
            closeClient();

            System.exit(1);
        }
    }
}