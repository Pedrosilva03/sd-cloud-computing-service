package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Base64;

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
            dos.writeUTF("1");
        }
        else dos.writeUTF("0");
        dos.flush();
    }

    private void handleSignIn(String username, String password) throws IOException{
        boolean out = manager.signUpUser(username, password);
        if(out) dos.writeUTF("1");
        else dos.writeUTF("0");
        dos.flush();
    }

    private void handleExecution() throws IOException{
        int size = dis.readInt();
        byte[] programData = new byte[size];
        dis.readFully(programData);
        int memory = dis.readInt();

        // Generate ID for task
        int taskID = Utils.generateRandomNumber(1, 100000);
        Task t = new Task(taskID, loggedUser, programData, memory);

        Thread worker = new Thread(() -> {
            byte[] res = this.manager.execTaskLobby(t);
            try{
                dos.writeUTF("RESULT: " + t.getID() + " " + Base64.getEncoder().encodeToString(res));
                dos.flush();
            }
            catch(IOException e){
                System.out.println("Error sending task result for task " + t.getID());
            }
        });
        worker.start();

        dos.writeUTF(String.valueOf(taskID));
        dos.flush();
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
                    dos.writeUTF("1");
                    dos.flush();
                }
                else if(requestSplit[0].equals(Utils.QUEUE)){
                    dos.writeUTF(this.manager.getQueue());
                    dos.flush();
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
