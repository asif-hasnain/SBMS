package mapper.DBMapper;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.util.Date;

@Entity
public class Address {
    @Column(name="user_id")
    private String user_id;
    @Column(name ="address_id")
    private long address_id;
    @Column(name ="address_type")
    private String address_type;
    @Column(name ="street_address")
    private String street_address;
    @Column(name ="city")
    private String city;
    @Column(name ="state")
    private String state;
    @Column(name="zipcode")
    private int zipcode;
    @Column(name="updated_date")
    private Date updated_date;
    @Column(name="created_date")
    private Date created_date;

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public long getAddress_id() {
        return address_id;
    }

    public void setAddress_id(long address_id) {
        this.address_id = address_id;
    }

    public String getAddress_type() {
        return address_type;
    }

    public void setAddress_type(String address_type) {
        this.address_type = address_type;
    }

    public String getStreet_address() {
        return street_address;
    }

    public void setStreet_address(String street_address) {
        this.street_address = street_address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getZipcode() {
        return zipcode;
    }

    public void setZipcode(int zipcode) {
        this.zipcode = zipcode;
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
        return "Address{" +
                "user_id=" + user_id +
                ", address_id=" + address_id +
                ", address_type='" + address_type + '\'' +
                ", street_address='" + street_address + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", zipcode=" + zipcode +
                ", updated_date=" + updated_date +
                ", created_date=" + created_date +
                '}';
    }
}
