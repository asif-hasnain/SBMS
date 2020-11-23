package mapper;

import java.util.List;

public class GetStockDataRequest {
    List<String> stockList;

    public GetStockDataRequest() {
    }

    public GetStockDataRequest(List<String> stockList) {
        this.stockList = stockList;
    }

    public List<String> getStockList() {
        return stockList;
    }

    public void setStockList(List<String> stockList) {
        this.stockList = stockList;
    }

    @Override
    public String toString() {
        return "UpdateStockRequest{" +
                "stockList=" + stockList +
                '}';
    }
}
