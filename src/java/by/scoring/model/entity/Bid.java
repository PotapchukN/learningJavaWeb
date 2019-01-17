package by.scoring.model.entity;

import sun.util.calendar.LocalGregorianCalendar;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Time;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="bids")
public class Bid implements Serializable{

    static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name="credit_id")
    private CreditInfo credit;

    @Column(name="maxTerm")
    private Float maxTerm;

    @Column(name="maxSum")
    private Float maxSum;

    @Column(name="guarantor")
    private String guarantor;

    @Temporal(TemporalType.DATE)
    @Column(name="date")
    private Date date;

//    @Temporal(TemporalType.TIME)
    @Column(name="time")
    private String time;

    public Long getId() { return id; }

    public void setId(Long id) {this.id = id;}

    public User getUser() {return user;}

    public void setUser(User user) {this.user = user;}

    public CreditInfo getCredit() {return credit;}

    public void setCredit(CreditInfo credit) {this.credit = credit;}

    public Float getMaxTerm() {return maxTerm;}

    public void setMaxTerm(Float maxTerm) {this.maxTerm = maxTerm;}

    public Float getMaxSum() {return maxSum;}

    public void setMaxSum(Float maxSum) {this.maxSum = maxSum;}

    public String getGuarantor() {return guarantor;}

    public void setGuarantor(String guarantor) {this.guarantor = guarantor;}

    public Date getDate() {return date;}

    public void setDate(Date date) {this.date = date;}

    public String getTime() {return time;}

    public void setTime(String time) {


        this.time = time;}
}
