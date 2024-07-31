package server;

import java.net.Socket;

public class Handler implements Runnable{
    private Socket s;
    private Manager manager;
    public Handler(Socket s, Manager manager){
        this.s = s;
        this.manager = manager;
    }

    public void run(){
        
    }
}
