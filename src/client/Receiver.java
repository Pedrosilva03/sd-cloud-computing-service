package client;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import utils.Utils;

public class Receiver implements Runnable{
    private BlockingQueue<String> responses;
    private DataInputStream dis;

    private boolean status;

    public Receiver(DataInputStream dis){
        this.responses = new LinkedBlockingQueue<>();
        this.dis = dis;
    }
    
    public void run(){
        while(this.status){
            try{
                String answer = dis.readUTF();

                if(answer.startsWith("RESULT:")){
                    String[] splittedAnswer = answer.split(" ");
                    Utils.writeDataFromFile(Utils.RESULTS_PATH + splittedAnswer[1], Base64.getDecoder().decode(splittedAnswer[2]));
                }
                else{
                    responses.add(answer);
                }
            }
            catch(IOException e){
                System.out.println("An error ocurred on the receiver");
            }
        }
    }

    public String takeImmediateResult(){
        try{
            return this.responses.take();
        }
        catch(InterruptedException e){
            return null;
        }
    }

    public void stop(){
        this.status = false;
    }
}
