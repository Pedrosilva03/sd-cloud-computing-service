package utils;

public class User {
    private int ID;

    private String username;
    private String password;

    // Caso para um utlizador que já existe
    public User(int ID, String username, String password){
        this.ID = ID;

        this.username = username;
        this.password = password;
    }

    // Caso para um utilizador novo (sign-up)
    public User(String username, String password){
        this.ID = Utils.generateRandomNumber(1, 100000);

        this.username = username;
        this.password = password;
    }

    public int getID(){
        return this.ID;
    }

    public Integer getIDasInteger(){
        return Integer.valueOf(this.ID);
    }

    public String getUsername(){
        return this.username;
    }

    public String getPassword(){
        return this.password;
    }
}
