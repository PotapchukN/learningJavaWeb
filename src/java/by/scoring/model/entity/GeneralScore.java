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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GeneralScore)) return false;

        GeneralScore that = (GeneralScore) o;

        if (maxScore != that.maxScore) return false;
        if (minScore != that.minScore) return false;
        return id != null ? id.equals(that.id) : that.id == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + maxScore;
        result = 31 * result + minScore;
        return result;
    }
}
