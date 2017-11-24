package live.coinvalue.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Source {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String site;

    @ManyToMany(mappedBy = "source", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Currency> currencies;

    @OneToMany(mappedBy = "source", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Price> price;

    public Source(){}

    public Source(String site) {
        this.site = site;
        this.currencies = new ArrayList<>();
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

    public List<Price> getPrice() {
        return price;
    }

    public void setPrice(List<Price> price) {
        this.price = price;
    }

    public void addCurrencies(List<Currency> c){
        this.currencies.addAll(c);
    }
}
