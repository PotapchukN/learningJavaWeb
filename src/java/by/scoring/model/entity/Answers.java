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

     public Long getId() {return id;}

     public String getAnswer() {return answer;}

     public int getScore() {return score;}

     public Questions getQuestions() {return questions;}

     public void setId(Long id) {this.id = id;}

     public void setAnswer(String answer) {this.answer = answer;}

     public void setScore(int score) {this.score = score; }

     public void setQuestions(Questions questions) {this.questions = questions;}

     @Override
     public boolean equals(Object o) {
         if (this == o) return true;
         if (!(o instanceof Answers)) return false;

         Answers answers = (Answers) o;

         if (score != answers.score) return false;
         if (id != null ? !id.equals(answers.id) : answers.id != null) return false;
         if (answer != null ? !answer.equals(answers.answer) : answers.answer != null) return false;
         return questions != null ? questions.equals(answers.questions) : answers.questions == null;
     }

     @Override
     public int hashCode() {
         int result = id != null ? id.hashCode() : 0;
         result = 31 * result + (answer != null ? answer.hashCode() : 0);
         result = 31 * result + score;
         result = 31 * result + (questions != null ? questions.hashCode() : 0);
         return result;
     }
 }
