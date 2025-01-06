package server;

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
            
            // Interactions
            Thread interacter = new Thread(new Interact());
            interacter.start();

            manager = new Manager();

            System.out.println("Servidor conectado em " + ss.getInetAddress().getHostAddress() + "/" + ss.getLocalPort());

            // Loop principal
            while(true){
                Socket s = ss.accept();

                Thread t = new Thread(new Handler(s, manager));
                t.start();
            }
        }
        // Exception para lidar com o fecho do servidor
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
