package mapper.DBMapper;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.util.Date;

@Entity
public class User {
	@Column(name="user_id")
	private String userId;
	@Column(name="first_name")
	private String first_name;
	@Column(name="last_name")
	private String last_name;
	@Column(name="gender")
	private String gender;
	@Column(name="user_type")
	private String user_type;
	@Column(name="designation")
	private String designation;
	@Column(name="emp_department")
	private String emp_department;
	@Column(name="email_id")
	private String email_id;
	@Column(name="password")
	private String password;
	@Column(name="updated_date")
	private Date updated_date;
	@Column(name="created_date")
	private Date created_date;
	@Column(name="dob")
	private Date dob;
	@Column(name="brokerage_id")
	private String brokerage_id;

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public User(String userId, String first_name, String last_name, String gender, String user_type, String designation, String emp_department, String email_id, String password, Date updated_date, Date created_date, Date dob, String brokerage_id) {
		this.userId = userId;
		this.first_name = first_name;
		this.last_name = last_name;
		this.gender = gender;
		this.user_type = user_type;
		this.designation = designation;
		this.emp_department = emp_department;
		this.email_id = email_id;
		this.password = password;
		this.updated_date = updated_date;
		this.created_date = created_date;
		this.dob = dob;
		this.brokerage_id = brokerage_id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getUser_type() {
		return user_type;
	}

	public void setUser_type(String user_type) {
		this.user_type = user_type;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getEmp_department() {
		return emp_department;
	}

	public void setEmp_department(String emp_department) {
		this.emp_department = emp_department;
	}

	public String getEmail_id() {
		return email_id;
	}

	public void setEmail_id(String email_id) {
		this.email_id = email_id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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
	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getBrokerage_id() {
		return brokerage_id;
	}

	public void setBrokerage_id(String brokerage_id) {
		this.brokerage_id = brokerage_id;
	}

	@Override
	public String toString() {
		return "User{" +
				"user_id=" + userId +
				", first_name='" + first_name + '\'' +
				", last_name='" + last_name + '\'' +
				", gender='" + gender + '\'' +
				", user_type='" + user_type + '\'' +
				", designation='" + designation + '\'' +
				", emp_department='" + emp_department + '\'' +
				", email_id='" + email_id + '\'' +
				", password='" + password + '\'' +
				", updated_date=" + updated_date +
				", created_date=" + created_date +
				", dob=" + dob +
				", brokerage_id=" + brokerage_id +
				'}';
	}
}
