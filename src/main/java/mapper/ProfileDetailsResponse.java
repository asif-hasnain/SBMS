package mapper;


import mapper.DBMapper.Address;
import util.Response;

import java.util.Date;
import java.util.List;

public class ProfileDetailsResponse {
    private Response response;
    private String emailId;
    private String firstName;
    private String lastName;
    private String gender;
    private Date dob;
    private List<Address> address;
    private String userType;
    private String designation;
    private String membership_type;
    private String relationship_manager;
    private int superviser_id;
    private String brokerage_id;
    private String brokerage_name;
    private int customer_id;
    private int employee_id;

    public ProfileDetailsResponse(Response response) {
        this.response = response;
    }

    public ProfileDetailsResponse(Response response, String emailId, String firstName, String lastName, String gender, Date dob, List<Address> address, String userType, String relationship_manager, int customer_id) {
        this.response = response;
        this.emailId = emailId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.dob = dob;
        this.address = address;
        this.userType = userType;
        this.relationship_manager = relationship_manager;
        this.customer_id = customer_id;
    }

    public ProfileDetailsResponse(Response response, String emailId, String firstName, String lastName, String gender, Date dob, List<Address> address, String userType, String designation, int superviser_id, int employee_id) {
        this.response = response;
        this.emailId = emailId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.dob = dob;
        this.address = address;
        this.userType = userType;
        this.designation = designation;
        this.superviser_id = superviser_id;
        this.employee_id = employee_id;
    }

    public ProfileDetailsResponse(Response response, String emailId, String firstName, String lastName, String gender, Date dob, List<Address> address, String userType, String membership_type, String brokerage_name) {
        this.response = response;
        this.emailId = emailId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.dob = dob;
        this.address = address;
        this.userType = userType;
        this.membership_type = membership_type;
        this.brokerage_name = brokerage_name;
    }

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }

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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public List<Address> getAddress() {
        return address;
    }

    public void setAddress(List<Address> address) {
        this.address = address;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
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

    public int getSuperviser_id() {
        return superviser_id;
    }

    public void setSuperviser_id(int superviser_id) {
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

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public int getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(int employee_id) {
        this.employee_id = employee_id;
    }

    @Override
    public String toString() {
        return "ProfileDetailsResponse{" +
                "response=" + response +
                ", emailId='" + emailId + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", gender='" + gender + '\'' +
                ", dob=" + dob +
                ", address=" + address +
                ", userType='" + userType + '\'' +
                ", designation='" + designation + '\'' +
                ", membership_type='" + membership_type + '\'' +
                ", relationship_manager='" + relationship_manager + '\'' +
                ", superviser_id=" + superviser_id +
                ", brokerage_id='" + brokerage_id + '\'' +
                ", brokerage_name='" + brokerage_name + '\'' +
                ", customer_id=" + customer_id +
                ", employee_id=" + employee_id +
                '}';
    }
}
