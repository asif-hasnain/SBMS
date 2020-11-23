package mapper;

import util.Response;

public class LoginResponse {
	private Response response;
	private String name;
	private String userId;
	private String userType;
	private String auth_key;
	private String brokerage_id;
	private int id;

	public LoginResponse(Response response) {
		this.response = response;
	}

	public LoginResponse(Response response, String name, String userId, String userType, String auth_key, String brokerage_id, int id) {
		this.response = response;
		this.name = name;
		this.userId = userId;
		this.userType = userType;
		this.auth_key = auth_key;
		this.brokerage_id = brokerage_id;
		this.id = id;
	}

	public LoginResponse(Response response, String name, int id) {
		this.response = response;
		this.name = name;
		this.id = id;
	}

	public Response getResponse() {
		return response;
	}

	public void setResponse(Response response) {
		this.response = response;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getAuth_key() {
		return auth_key;
	}

	public void setAuth_key(String auth_key) {
		this.auth_key = auth_key;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getBrokerage_id() {
		return brokerage_id;
	}

	public void setBrokerage_id(String brokerage_id) {
		this.brokerage_id = brokerage_id;
	}

	@Override
	public String toString() {
		return "LoginResponse{" +
				"response=" + response +
				", name='" + name + '\'' +
				", userId='" + userId + '\'' +
				", userType='" + userType + '\'' +
				", auth_key='" + auth_key + '\'' +
				", brokerage_id='" + brokerage_id + '\'' +
				", id=" + id +
				'}';
	}
}
