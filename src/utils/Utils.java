package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Random;

import java.nio.file.Paths;
import java.nio.file.Files;

public class Utils {
    // Generic utils
    public static final int DEFAULT_PORT = 1109; // Porta a que o servidor se conecta por predefinição
    public static final int MAX_MEM = 500; // Memória máxima do servidor em unidades ficticias

    public static final String LOGIN = "LOGIN";
    public static final String LOGOUT = "LOGOUT";
    public static final String SIGNIN = "SIGNIN";
    public static final String EXECUTE = "EXECUTE";
    public static final String QUEUE = "QUEUE";

    public static final String PROGRAM_PATH = "programs/";

    private static Random randomGen = new Random();

    public static int generateRandomNumber(int min, int max){
        return min + randomGen.nextInt(max - min + 1);
    }

    public static byte[] readDataFromFile(String file) throws IOException{
        return Files.readAllBytes(Paths.get(file));
    }

    // Client utils
    public static String generateMainIntro() {
        return "---------------------------------------------\n" +
               "Welcome!\n\n" +
               "This is a system for task execution on the cloud. This system allows the users to send tasks to be executed on a server,\n" +
               "with the result being received and written on a file. This file will have the same ID as the one received after sending the task.\n";
    }

    public static String generateLoginMenu() {
        return "---------------------------------------------\n" +
               "Login menu\n" +
               "1- Login\n" +
               "2- SignUp\n" +
               "3- Exit\n";
    }

    public static String generateMainMenu() {
        return "---------------------------------------------\n" +
               "Login menu\n" +
               "1- Execute program\n" +
               "2- Logout\n" +
               "3- Exit\n";
    }

    // Server utils
}
