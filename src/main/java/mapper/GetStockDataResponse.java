package mapper;

import util.Response;

import java.util.Map;

public class GetStockDataResponse {
    Map<String, IEXData> stockMap;
    Response response;

    public GetStockDataResponse() {
    }

    public GetStockDataResponse(Response response) {
        this.response = response;
    }

    public GetStockDataResponse(Map<String, IEXData> stockMap, Response response) {
        this.stockMap = stockMap;
        this.response = response;
    }

    public Map<String, IEXData> getStockMap() {
        return stockMap;
    }

    public void setStockMap(Map<String, IEXData> stockMap) {
        this.stockMap = stockMap;
    }

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }

    @Override
    public String toString() {
        return "UpdateStockResponse{" +
                "stockMap=" + stockMap +
                ", response=" + response +
                '}';
    }
}
