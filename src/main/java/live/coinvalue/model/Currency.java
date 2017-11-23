package live.coinvalue.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Currency {

    @Id
    @Column(length = 5)
    private String name;

    @Column
    private String sign;

    @ManyToOne
    @JoinColumn(name = "source_id")
    private Source source;

    @OneToMany(mappedBy = "currency")
    private List<Price> price;

    public Currency(String name, String sign){
        this.name = name;
        this.sign = sign;
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

    public Source getSource() {
        return source;
    }

    public void setSource(Source source) {
        this.source = source;
    }

//    public Price getPrice() {
//        return price;
//    }
//
//    public void setPrice(Price price) {
//        this.price = price;
//    }
}
