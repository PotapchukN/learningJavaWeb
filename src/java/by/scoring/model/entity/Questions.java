package by.scoring.model.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="questions")
public class Questions implements Serializable {

    static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name="question")
    private String question;

    @ManyToOne
    @JoinColumn(name="category_id")
    private CategoryQuestion categoryQuestion;

    public Long getId() {return id;}

    public void setId(Long id) {this.id = id;}

    public String getQuestion() {return question;}

    public void setQuestion(String question) {this.question = question;}

    public CategoryQuestion getCategoryQuestion() {return categoryQuestion;}

    public void setCategoryQuestion(CategoryQuestion categoryQuestion) {this.categoryQuestion = categoryQuestion;}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Questions)) return false;

        Questions questions = (Questions) o;

        if (id != null ? !id.equals(questions.id) : questions.id != null) return false;
        if (question != null ? !question.equals(questions.question) : questions.question != null) return false;
        return categoryQuestion != null ? categoryQuestion.equals(questions.categoryQuestion) : questions.categoryQuestion == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (question != null ? question.hashCode() : 0);
        result = 31 * result + (categoryQuestion != null ? categoryQuestion.hashCode() : 0);
        return result;
    }
}
