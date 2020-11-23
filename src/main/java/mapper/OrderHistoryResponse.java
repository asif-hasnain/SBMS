package mapper;

import mapper.DBMapper.OrderHistory;
import util.Response;

import java.util.List;

public class OrderHistoryResponse {
    List<OrderHistory> orderHistoryList;
    Response response;

    public OrderHistoryResponse(Response response, List<OrderHistory> orderHistoryList) {
        this.orderHistoryList = orderHistoryList;
        this.response = response;
    }

    public OrderHistoryResponse(Response response) {
        this.response = response;
    }

    public List<OrderHistory> getOrderHistoryList() {
        return orderHistoryList;
    }

    public void setOrderHistoryList(List<OrderHistory> orderHistoryList) {
        this.orderHistoryList = orderHistoryList;
    }

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }

    @Override
    public String toString() {
        return "OrderHistoryResponse{" +
                "orderHistoryList=" + orderHistoryList +
                ", response=" + response +
                '}';
    }
}
