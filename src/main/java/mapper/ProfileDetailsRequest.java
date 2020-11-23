package mapper;

public class ProfileDetailsRequest {

	private String userId;

	public ProfileDetailsRequest(String userId) {
		super();
		this.userId = userId;
	}
	public ProfileDetailsRequest() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "ProfileDetailsRequest [userId=" + userId + "]";
	}
	
}
