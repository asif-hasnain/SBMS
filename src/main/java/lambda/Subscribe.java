package lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import mapper.DBMapper.StockData;
import mapper.OrderResponse;
import mapper.SubscribeRequest;
import util.*;

import java.sql.Connection;


public class Subscribe implements RequestHandler<SubscribeRequest, Response> {
    Connection con = null;
    @Override
    public Response handleRequest(SubscribeRequest input, Context context) {
        System.out.println("input: " + input);
        if(input == null || !CommonUtil.isValidString(input.getUserId()) || !CommonUtil.isValidString(input.getSymbol())){
            return new Response(Constant.INVALID_INPUT,Constant.INVALID_INPUT_MSG);
        }
        con = JDBCConnection.getJDBCCOnnection(con, 0);
        StockData stockData = DBUtil.getStockData(input.getSymbol(),con);
        if(stockData == null) {
            return new Response(Constant.DEFAULT_ERROR,"Stock not supported.");
        }
        boolean subscribe = DBUtil.subscribe(input.getUserId(),stockData.getSymbol(),con);
        if(subscribe) {
            return new Response(Constant.SUCCESS, Constant.SUCCESS_MSG);
        }else return new Response(Constant.DEFAULT_ERROR,Constant.DEFAULT_ERROR_MSG);
    }
}
