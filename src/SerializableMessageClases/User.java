package SerializableMessageClases;

import java.io.Serializable;

public class User implements Serializable {
    private String FIO;
    private String login;
    private String password;
    private String contacts;
    private int age;
    private String type;

    public User() {
        this.FIO = null;
        this.login = null;
        this.password = null;
        this.contacts = null;
        this.age = 0;
        this.type = null;
    }

    public String getFIO() {
        return FIO;
    }

    public void setFIO(String FIO) {
        this.FIO = FIO;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getContacts() {
        return contacts;
    }

    public void setContacts(String contacts) {
        this.contacts = contacts;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}

