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
}
