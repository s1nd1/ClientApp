package Client;

import Services.SceneSwitcher;

import java.io.*;
import java.net.Socket;
import java.sql.SQLInput;

public class Client {
    private static String accountLogin;
    private static Client instance;
    private static String ipAddr = "localhost";
    private static int port = 8089;
    private Socket socket;
    private static ObjectOutputStream out = null;
    private static ObjectInputStream in = null;

    private Client(){
        try {
            socket = new Socket(ipAddr, port);
            out = new ObjectOutputStream(socket.getOutputStream());//создание потока вывода
            in = new ObjectInputStream(socket.getInputStream());//создание потока ввода
        } catch (IOException ex) {
            SceneSwitcher.showAllertErrorWindow("Сервер не включен!");

        }
    }

    public static Client getInstance(){
        if(instance == null){
            instance = new Client();
        }
        return instance;
    }

    public static void sendMessage(Object msg){
        try {
            out.writeObject(msg);
        } catch (IOException e) {
            String content = "Сервер выключен. Дождитесь включения сервера.";
            SceneSwitcher.showAllertWarningWindow(content);
        }

    }

    public static Object receiveMessage(){
        try {
            return in.readObject();
        } catch (IOException e) {
            String content = "Сервер выключен. Дождитесь включения сервера.";
            SceneSwitcher.showAllertWarningWindow(content);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getAccountLogin() {
        return accountLogin;
    }

    public static void setAccountLogin(String accountLogin) {
        Client.accountLogin = accountLogin;
    }

}
