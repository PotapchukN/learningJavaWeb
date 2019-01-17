package by.scoring.model.entity;

import javax.persistence.*;
import java.io.Serializable;

 @Entity
 @Table(name="answers")
public class Answers implements Serializable {

    static final long serialVersionUID = 1L;

     @Id
     @GeneratedValue(strategy = GenerationType.AUTO)
     private Long id;

     @Column(name="answer")
     private String answer;

     @Column(name="score")
     private int score;

     @ManyToOne
     @JoinColumn(name="question_id")
     private Questions questions;


//     @OneToMany(mappedBy = "answers")
//     private UserAnswers userAnswers;

//     public UserAnswers getUserAnswers() {
//         return userAnswers;
//     }
//
//     public void setUserAnswers(UserAnswers userAnswers) {
//         this.userAnswers = userAnswers;
//     }

     public Long getId() {return id;}

     public String getAnswer() {return answer;}

     public int getScore() {return score;}

     public Questions getQuestions() {return questions;}

     public void setId(Long id) {this.id = id;}

     public void setAnswer(String answer) {this.answer = answer;}

     public void setScore(int score) {this.score = score; }

     public void setQuestions(Questions questions) {this.questions = questions;}

 }
