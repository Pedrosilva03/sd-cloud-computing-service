package utils;

public class Messages {
    public static String generateLoginMessage(String username, String password){
        StringBuilder sb = new StringBuilder();

        sb.append(Utils.LOGIN);
        sb.append(" ");
        sb.append(username);
        sb.append(" ");
        sb.append(password);

        return sb.toString();
    }

    public static String generateLogoutMessage(){
        StringBuilder sb = new StringBuilder();

        sb.append(Utils.LOGOUT);

        return sb.toString();
    }

    public static String generateSignupMessage(String username, String password){
        StringBuilder sb = new StringBuilder();

        sb.append(Utils.SIGNIN);
        sb.append(" ");
        sb.append(username);
        sb.append(" ");
        sb.append(password);

        return sb.toString();
    }

    public static String generateExecuteMessage(){
        StringBuilder sb = new StringBuilder();

        sb.append(Utils.EXECUTE);

        return sb.toString();
    }

    public static String generateQueueMessage(){
        StringBuilder sb = new StringBuilder();

        sb.append(Utils.QUEUE);

        return sb.toString();
    }
}
