package mapper.DBMapper;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class EmployeeData {
    @Column(name="user_id")
    String user_id;
    @Column(name="first_name")
    String first_name;
    @Column(name="last_name")
    String last_name;
    @Column(name="gender")
    String gender;
    @Column(name="email_id")
    String email_id;
    @Column(name="superviser_id")
    int superviser_id;
    @Column(name="employee_id")
    int employee_id;
    @Column(name="designation")
    String designation;

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

    public int getSuperviser_id() {
        return superviser_id;
    }

    public void setSuperviser_id(int superviser_id) {
        this.superviser_id = superviser_id;
    }

    public int getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(int employee_id) {
        this.employee_id = employee_id;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    @Override
    public String toString() {
        return "EmployeeData{" +
                "user_id='" + user_id + '\'' +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", gender='" + gender + '\'' +
                ", email_id='" + email_id + '\'' +
                ", superviser_id='" + superviser_id + '\'' +
                ", employee_id='" + employee_id + '\'' +
                ", designation='" + designation + '\'' +
                '}';
    }
}
