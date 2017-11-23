package live.coinvalue.model;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Price {

    @Id
    @Column
    private Date date;

    @ManyToOne
    @JoinColumn(name = "source_site")
    private Source source;

    @ManyToOne
    @JoinColumn(name = "currency_name")
    private Currency currency;

    @Column
    private double price;


    public Price(){

    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Source getSource() {
        return source;
    }

    public void setSource(Source source) {
        this.source = source;
    }

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
