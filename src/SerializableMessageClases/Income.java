package SerializableMessageClases;

import java.io.Serializable;
import java.util.Date;

public class Income implements Serializable {
    private int id;
    private String group;
    private int sum;
    private String comment;
    private int idbalance;
    private int idaccount;
    private String loginAcc;
    private String nameBalance;
    private Date date;

    public Income(int id, String group, int sum, String comment, int idbalance, int idaccount) {
        this.id = id;
        this.group = group;
        this.sum = sum;
        this.comment = comment;
        this.idbalance = idbalance;
        this.idaccount = idaccount;
    }

    public Income() {

    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getNameBalance() {
        return nameBalance;
    }

    public void setNameBalance(String nameBalance) {
        this.nameBalance = nameBalance;
    }

    public String getLoginAcc() {
        return loginAcc;
    }

    public void setLoginAcc(String loginAcc) {
        this.loginAcc = loginAcc;
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
