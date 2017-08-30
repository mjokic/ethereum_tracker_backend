package live.coinvalue.model;

public class ResponsePojo {

    private double currentPrice;
    private double change24hour;

    public ResponsePojo(double currentPrice, double change24hour){
        this.currentPrice = currentPrice;
        this.change24hour = change24hour; // or do change calculation here and as parameter accept previous price
    }


    public double getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(double currentPrice) {
        this.currentPrice = currentPrice;
    }

    public double getChange24hour() {
        return change24hour;
    }

    public void setChange24hour(double change24hour) {
        this.change24hour = change24hour;
    }
}
