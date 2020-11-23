package lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import mapper.OrderHistoryRequest;
import mapper.OrderHistoryResponse;
import util.*;

import java.sql.Connection;
import java.util.List;

public class OrderHistory implements RequestHandler<OrderHistoryRequest, OrderHistoryResponse> {
    Connection con = null;
    @Override
    public OrderHistoryResponse handleRequest(OrderHistoryRequest input, Context context) {
        System.out.println("input :" + input);
        if(input == null || !CommonUtil.isValidString(input.getUserId())){
            return new OrderHistoryResponse(new Response(Constant.INVALID_INPUT,Constant.INVALID_INPUT_MSG));
        }
        con = JDBCConnection.getJDBCCOnnection(con, 0);
        List<mapper.DBMapper.OrderHistory> orderHistoryList = DBUtil.getCompletedOrderHistory(input.getUserId(),con);
        if(orderHistoryList != null && orderHistoryList.size()>0){
            return new OrderHistoryResponse(new Response(Constant.SUCCESS,Constant.SUCCESS_MSG),orderHistoryList);
        }
        return new OrderHistoryResponse(new Response(Constant.DATA_NOT_AVAILABLE,Constant.DATA_NOT_AVAILABE_MSG));
    }
}
