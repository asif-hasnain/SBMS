package lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import mapper.SubscribeRequest;
import util.*;

import java.sql.Connection;

public class Unsubscribe implements RequestHandler<SubscribeRequest, Response> {
    Connection con = null;
    @Override
    public Response handleRequest(SubscribeRequest input, Context context) {
        System.out.println("input: " + input);
        if(input == null || !CommonUtil.isValidString(input.getUserId()) || !CommonUtil.isValidString(input.getSymbol())){
            return new Response(Constant.INVALID_INPUT,Constant.INVALID_INPUT_MSG);
        }
        con = JDBCConnection.getJDBCCOnnection(con, 0);
        boolean unsubscribe = DBUtil.unsubscribe(input.getUserId(),input.getSymbol(),con);
        if(unsubscribe) {
            return new Response(Constant.SUCCESS, Constant.SUCCESS_MSG);
        }else return new Response(Constant.DEFAULT_ERROR,Constant.DEFAULT_ERROR_MSG);
    }
}
