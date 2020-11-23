package lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import mapper.DBMapper.OrderHistory;
import mapper.DBMapper.StockData;
import mapper.DBMapper.TransactionHistory;
import mapper.OrderRequest;
import mapper.OrderResponse;
import org.apache.commons.lang.time.DateUtils;
import util.*;

import java.sql.Connection;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class Order implements RequestHandler<OrderRequest,OrderResponse> {
    Connection con = null;
    @Override
    public OrderResponse handleRequest(OrderRequest input, Context context) {
        System.out.println("input :" + input);
        if(input == null || !CommonUtil.isValidString(input.getUserId()) || !CommonUtil.isValidString(input.getSymbol())
        || !CommonUtil.isValidString(input.getOrderType()) || !CommonUtil.isValidString(input.getTradeType())||
        input.getQuantity()<=0|| !(input.getOrderType().equalsIgnoreCase(OrderHistory.orderType.BUY.name())||input.getOrderType().equalsIgnoreCase(OrderHistory.orderType.SELL.name()))
        ||!(input.getTradeType().equalsIgnoreCase(OrderHistory.tradeType.INTRADAY.name())||input.getTradeType().equalsIgnoreCase(OrderHistory.tradeType.INTERDAY.name()))){
            return new OrderResponse(new Response(Constant.INVALID_INPUT,Constant.INVALID_INPUT_MSG));
        }
//        if(!validateTime()){
//            return new OrderResponse(new Response(Constant.INVALID_INPUT,"Please place order during trading hours."));
//        }
        con = JDBCConnection.getJDBCCOnnection(con, 0);
        StockData stockData = DBUtil.getStockData(input.getSymbol(),con);
        if(stockData == null) {
            return new OrderResponse(new Response(Constant.DEFAULT_ERROR,"Stock not supported."));
        }
        if(input.getOrderType().equalsIgnoreCase(OrderHistory.orderType.BUY.name())){
            double creditAvailable = getAvailableCredit(input.getUserId(),con);
            System.out.println("creditAvailable: " + creditAvailable);
            double margin = creditAvailable + getMargin(input.getUserId(),con);
            System.out.println("margin: " + margin);
            double totalCost = input.getTradeType().equalsIgnoreCase(OrderHistory.tradeType.INTRADAY.name()) ?
                    stockData.getLatestPrice()*input.getQuantity() * 0.1 : stockData.getLatestPrice()*input.getQuantity();
            if(totalCost<=margin){
                String orderId = UUID.randomUUID().toString();
                boolean orderResult = DBUtil.addOrder(new OrderHistory(input.getUserId(),orderId,OrderHistory.orderType.BUY.name(),input.getTradeType().toUpperCase(),input.getSymbol(),input.getQuantity(),stockData.getLatestPrice(),OrderHistory.orderStatus.COMPLETE.toString()),con);
                if(orderResult) return new OrderResponse(new Response(Constant.SUCCESS,Constant.SUCCESS_MSG),orderId);
            } else{
                return new OrderResponse(new Response(Constant.DEFAULT_ERROR,"Not enough margin available."));
            }
        } else if(input.getOrderType().equalsIgnoreCase(OrderHistory.orderType.SELL.name())){
            List<OrderHistory> orderHistoryList = DBUtil.getCompletedOrderHistoryWithserIdAndSymbol(input.getUserId(),input.getSymbol(),con);
            int ownStockCount = 0;
            if (input.getTradeType().equalsIgnoreCase(OrderHistory.tradeType.INTRADAY.name())) {
                for(OrderHistory order : orderHistoryList){
                    if(order.getTradeType().equalsIgnoreCase(OrderHistory.tradeType.INTRADAY.name()) && order.getOrderEndTime().after(DateUtils.truncate(new Date(), Calendar.DAY_OF_MONTH))) {
                        if(order.getOrderType().equalsIgnoreCase(OrderHistory.orderType.BUY.name())){
                            ownStockCount += order.getQuantity();
                        } else {
                            ownStockCount -= order.getQuantity();
                        }
                    }
                }
            } else if(input.getTradeType().equalsIgnoreCase(OrderHistory.tradeType.INTERDAY.name())){
                for(OrderHistory order : orderHistoryList){
                    if(order.getTradeType().equalsIgnoreCase(OrderHistory.tradeType.INTERDAY.name())) {
                        if(order.getOrderType().equalsIgnoreCase(OrderHistory.orderType.BUY.name())){
                            ownStockCount += order.getQuantity();
                        } else {
                            ownStockCount -= order.getQuantity();
                        }
                    }
                }
            }
            if(ownStockCount>=input.getQuantity()){
                String orderId = UUID.randomUUID().toString();
                boolean orderResult = DBUtil.addOrder(new OrderHistory(input.getUserId(),orderId,OrderHistory.orderType.SELL.name(),input.getTradeType().toUpperCase(),input.getSymbol(),input.getQuantity(),stockData.getLatestPrice(),OrderHistory.orderStatus.COMPLETE.toString()),con);
                if(orderResult) return new OrderResponse(new Response(Constant.SUCCESS,Constant.SUCCESS_MSG),orderId);
            } else{
                return new OrderResponse(new Response(Constant.DEFAULT_ERROR,"Not enough stocks to sell."));
            }
        }
        return new OrderResponse(new Response(Constant.DEFAULT_ERROR,Constant.DEFAULT_ERROR_MSG));
    }

    public boolean validateTime(){
        try {
            DateFormat sdf = new SimpleDateFormat("hh:mm:ss");
            Date start = sdf.parse("09:30:00");
            Date end = sdf.parse( "16:00:00");
            Calendar now = Calendar.getInstance();
            int day_of_week = now.get(Calendar.DAY_OF_WEEK);
            if(day_of_week>5) return false;
            if(! now.getTime().before( start ) && now.getTime().before( end ) ) return true;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return false;
    }
    public static double getAvailableCredit(String userId,Connection con){
        List<TransactionHistory> transactionHistoryList = DBUtil.getTransactionHistory(userId,con);
        double availableCredit  = 0;
        if(transactionHistoryList != null && transactionHistoryList.size()>0) {
            for (TransactionHistory transaction : transactionHistoryList) {
                if(transaction.getTransactionType().equalsIgnoreCase(TransactionHistory.transactionType.CREDIT.name())){
                    availableCredit += transaction.getAmount();
                } else if(transaction.getTransactionType().equalsIgnoreCase(TransactionHistory.transactionType.DEBIT.name())){
                    availableCredit -= transaction.getAmount();
                }
            }
        }
        return availableCredit;
    }
    public static double getMargin(String userId,Connection con){
        List<OrderHistory> orderHistoryList= DBUtil.getCompletedOrderHistory(userId,con);
        double margin  = 0;
        if(orderHistoryList != null && orderHistoryList.size()>0) {
            for (OrderHistory order : orderHistoryList) {
                if (order != null && CommonUtil.isValidString(order.getOrderType()) && CommonUtil.isValidString(order.getTradeType())) {
                    if (order.getOrderType().equalsIgnoreCase(OrderHistory.orderType.BUY.name())) {
                        if (order.getTradeType().equalsIgnoreCase(OrderHistory.tradeType.INTRADAY.name()) && order.getOrderEndTime().after(DateUtils.truncate(new Date(), Calendar.DAY_OF_MONTH))) {
                            margin -= (order.getQuantity() * order.getUnitPrice() * 0.1);
                        } else {
                            margin -= (order.getQuantity() * order.getUnitPrice());
                        }
                    } else if (order.getOrderType().equalsIgnoreCase(OrderHistory.orderType.SELL.name())) {
                        if (order.getTradeType().equalsIgnoreCase(OrderHistory.tradeType.INTRADAY.name()) && order.getOrderEndTime().after(DateUtils.truncate(new Date(), Calendar.DAY_OF_MONTH))) {
                            continue;
                        } else {
                            margin += (order.getQuantity() * order.getUnitPrice());
                        }
                    }
                }
            }
        }
        return  margin;
    }

}
