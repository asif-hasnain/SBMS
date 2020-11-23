package mapper.DBMapper;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Brokerage {
    @Column(name="brokerage_id")
    private String brokerageId;
    @Column(name="brokerage_name")
    private String brokerage_name;
    @Column(name="street_address")
    private String street_address;
    @Column(name="city")
    private String city;
    @Column(name="state")
    private String state;
    @Column(name="zipcode")
    private int zipcode;
    @Column(name="membership_id")
    private String membership_id;
    @Column(name="membership_type")
    private String membership_type;

    public String getBrokerageId() {
        return brokerageId;
    }

    public void setBrokerageId(String brokerageId) {
        this.brokerageId = brokerageId;
    }

    public String getBrokerage_name() {
        return brokerage_name;
    }

    public void setBrokerage_name(String brokerage_name) {
        this.brokerage_name = brokerage_name;
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

    public String getMembership_id() {
        return membership_id;
    }

    public void setMembership_id(String membership_id) {
        this.membership_id = membership_id;
    }

    public String getMembership_type() {
        return membership_type;
    }

    public void setMembership_type(String membership_type) {
        this.membership_type = membership_type;
    }

    @Override
    public String toString() {
        return "Brokerage{" +
                "brokerageId='" + brokerageId + '\'' +
                ", brokerage_name='" + brokerage_name + '\'' +
                ", street_address='" + street_address + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", zipcode=" + zipcode +
                ", membership_id='" + membership_id + '\'' +
                ", membership_type='" + membership_type + '\'' +
                '}';
    }
}
