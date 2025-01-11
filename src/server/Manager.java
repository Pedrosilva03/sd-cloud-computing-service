package server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import utils.*;

public class Manager {
    private int max_mem;

    private String databasePath;

    private HashMap<Integer, User> users;
    private List<Task> queue;

    public Manager(String databasePath) throws IOException{
        this.max_mem = Utils.MAX_MEM;

        this.databasePath = databasePath;

        this.users = this.importUsers();
        this.queue = new ArrayList<>();
    }

    public HashMap<Integer, User> importUsers() throws IOException{
        HashMap<Integer, User> users = new HashMap<>();

        BufferedReader br = new BufferedReader(new FileReader(databasePath + "/users.txt"));
        String line;
        while((line = br.readLine()) != null){
            String[] splitData = line.split(";");
            User user = new User(Integer.parseInt(splitData[0]), splitData[1], splitData[2]);
            users.put(user.getIDasInteger(), user);
        }
        br.close();

        return users;
    }

    public void exportUsers() throws IOException{
        BufferedWriter bw = new BufferedWriter(new FileWriter(databasePath + "/users.txt"));
        for(Entry<Integer, User> user: this.users.entrySet()){
            bw.write(user.getKey().intValue() + ";" + user.getValue().getUsername() + ";" + user.getValue().getPassword());
            bw.newLine();
        }
        bw.close();
    }

    public boolean loginUser(String username, String password){
        for(Entry<Integer, User> user: this.users.entrySet()){
            if(user.getValue().getUsername().equals(username) && user.getValue().getPassword().equals(password)) return true;
        }
        return false;
    }

    public boolean signUpUser(String username, String password){
        User newUser = new User(username, password);

        if(!(newUser.getUsername().equals(username) && newUser.getPassword().equals(password))) return false;

        this.users.put(newUser.getIDasInteger(), newUser);

        try{
            BufferedWriter bw = new BufferedWriter(new FileWriter(databasePath + "/users.txt"));
            bw.write(newUser.getIDasInteger() + ";" + newUser.getUsername() + ";" + newUser.getPassword());
            bw.newLine();
            bw.close();
        }
        catch(IOException e){
            System.out.println("Error writing new user on file");
            this.users.remove(newUser.getIDasInteger());
            return false;
        }

        return true;
    }
}
