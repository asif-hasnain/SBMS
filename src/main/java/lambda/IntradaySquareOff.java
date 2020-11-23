package lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import mapper.DBMapper.OrderHistory;
import util.DBUtil;
import util.JDBCConnection;

import java.sql.Connection;
import java.sql.Date;
import java.util.*;

public class IntradaySquareOff implements RequestHandler<Object,Object> {
    Connection con = null;
    @Override
    public Object handleRequest(Object input, Context context) {
        try {
            con = JDBCConnection.getJDBCCOnnection(con, 0);
            List<OrderHistory> orderHistoryList= DBUtil.getAllIntradayOrders(new Date(Calendar.getInstance().getTimeInMillis()),con);
            if(orderHistoryList == null) return null;
            Map<String, Map<String, Integer>>  userSymbolMap = new HashMap<>();
            List<OrderHistory> addOderHistoryList = new ArrayList<>();
            for(OrderHistory orderHistory : orderHistoryList){
                Map<String, Integer> symbolMap = userSymbolMap.getOrDefault(orderHistory.getUserId(),new HashMap<>());
                int count = orderHistory.getOrderType().equalsIgnoreCase(OrderHistory.orderType.BUY.toString())? orderHistory.getQuantity():orderHistory.getQuantity()*-1;
                System.out.println("Count :" + count);
                symbolMap.put(orderHistory.getSymbol(),symbolMap.getOrDefault(orderHistory.getSymbol(),0)+count);
                userSymbolMap.put(orderHistory.getUserId(),symbolMap);
            }
            userSymbolMap.forEach((userId, v) -> v.forEach((symbol,count) ->
                    {if(count>0) addOderHistoryList.add(new OrderHistory(userId,UUID.randomUUID().toString(),OrderHistory.orderType.SELL.name(),OrderHistory.tradeType.INTRADAY.name(),symbol,count,0,OrderHistory.orderStatus.COMPLETE.toString()));
                    }
                     ));
            DBUtil.addIntradaySquareoffOrder(addOderHistoryList,con);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
