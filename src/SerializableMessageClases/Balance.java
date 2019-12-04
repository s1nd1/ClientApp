package SerializableMessageClases;

import java.io.Serializable;

public class Balance implements Serializable {
    private int id;
    private String name;
    private int balance;
    private String nameAcc;


    public Balance() {
        this.id=0;
        this.name = null;
        this.balance = 0;
        this.nameAcc=null;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameAcc() {
        return nameAcc;
    }

    public void setNameAcc(String nameAcc) {
        this.nameAcc = nameAcc;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }
}
