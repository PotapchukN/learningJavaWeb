package by.scoring.model.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="user_answers")
public class UserAnswers implements Serializable {

    static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name="question_id")
    private int question_id;

    @ManyToOne
    @JoinColumn(name="answer_id")
    private Answers answers;

    @Column(name="score_for_user")
    private int score_for_user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    public String getRisk() {return risk;}

    public void setRisk(String risk) {this.risk = risk;}

    @Column(name="risk")
    private String risk;

    public Long getId() {return id;}

    public void setId(Long id) {this.id = id;}

    public int getQuestion_id() {return question_id;}

    public void setQuestion_id(int question_id) {this.question_id = question_id;}

    public Answers getAnswers() {return answers;}

    public void setAnswers(Answers answers) {this.answers = answers;}

    public int getScore_for_user() {return score_for_user;}

    public void setScore_for_user(int score_for_user) {this.score_for_user = score_for_user;}
}
