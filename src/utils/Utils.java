package utils;

import java.util.Random;

public class Utils {
    // Generic utils
    public static final int DEFAULT_PORT = 1109; // Porta a que o servidor se conecta por predefinição
    public static final int MAX_MEM = 500; // Memória máxima do servidor em unidades ficticias

    // Client utils
    public static final String mainIntro = """
            Welcome!

            This a system for task execution on the cloud. This system allows the users to send tasks to be executed on a server,
            with the result being recieved and written on a file.
            """;

    public static final String loginMenu = """
                Login menu
                1- Login
                2- SignUp
                3- Exit
                """;

    public static final String mainMenu = """
    Login menu
    1- Execute program
    2- Logout
    3- Exit
    """;

    // Server utils

    private static Random randomGen = new Random();

    public static int generateRandomNumber(int min, int max){
        return min + randomGen.nextInt(max - min + 1);
    }
}
