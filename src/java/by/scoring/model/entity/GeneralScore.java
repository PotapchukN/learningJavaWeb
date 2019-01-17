package by.scoring.model.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="general_score")
public class GeneralScore implements Serializable {

    static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name="maxScore")
    private int maxScore;

    @Column(name="minScore")
    private int minScore;

    public int getMax_score() {return maxScore;}

    public void setMax_score(int max_score) {this.maxScore = max_score;}

    public int getMin_score() {return minScore;}

    public void setMin_score(int min_score) {this.minScore = min_score;}
}
