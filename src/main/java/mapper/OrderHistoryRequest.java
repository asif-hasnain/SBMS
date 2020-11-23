package mapper;

public class OrderHistoryRequest {
    String userId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "OrderHistoryRequest{" +
                "userId='" + userId + '\'' +
                '}';
    }
}
