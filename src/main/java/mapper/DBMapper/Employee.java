package mapper.DBMapper;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Employee {
    @Column(name="user_id")
    private String userId;
    @Column(name="designation")
    private String designation;
    @Column(name="salary")
    private Double salary;
    @Column(name="commision")
    private Double commision;
    @Column(name="superviser")
    private String superviser;
    @Column(name="employee_id")
    private int employee_id;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public Double getCommision() {
        return commision;
    }

    public void setCommision(Double commision) {
        this.commision = commision;
    }

    public String getSuperviser() {
        return superviser;
    }

    public void setSuperviser(String superviser) {
        this.superviser = superviser;
    }

    public int getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(int employee_id) {
        this.employee_id = employee_id;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "userId='" + userId + '\'' +
                ", designation='" + designation + '\'' +
                ", salary=" + salary +
                ", commision=" + commision +
                ", superviser='" + superviser + '\'' +
                ", employee_id=" + employee_id +
                '}';
    }
}
