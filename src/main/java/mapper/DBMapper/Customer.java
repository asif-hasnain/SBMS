package mapper.DBMapper;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Customer {
    @Column(name="user_id")
    private String userId;
    @Column(name="relationship_manager")
    private String relationship_manager;
    @Column(name="customer_id")
    private int customer_id;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getRelationship_manager() {
        return relationship_manager;
    }

    public void setRelationship_manager(String relationship_manager) {
        this.relationship_manager = relationship_manager;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    @Override
    public String toString() {
        return "customer{" +
                "userId='" + userId + '\'' +
                ", relationship_manager='" + relationship_manager + '\'' +
                ", customer_id=" + customer_id +
                '}';
    }
}
