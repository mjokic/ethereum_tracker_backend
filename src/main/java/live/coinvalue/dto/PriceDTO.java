package live.coinvalue.dto;

import live.coinvalue.model.Currency;
import live.coinvalue.model.Price;

import java.text.DecimalFormat;
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
        if (currency.getName().equals("BTC")){
            this.price = price.getPrice();
        }else {
            DecimalFormat df = new DecimalFormat(".##");
            this.price = Double.parseDouble(df.format(price.getPrice()));
        }
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
