package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import utils.*;

public class Handler implements Runnable{
    private Socket s;
    private Manager manager;

    private DataInputStream dis;
    private DataOutputStream dos;

    private boolean status;

    private User loggedUser;

    public Handler(Socket s, Manager manager) throws IOException{
        this.s = s;
        this.manager = manager;

        this.dis = new DataInputStream(s.getInputStream());
        this.dos = new DataOutputStream(s.getOutputStream());

        this.status = true;
    }

    public void run(){
        while(this.status){
            try{
                String request = dis.readUTF();

                String[] requestSplit = request.split(" ");

                if(requestSplit[0].equals(Utils.EXECUTE)){

                }
                else if(requestSplit[0].equals(Utils.LOGIN)){
                    boolean out = manager.loginUser(requestSplit[1], requestSplit[2]);
                    if(out){
                        // TODO: Get user here
                        dos.writeInt(1);
                    }
                    else dos.writeInt(0);
                    dos.flush();
                }
                else if(requestSplit[0].equals(Utils.LOGOUT)){
                    this.loggedUser = null;
                    dos.writeInt(1);
                    dos.flush();
                }
                else if(requestSplit[0].equals(Utils.QUEUE)){

                }
                else if(requestSplit[0].equals(Utils.SIGNIN)){
                    boolean out = manager.signUpUser(requestSplit[1], requestSplit[2]);
                    if(out) dos.writeInt(1);
                    else dos.writeInt(0);
                    dos.flush();
                }
                else{
                    this.status = false;
                }
            }
            catch(IOException e){
                
            }
        }
    }
}
