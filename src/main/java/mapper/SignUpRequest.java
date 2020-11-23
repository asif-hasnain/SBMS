package mapper;

import java.util.Date;

public class SignUpRequest {
	private String emailId;
	private String firstName;
	private String lastName;
	private String password;
	private Date dob;
	private String gender;
	private String st_address_primary;
	private String city_primary;
	private String state_primary;
	private int zipcode_primary;
	private String st_address_secondary;
	private String city_secondary;
	private String state_secondary;
	private int zipcode_secondary;
	private String user_type;
	private String designation;
	private String emp_department;
	private String membership_type;
	private String relationship_manager;
	private String superviser_id;
	private String brokerage_id;
	private String brokerage_name;
	private double salary;
	private double commision;

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getSt_address_primary() {
		return st_address_primary;
	}

	public void setSt_address_primary(String st_address_primary) {
		this.st_address_primary = st_address_primary;
	}

	public String getCity_primary() {
		return city_primary;
	}

	public void setCity_primary(String city_primary) {
		this.city_primary = city_primary;
	}

	public String getState_primary() {
		return state_primary;
	}

	public void setState_primary(String state_primary) {
		this.state_primary = state_primary;
	}

	public int getZipcode_primary() {
		return zipcode_primary;
	}

	public void setZipcode_primary(int zipcode_primary) {
		this.zipcode_primary = zipcode_primary;
	}

	public String getSt_address_secondary() {
		return st_address_secondary;
	}

	public void setSt_address_secondary(String st_address_secondary) {
		this.st_address_secondary = st_address_secondary;
	}

	public String getCity_secondary() {
		return city_secondary;
	}

	public void setCity_secondary(String city_secondary) {
		this.city_secondary = city_secondary;
	}

	public String getState_secondary() {
		return state_secondary;
	}

	public void setState_secondary(String state_secondary) {
		this.state_secondary = state_secondary;
	}

	public int getZipcode_secondary() {
		return zipcode_secondary;
	}

	public void setZipcode_secondary(int zipcode_secondary) {
		this.zipcode_secondary = zipcode_secondary;
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

	public String getMembership_type() {
		return membership_type;
	}

	public void setMembership_type(String membership_type) {
		this.membership_type = membership_type;
	}

	public String getRelationship_manager() {
		return relationship_manager;
	}

	public void setRelationship_manager(String relationship_manager) {
		this.relationship_manager = relationship_manager;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getSuperviser_id() {
		return superviser_id;
	}

	public void setSuperviser_id(String superviser_id) {
		this.superviser_id = superviser_id;
	}

	public String getBrokerage_id() {
		return brokerage_id;
	}

	public void setBrokerage_id(String brokerage_id) {
		this.brokerage_id = brokerage_id;
	}

	public String getBrokerage_name() {
		return brokerage_name;
	}

	public void setBrokerage_name(String brokerage_name) {
		this.brokerage_name = brokerage_name;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public double getCommision() {
		return commision;
	}

	public void setCommision(double commision) {
		this.commision = commision;
	}

	@Override
	public String toString() {
		return "SignUpRequest{" +
				"emailId='" + emailId + '\'' +
				", firstName='" + firstName + '\'' +
				", lastName='" + lastName + '\'' +
				", password='" + password + '\'' +
				", dob=" + dob +
				", gender='" + gender + '\'' +
				", st_address_primary='" + st_address_primary + '\'' +
				", city_primary='" + city_primary + '\'' +
				", state_primary='" + state_primary + '\'' +
				", zipcode_primary='" + zipcode_primary + '\'' +
				", st_address_secondary='" + st_address_secondary + '\'' +
				", city_secondary='" + city_secondary + '\'' +
				", state_secondary='" + state_secondary + '\'' +
				", zipcode_secondary='" + zipcode_secondary + '\'' +
				", user_type='" + user_type + '\'' +
				", designation='" + designation + '\'' +
				", emp_department='" + emp_department + '\'' +
				", membership_type='" + membership_type + '\'' +
				", relationship_manager='" + relationship_manager + '\'' +
				", superviser_id='" + superviser_id + '\'' +
				", brokerage_id='" + brokerage_id + '\'' +
				", brokerage_name='" + brokerage_name + '\'' +
				'}';
	}
}
