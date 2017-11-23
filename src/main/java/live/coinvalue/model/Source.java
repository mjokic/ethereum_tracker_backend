package live.coinvalue.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Source {

    @Id
    @Column
    private long id;

    @Column
    private String site;

    @OneToMany(mappedBy = "source", cascade = CascadeType.ALL)
    private List<Currency> currencies;

    @OneToMany(mappedBy = "source")
    private List<Price> price;

    public Source(String site, List<Currency> currencies) {
        this.site = site;
        this.currencies = currencies;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public List<Currency> getCurrencies() {
        return currencies;
    }

    public void setCurrencies(List<Currency> currencies) {
        this.currencies = currencies;
    }

//    public Price getPrice() {
//        return price;
//    }
//
//    public void setPrice(Price price) {
//        this.price = price;
//    }
}
