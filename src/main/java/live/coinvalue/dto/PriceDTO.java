package live.coinvalue.dto;

import live.coinvalue.model.Currency;
import live.coinvalue.model.Price;

import java.util.Date;

public class PriceDTO {

    private Date date;
    private double price;
    private Currency currency;
    private double change24hr;

    public PriceDTO(){

    }

    public PriceDTO(Price price, Currency currency, double change24hr) {
        this.date = price.getDate();
        this.price = price.getPrice();
        this.currency = currency;
        this.change24hr = change24hr;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public double getChange24hr() {
        return change24hr;
    }

    public void setChange24hr(double change24hr) {
        this.change24hr = change24hr;
    }
}
