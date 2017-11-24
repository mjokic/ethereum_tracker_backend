package live.coinvalue.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Currency {

    @Id
    @Column(length = 5)
    private String name;

    @Column
    private String sign;

    @ManyToMany
    @JoinColumn(name = "source_id")
    private List<Source> source;

    @OneToMany(mappedBy = "currency", cascade = CascadeType.ALL)
    private List<Price> price;

    public Currency(){}

    public Currency(String name, String sign){
        this.name = name;
        this.sign = sign;
        this.source = new ArrayList<>();
        this.price = new ArrayList<>();
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public List<Source> getSource() {
        return source;
    }

    public void setSource(List<Source> source) {
        this.source = source;
    }

    public List<Price> getPrice() {
        return price;
    }

    public void setPrice(List<Price> price) {
        this.price = price;
    }


    public Currency addSource(Source source){
        this.source.add(source);
        return this;
    }

}
