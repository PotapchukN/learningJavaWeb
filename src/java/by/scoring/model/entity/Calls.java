package by.scoring.model.entity;



import javax.persistence.*;
import java.io.Serializable;

@Entity(name="calls")
public class Calls implements Serializable {

    static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name="topic")
    private String topic;

    @Column(name="name")
    private String name;

    @Column(name="phone")
    private String phone;

    @Column(name="email")
    private String email;

    public Long getId() {return id;}

    public String getName() {return name;}

    public void setId(Long id) {this.id = id;}

    public void setName(String name) {this.name = name;}

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Calls)) return false;

        Calls calls = (Calls) o;

        if (id != null ? !id.equals(calls.id) : calls.id != null) return false;
        if (topic != null ? !topic.equals(calls.topic) : calls.topic != null) return false;
        if (name != null ? !name.equals(calls.name) : calls.name != null) return false;
        if (phone != null ? !phone.equals(calls.phone) : calls.phone != null) return false;
        return email != null ? email.equals(calls.email) : calls.email == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (topic != null ? topic.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        return result;
    }
}



