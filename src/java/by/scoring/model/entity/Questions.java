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
}
