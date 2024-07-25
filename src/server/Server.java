package server;

import java.io.IOException;
import java.net.ServerSocket;

public class Server {
    private static ServerSocket ss;
    private static Interact i;
    public static void main(String[] args) throws IOException{
        ss = new ServerSocket();
        i = new Interact();
        while(true){
            Socket s = ss.accept();
        }
    }
}
