package SerializableMessageClases;

import java.io.Serializable;
import java.util.Date;


public class Expense implements Serializable {
    private int id;
    private String group;
    private int sum;
    private String comment;
    private int idbalance;
    private int idaccount;
    private Date date;
    private String nameBalance;
    private String account;

    public Expense(int id, String group, int sum, String comment, int idbalance, int idaccount) {
        this.id = 0;
        this.group = null;
        this.sum = 0;
        this.comment = null;
        this.idbalance = 0;
        this.idaccount = 0;
    }

    public Expense() {

    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getNameBalance() {
        return nameBalance;
    }

    public void setNameBalance(String nameBalance) {
        this.nameBalance = nameBalance;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getIdbalance() {
        return idbalance;
    }

    public void setIdbalance(int idbalance) {
        this.idbalance = idbalance;
    }

    public int getIdaccount() {
        return idaccount;
    }

    public void setIdaccount(int idaccount) {
        this.idaccount = idaccount;
    }
}
