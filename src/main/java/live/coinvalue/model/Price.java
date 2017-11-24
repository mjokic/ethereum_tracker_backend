package live.coinvalue.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Price {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private Date date;

    @Column
    private double price;

    @ManyToOne
    @JoinColumn(name = "source")
    private Source source;

    @ManyToOne
    @JoinColumn(name = "currency")
    private Currency currency;

    public Price() {
    }

    public Price(Date date, double price, Source source, Currency currency) {
        this.date = date;
        this.price = price;
        this.source = source;
        this.currency = currency;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @JsonIgnore
    public Source getSource() {
        return source;
    }

    public void setSource(Source source) {
        this.source = source;
    }

    @JsonIgnore
    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
