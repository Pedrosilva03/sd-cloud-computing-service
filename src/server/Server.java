package server;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import utils.Utils;

public class Server {
    private static ServerSocket ss;
    public static boolean state = true;
    private static Manager manager;
    public static void main(String[] args){
        try {
            ss = new ServerSocket(Utils.DEFAULT_PORT);
            
            // Specific exception to handle database problems (keep it clean)
            try{
                manager = new Manager("data");
            }
            catch(IOException e){
                System.out.println("Error creating server's database. Closing...");
                closeSocket();
            }

            // Interactions
            Thread interacter = new Thread(new Interact());
            interacter.start();

            System.out.println("Servidor conectado em " + ss.getInetAddress().getHostAddress() + "/" + ss.getLocalPort());

            // Loop principal
            while(true){
                Socket s = ss.accept();

                Thread t = new Thread(new Handler(s, manager));
                t.start();
            }
        }
        // An exception is forced to make the server stop reading from the socket. If an exit signal was recieved, then "state" will be false and the server will know it.
        catch (IOException e) {
            if(state){
                closeSocket();
                System.out.println("Server crash");
            }
            else System.out.println("Server closed");
        }
    }

    public static void closeSocket(){
        try{
            ss.close();
        }
        catch(IOException e){
            System.out.println("Error closing server socket");
        }
    }
}
