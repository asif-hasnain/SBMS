package mapper.DBMapper;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.util.Date;

@Entity(name = "payment_history")
public class Invoice {
    @Column(name="invoice_id")
    private String invoice_id;
    @Column(name="brokerage_id")
    private String brokerage_id;
    @Column(name="invoice_date")
    private Date invoice_date;
    @Column(name="payment_due_date")
    private Date payment_due_date;
    @Column(name="invoice_amount")
    private double invoice_amount;
    @Column(name="payment_id")
    private String payment_id;
    @Column(name="payment_date")
    private Date payment_date;
    private boolean paid;



    public String getInvoice_id() {
        return invoice_id;
    }

    public void setInvoice_id(String invoice_id) {
        this.invoice_id = invoice_id;
    }

    public String getBrokerage_id() {
        return brokerage_id;
    }

    public void setBrokerage_id(String brokerage_id) {
        this.brokerage_id = brokerage_id;
    }

    public Date getInvoice_date() {
        return invoice_date;
    }

    public void setInvoice_date(Date invoice_date) {
        this.invoice_date = invoice_date;
    }

    public Date getPayment_due_date() {
        return payment_due_date;
    }

    public void setPayment_due_date(Date payment_due_date) {
        this.payment_due_date = payment_due_date;
    }

    public double getInvoice_amount() {
        return invoice_amount;
    }

    public void setInvoice_amount(double invoice_amount) {
        this.invoice_amount = invoice_amount;
    }

    public String getPayment_id() {
        return payment_id;
    }

    public void setPayment_id(String payment_id) {
        this.payment_id = payment_id;
    }

    public Date getPayment_date() {
        return payment_date;
    }

    public void setPayment_date(Date payment_date) {
        this.payment_date = payment_date;
    }

    public boolean isPaid() {
        return paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }

    @Override
    public String toString() {
        return "Invoice{" +
                "invoice_id='" + invoice_id + '\'' +
                ", brokerage_id='" + brokerage_id + '\'' +
                ", invoice_date=" + invoice_date +
                ", payment_due_date=" + payment_due_date +
                ", invoice_amount=" + invoice_amount +
                ", payment_id='" + payment_id + '\'' +
                ", payment_date=" + payment_date +
                ", paid=" + paid +
                '}';
    }
}
