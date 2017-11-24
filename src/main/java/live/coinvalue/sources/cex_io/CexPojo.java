package live.coinvalue.sources.cex_io;


public class CexPojo {

    private double low;
    private double high;
    private double last;

    public double getLow() {
        return low;
    }

    public void setLow(double low) {
        this.low = low;
    }

    public double getHigh() {
        return high;
    }

    public void setHigh(double high) {
        this.high = high;
    }

    public double getLast() {
        return last;
    }

    public void setLast(double last) {
        this.last = last;
    }

    @Override
    public String toString() {
        return "CexPojo{" +
                "low=" + low +
                ", high=" + high +
                ", last=" + last +
                '}';
    }
}
