package server;

import java.util.Scanner;

public class Interact implements Runnable{
    private Scanner s;
    public Interact(){
        this.s = new Scanner(System.in);
    }

    public void run(){
        while(true){
            String input = s.nextLine();

            if(input.equals("close")){
                Server.state = false;
                Server.closeSocket();
                break;
            }
        }
    }
}
