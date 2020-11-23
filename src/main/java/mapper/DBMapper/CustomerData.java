package mapper.DBMapper;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.sql.Date;

@Entity
public class CustomerData {
    @Column(name="user_id")
    String user_id;
    @Column(name="first_name")
    String first_name;
    @Column(name="last_name")
    String last_name;
    @Column(name="dob")
    Date dob;
    @Column(name="gender")
    String gender;
    @Column(name="email_id")
    String email_id;
    @Column(name="relationship_manager_id")
    int relationship_manager_id;
    @Column(name="customer_id")
    int customer_id;

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
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

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail_id() {
        return email_id;
    }

    public void setEmail_id(String email_id) {
        this.email_id = email_id;
    }

    public int getRelationship_manager_id() {
        return relationship_manager_id;
    }

    public void setRelationship_manager_id(int relationship_manager_id) {
        this.relationship_manager_id = relationship_manager_id;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    @Override
    public String toString() {
        return "CustomerData{" +
                "user_id='" + user_id + '\'' +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", dob=" + dob +
                ", gender='" + gender + '\'' +
                ", email_id='" + email_id + '\'' +
                ", relationship_manager_id='" + relationship_manager_id + '\'' +
                ", customer_id='" + customer_id + '\'' +
                '}';
    }
}
