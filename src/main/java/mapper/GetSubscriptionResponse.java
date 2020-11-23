package mapper;

import mapper.DBMapper.StockData;
import util.Response;

import java.util.List;

public class GetSubscriptionResponse {
    Response response;
    List<StockData> subscriptionList;
    List<StockData> stockList;
    double totalInvestment;
    double marginAvailable;
    public GetSubscriptionResponse(Response response) {
        this.response = response;
    }

    public GetSubscriptionResponse(Response response, List<StockData> subscriptionList, List<StockData> stockList, double totalInvestment, double marginAvailable) {
        this.response = response;
        this.subscriptionList = subscriptionList;
        this.stockList = stockList;
        this.totalInvestment = totalInvestment;
        this.marginAvailable = marginAvailable;
    }

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }

    public List<StockData> getStockList() {
        return stockList;
    }

    public void setStockList(List<StockData> stockList) {
        this.stockList = stockList;
    }

    public List<StockData> getSubscriptionList() {
        return subscriptionList;
    }

    public void setSubscriptionList(List<StockData> subscriptionList) {
        this.subscriptionList = subscriptionList;
    }

    public double getTotalInvestment() {
        return totalInvestment;
    }

    public void setTotalInvestment(double totalInvestment) {
        this.totalInvestment = totalInvestment;
    }

    public double getMarginAvailable() {
        return marginAvailable;
    }

    public void setMarginAvailable(double marginAvailable) {
        this.marginAvailable = marginAvailable;
    }

    @Override
    public String toString() {
        return "GetSubscriptionResponse{" +
                "response=" + response +
                ", subscriptionList=" + subscriptionList +
                ", stockList=" + stockList +
                ", creditAvailable=" + totalInvestment +
                ", marginAvailable=" + marginAvailable +
                '}';
    }
}
