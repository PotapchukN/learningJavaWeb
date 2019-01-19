package by.scoring.model.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "credit_info")
public class CreditInfo implements Serializable {

    static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name="type")
    private String type;

    @Column(name="percent")
    private Float percent;

    @Column(name="max_period")
    private int max_period;

    @Column(name="max_summ")
    private int max_summ;

    public Long getId() {return id;}

    public void setId(Long id) {this.id = id;}

    public String getType() {return type;}

    public void setType(String type) {this.type = type;}

    public Float getPercent() {return percent;}

    public void setPercent(Float percent) {this.percent = percent;}

    public int getMax_period() {return max_period;}

    public void setMax_period(int max_period) {this.max_period = max_period;}

    public int getMax_summ() {return max_summ;}

    public void setMax_summ(int max_summ) {this.max_summ = max_summ;}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CreditInfo)) return false;

        CreditInfo that = (CreditInfo) o;

        if (max_period != that.max_period) return false;
        if (max_summ != that.max_summ) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (type != null ? !type.equals(that.type) : that.type != null) return false;
        return percent != null ? percent.equals(that.percent) : that.percent == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (percent != null ? percent.hashCode() : 0);
        result = 31 * result + max_period;
        result = 31 * result + max_summ;
        return result;
    }
}
