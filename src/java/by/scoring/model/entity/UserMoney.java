package by.scoring.model.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="user_money")
public class UserMoney implements Serializable {

    static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name="income")
    private Float income;

    @Column(name="consumption")
    private Float consumption;

    public User getUser() {return user;}

    public void setUser(User user) {
        this.user = user;
    }

    @OneToOne
    @JoinColumn(name="user_id")
    private User user;

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Column(name="score")
    private int score;

    public Long getId() {return id;}

    public void setId(Long id) {this.id = id;}

    public Float getIncome() {return income;}

    public void setIncome(Float income) {this.income = income;}

    public Float getConsumption() {return consumption;}

    public void setConsumption(Float consumption) {this.consumption = consumption;}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserMoney)) return false;

        UserMoney userMoney = (UserMoney) o;

        if (score != userMoney.score) return false;
        if (id != null ? !id.equals(userMoney.id) : userMoney.id != null) return false;
        if (income != null ? !income.equals(userMoney.income) : userMoney.income != null) return false;
        if (consumption != null ? !consumption.equals(userMoney.consumption) : userMoney.consumption != null)
            return false;
        return user != null ? user.equals(userMoney.user) : userMoney.user == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (income != null ? income.hashCode() : 0);
        result = 31 * result + (consumption != null ? consumption.hashCode() : 0);
        result = 31 * result + (user != null ? user.hashCode() : 0);
        result = 31 * result + score;
        return result;
    }
}
