package server;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

import utils.*;

public class Manager {
    private int max_mem;

    private String databasePath;

    private HashMap<Integer, User> users;

    public Manager(String databasePath) throws IOException{
        this.max_mem = Utils.MAX_MEM;

        this.databasePath = databasePath;

        this.users = this.importUsers();
    }

    private HashMap<Integer, User> importUsers() throws IOException{
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
}
