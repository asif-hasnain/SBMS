package mapper;

public class GetSubscriptionRequest {
    String userId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
    @Override
    public String toString() {
        return "GetSubscriptionRequest{" +
                "user_id='" + userId + '\'' +
                '}';
    }
}
