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

    private void handleLogin(String username, String password) throws IOException{
        boolean out = manager.loginUser(username, password);
        if(out){
            loggedUser = manager.getUser(username);
            dos.writeInt(1);
        }
        else dos.writeInt(0);
        dos.flush();
    }

    private void handleSignIn(String username, String password) throws IOException{
        boolean out = manager.signUpUser(username, password);
        if(out) dos.writeInt(1);
        else dos.writeInt(0);
        dos.flush();
    }

    private void handleExecution() throws IOException{
        int size = dis.readInt();
        byte[] programData = new byte[size];
        dis.readFully(programData);

        // TODO: Send data to execution
    }

    public void run(){
        while(this.status){
            try{
                String request = dis.readUTF();

                String[] requestSplit = request.split(" ");

                if(requestSplit[0].equals(Utils.EXECUTE)){
                    this.handleExecution();
                }
                else if(requestSplit[0].equals(Utils.LOGIN)){
                    this.handleLogin(requestSplit[1], requestSplit[2]);
                }
                else if(requestSplit[0].equals(Utils.LOGOUT)){
                    this.loggedUser = null;
                    dos.writeInt(1);
                    dos.flush();
                }
                else if(requestSplit[0].equals(Utils.QUEUE)){

                }
                else if(requestSplit[0].equals(Utils.SIGNIN)){
                    this.handleSignIn(requestSplit[1], requestSplit[2]);
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
