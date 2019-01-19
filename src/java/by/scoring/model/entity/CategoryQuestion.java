package by.scoring.model.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="category_question")
public class CategoryQuestion implements Serializable {

    static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name="name")
    private String name;

    public Long getId() {return id;}

    public String getName() {return name;}

    public void setId(Long id) {this.id = id;}

    public void setName(String name) {this.name = name;}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CategoryQuestion)) return false;

        CategoryQuestion that = (CategoryQuestion) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        return name != null ? name.equals(that.name) : that.name == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}
