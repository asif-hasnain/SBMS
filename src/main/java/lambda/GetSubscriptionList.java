package lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import mapper.DBMapper.StockData;
import mapper.GetSubscriptionRequest;
import mapper.GetSubscriptionResponse;
import util.*;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class GetSubscriptionList implements RequestHandler<GetSubscriptionRequest, GetSubscriptionResponse> {
    Connection con = null;
    @Override
    public GetSubscriptionResponse handleRequest(GetSubscriptionRequest input, Context context) {
        System.out.println("input: "+ input);
        if(input == null || !CommonUtil.isValidString(input.getUserId())) {
            return new GetSubscriptionResponse(new Response(Constant.DEFAULT_ERROR, Constant.DEFAULT_ERROR_MSG));
        }
        con = JDBCConnection.getJDBCCOnnection(con, 0);

        try {
            List<StockData> subscriptionList = DBUtil.getSubscriptionList(input.getUserId(),con);
            List<StockData> stockList = new ArrayList<>(subscriptionList);
            subscriptionList.removeIf(s -> !s.isSubscribed());
            if(subscriptionList==null || subscriptionList.size()==0){
                return new GetSubscriptionResponse(new Response(Constant.DATA_NOT_AVAILABLE, Constant.DATA_NOT_AVAILABE_MSG));
            }
            double margin = Math.round(Order.getMargin(input.getUserId(),con)*100)/100;
            double credit = Math.round(Order.getAvailableCredit(input.getUserId(),con)*100)/100;
            return new GetSubscriptionResponse(new Response(Constant.SUCCESS,Constant.SUCCESS_MSG),subscriptionList,stockList,Math.abs(margin),credit+margin);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
