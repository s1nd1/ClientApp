package SerializableMessageClases;

import java.io.Serializable;
import java.util.Date;

public class ReportPieQuery implements Serializable {
    private Date dateStart;
    private Date dateEnd;
    private String reportType;
    private String nameAcc;

    public ReportPieQuery() {
        this.dateStart = null;
        this.dateEnd = null;
        this.reportType = null;
        this.nameAcc=null;
    }

    public String getNameAcc() {
        return nameAcc;
    }

    public void setNameAcc(String nameAcc) {
        this.nameAcc = nameAcc;
    }

    public Date getDateStart() {
        return dateStart;
    }

    public void setDateStart(Date dateStart) {
        this.dateStart = dateStart;
    }

    public Date getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(Date dateEnd) {
        this.dateEnd = dateEnd;
    }

    public String getReportType() {
        return reportType;
    }

    public void setReportType(String reportType) {
        this.reportType = reportType;
    }
}
