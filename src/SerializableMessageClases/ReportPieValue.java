package SerializableMessageClases;

import java.io.Serializable;

public class ReportPieValue implements Serializable {
    private String financialArticleName;
    private int amount;

    public ReportPieValue() {
        this.financialArticleName = null;
        this.amount = 0;
    }

    public String getFinancialArticleName() {
        return financialArticleName;
    }

    public void setFinancialArticleName(String financialArticleName) {
        this.financialArticleName = financialArticleName;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
