package lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import mapper.AddTransactionRequest;
import mapper.AddTransactionResponse;
import mapper.DBMapper.OrderHistory;
import mapper.DBMapper.TransactionHistory;
import util.*;

import java.sql.Connection;
import java.util.UUID;

public class AddTransaction implements RequestHandler<AddTransactionRequest, AddTransactionResponse> {
    Connection con = null;
    @Override
    public AddTransactionResponse handleRequest(AddTransactionRequest input, Context context) {
        System.out.println("input :" + input);
        if (input == null || !CommonUtil.isValidString(input.getUserId()) || !CommonUtil.isValidString(input.getTransactionType())
                || !(input.getTransactionType().equalsIgnoreCase(TransactionHistory.transactionType.DEBIT.name()) || input.getTransactionType().equalsIgnoreCase(TransactionHistory.transactionType.CREDIT.name()))
                || input.getAmount() <= 0) {
            return new AddTransactionResponse(new Response(Constant.INVALID_INPUT, Constant.INVALID_INPUT_MSG));
        }
        con = JDBCConnection.getJDBCCOnnection(con, 0);
        if(input.getTransactionType().equalsIgnoreCase(TransactionHistory.transactionType.DEBIT.name())) {
            double creditAvailable = Order.getAvailableCredit(input.getUserId(),con);
            System.out.println("creditAvailable: " + creditAvailable);
            double margin = creditAvailable + Order.getMargin(input.getUserId(),con);
            System.out.println("margin: " + margin);
            if (margin < input.getAmount()){
                return new AddTransactionResponse(new Response(Constant.DEFAULT_ERROR, "No enough margin available. Only $" + String.format("%.2f", margin) + " can be debited"));
            }
        }
        String transactionId = UUID.randomUUID().toString();
        boolean transactionResult = DBUtil.addTransaction(transactionId, input.getUserId(), input.getTransactionType().toUpperCase(), input.getAmount(), con);
        if (transactionResult) {
            return new AddTransactionResponse(transactionId, new Response(Constant.SUCCESS, Constant.SUCCESS_MSG));
        } else {
            return new AddTransactionResponse(new Response(Constant.DEFAULT_ERROR, Constant.DEFAULT_ERROR_MSG));
        }
    }
}
