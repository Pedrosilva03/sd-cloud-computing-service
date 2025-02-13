package utils;

import java.io.File;
import java.io.FileInputStream;
import java.util.Random;

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

    public static byte[] readDataFromFile(String file){
        File programFile = new File(file);
        long fileSize = programFile.length();

        FileInputStream programFileStream = new FileInputStream(programFile);
        byte[] programFileData = new byte[(int)fileSize];
        int bytesRead;
        while((bytesRead = programFileStream.read(programFileData)) != -1);

        return programFileData;
    }

    // Client utils
    public static final String mainIntro = """
        ---------------------------------------------
        Welcome!

        This a system for task execution on the cloud. This system allows the users to send tasks to be executed on a server,
        with the result being recieved and written on a file. This file will have the same ID as the one recieved after sending the task.
        """;

    public static final String loginMenu = """
        ---------------------------------------------
        Login menu
        1- Login
        2- SignUp
        3- Exit
        """;

    public static final String mainMenu = """
        ---------------------------------------------
        Login menu
        1- Execute program
        2- Logout
        3- Exit
        """;

    // Server utils
}
