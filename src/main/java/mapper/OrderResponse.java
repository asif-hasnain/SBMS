package mapper;

import util.Response;

public class OrderResponse {
    Response response;
    String orderId;

    public OrderResponse() {
    }

    public OrderResponse(Response response) {
        this.response = response;
    }

    public OrderResponse(Response response, String orderId) {
        this.response = response;
        this.orderId = orderId;
    }

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    @Override
    public String toString() {
        return "OrderResponse{" +
                "response=" + response +
                ", orderId='" + orderId + '\'' +
                '}';
    }
}
