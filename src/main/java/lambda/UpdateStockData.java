package lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import mapper.IEXData;
import mapper.GetStockDataRequest;
import mapper.GetStockDataResponse;
import util.*;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UpdateStockData implements RequestHandler<UpdateStockDataRequest,UpdateStockDataResponse> {
    Connection con = null;
    @Override
    public UpdateStockDataResponse handleRequest(UpdateStockDataRequest input, Context context) {
        System.out.println("Input: "+input);
        try {
            List<String> stockList = new ArrayList<>();
            if(input != null && CommonUtil.isValidString(input.getSymbol())){
                stockList.add(input.getSymbol());
            } else {
                stockList.addAll(Arrays.asList(Constant.STOCK_LIST));
            }
            GetStockDataResponse stockDataResponse = new GetStockData().handleRequest(new GetStockDataRequest(stockList),context);
            if(stockDataResponse!=null && stockDataResponse.getResponse() != null
                    && stockDataResponse.getResponse().getCode()==Constant.SUCCESS && stockDataResponse.getStockMap()!=null
                        && stockDataResponse.getStockMap().size()>0){
                con = JDBCConnection.getJDBCCOnnection(con, 0);
                System.out.println("GetStockDataResponse stockDataResponse : " + stockDataResponse);
                List<IEXData> list = new ArrayList<IEXData>(stockDataResponse.getStockMap().values());
                DBUtil.addStockData(list, con);
                return new UpdateStockDataResponse(list.get(0),new Response(Constant.SUCCESS,Constant.SUCCESS_MSG));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new UpdateStockDataResponse(new Response(Constant.DEFAULT_ERROR,Constant.DEFAULT_ERROR_MSG));
    }
}
class UpdateStockDataRequest{
    String symbol;

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    @Override
    public String toString() {
        return "UpdateStockDataRequest{" +
                "symbol='" + symbol + '\'' +
                '}';
    }
}
class UpdateStockDataResponse{
    IEXData stockData;
    Response response;

    public UpdateStockDataResponse(Response response) {
        this.response = response;
    }

    public UpdateStockDataResponse(IEXData stockData, Response response) {
        this.stockData = stockData;
        this.response = response;
    }

    public IEXData getStockData() {
        return stockData;
    }

    public void setStockData(IEXData stockData) {
        this.stockData = stockData;
    }

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }

    @Override
    public String toString() {
        return "UpdateStockDataResponse{" +
                "stockData=" + stockData +
                ", response=" + response +
                '}';
    }
}