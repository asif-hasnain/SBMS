package mapper;

public class SubscribeRequest {
    String userId;
    String symbol;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    @Override
    public String toString() {
        return "SubscribeRequest{" +
                "userId='" + userId + '\'' +
                ", symbol='" + symbol + '\'' +
                '}';
    }
}
