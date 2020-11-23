package mapper;

import util.Response;

public class AddTransactionResponse {
    String transactionId;
    Response response;

    public AddTransactionResponse(String transactionId, Response response) {
        this.transactionId = transactionId;
        this.response = response;
    }

    public AddTransactionResponse(Response response) {
        this.response = response;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }

    @Override
    public String toString() {
        return "AddTransactionResponse{" +
                "transactionId='" + transactionId + '\'' +
                ", response=" + response +
                '}';
    }
}
