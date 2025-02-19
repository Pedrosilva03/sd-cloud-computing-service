package utils;

public class Task {
    private int id;
    private User user;

    private byte[] taskData;
    private int memory;

    public Task(int id, User user, byte[] data, int memory){
        this.id = id;
        this.user = user;
        this.taskData = data;
        this.memory = memory;
    }

    public int getID(){
        return this.id;
    }

    public User getUser(){
        return this.user;
    }

    public byte[] getData(){
        return this.taskData;
    }

    public int getMemory(){
        return this.memory;
    }
}
