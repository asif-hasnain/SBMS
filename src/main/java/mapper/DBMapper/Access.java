package mapper.DBMapper;


import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Access {
    @Column(name="user_id")
    private String user_id;
    @Column(name="role_id")
    private String role_id;
    @Column(name="feature_id")
    private String feature_id;

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getRole_id() {
        return role_id;
    }

    public void setRole_id(String role_id) {
        this.role_id = role_id;
    }

    public String getFeature_id() {
        return feature_id;
    }

    public void setFeature_id(String feature_id) {
        this.feature_id = feature_id;
    }

    @Override
    public String toString() {
        return "Access{" +
                "user_id='" + user_id + '\'' +
                ", role_id='" + role_id + '\'' +
                ", feature_id='" + feature_id + '\'' +
                '}';
    }
}
