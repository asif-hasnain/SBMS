package mapper.DBMapper;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.util.Date;

@Entity(name = "transaction_history")
public class TransactionHistory {
    @Column(name="user_id")
    private String userId;
    @Column(name="transaction_id")
    private String transactionId;
    @Column(name="transaction_type")
    private String transactionType;
    @Column(name="amount")
    private double amount;
    @Column(name="updated_date")
    private Date updated_date;
    @Column(name="created_date")
    private Date created_date;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Date getUpdated_date() {
        return updated_date;
    }

    public void setUpdated_date(Date updated_date) {
        this.updated_date = updated_date;
    }

    public Date getCreated_date() {
        return created_date;
    }

    public void setCreated_date(Date created_date) {
        this.created_date = created_date;
    }
    public enum  transactionType{
        CREDIT,DEBIT
    }
    @Override
    public String toString() {
        return "TransactionHistory{" +
                "userId='" + userId + '\'' +
                ", transactionId='" + transactionId + '\'' +
                ", transactionType='" + transactionType + '\'' +
                ", amount=" + amount +
                ", updated_date=" + updated_date +
                ", created_date=" + created_date +
                '}';
    }
}
