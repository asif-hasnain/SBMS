package mapper.DBMapper;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Authentication {
	@Column(name="authentication_id")
	private String auth_key;
	@Column(name="user_id")
	private String user_id;
	@Column(name="updated_date")
	private Date updated_date;
	@Column(name="created_date")
	private Date created_date;
	public Authentication() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getAuth_key() {
		return auth_key;
	}

	public void setAuth_key(String auth_key) {
		this.auth_key = auth_key;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public Date getUpdated_date() {
		return updated_date;
	}

	public void setUpdated_date(Date updated_date) {
		this.updated_date = updated_date;
	}

	public Date getCreated_date() {
		return created_date;
	}

	public void setCreated_date(Date created_date) {
		this.created_date = created_date;
	}

	@Override
	public String toString() {
		return "Authentication{" +
				"auth_key='" + auth_key + '\'' +
				", user_id=" + user_id +
				", updated_date=" + updated_date +
				", created_date=" + created_date +
				'}';
	}
}
