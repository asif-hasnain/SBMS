package mapper.DBMapper;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.util.Date;

@Entity(name = "order_history")
public class OrderHistory {
    @Column(name="user_id")
    private String userId;
    @Column(name="order_id")
    private String orderId;
    @Column(name="order_type")
    private String orderType;
    @Column(name="trade_type")
    private String tradeType;
    @Column(name="symbol")
    private String symbol;
    @Column(name="quantity")
    private int quantity;
    @Column(name="unit_price")
    private double unitPrice;
    @Column(name="order_start_time")
    private Date orderStartTime;
    @Column(name="order_end_time")
    private Date orderEndTime;
    @Column(name="order_status")
    private String orderStatus;
    @Column(name="updated_date")
    private Date updated_date;
    @Column(name="created_date")
    private Date created_date;

    public OrderHistory() {
    }

    public OrderHistory(String userId, String orderId, String orderType, String tradeType, String symbol, int quantity, double unitPrice, String orderStatus) {
        this.userId = userId;
        this.orderId = orderId;
        this.orderType = orderType;
        this.tradeType = tradeType;
        this.symbol = symbol;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.orderStatus = orderStatus;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderType() {
        return orderType;
    }

    public String getTradeType() {
        return tradeType;
    }

    public void setTradeType(String tradeType) {
        this.tradeType = tradeType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Date getOrderStartTime() {
        return orderStartTime;
    }

    public void setOrderStartTime(Date orderStartTime) {
        this.orderStartTime = orderStartTime;
    }

    public Date getOrderEndTime() {
        return orderEndTime;
    }

    public void setOrderEndTime(Date orderEndTime) {
        this.orderEndTime = orderEndTime;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
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
    public enum  orderType{
        BUY,SELL
    }
    public enum  tradeType{
        INTERDAY,INTRADAY
    }
    public enum  orderStatus{
        COMPLETE,CANCELLED,PENDING
    }
    @Override
    public String toString() {
        return "OrderHistory{" +
                "userId='" + userId + '\'' +
                ", orderId='" + orderId + '\'' +
                ", orderType='" + orderType + '\'' +
                ", tradeType='" + tradeType + '\'' +
                ", symbol='" + symbol + '\'' +
                ", quantity=" + quantity +
                ", unitPrice=" + unitPrice +
                ", orderStartTime=" + orderStartTime +
                ", orderEndTime=" + orderEndTime +
                ", orderStatus='" + orderStatus + '\'' +
                ", updated_date=" + updated_date +
                ", created_date=" + created_date +
                '}';
    }
}
