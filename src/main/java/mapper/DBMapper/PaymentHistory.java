package mapper.DBMapper;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.util.Date;

@Entity(name = "payment_history")
public class PaymentHistory {
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
}
