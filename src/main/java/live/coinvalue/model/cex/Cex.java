package live.coinvalue.model.cex;


import com.google.gson.Gson;
import live.coinvalue.model.cex.pojo.CexPojo;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.logging.Logger;

@Component
@Entity(name = "cex")
public class Cex {


    @Id
    private long id = 1;

    @Column(name = "usd")
    private double usdValue;
    @Column(name = "eur")
    private double eurValue;
    @Column(name = "gbp")
    private double gbpValue;
    @Column(name = "btc")
    private double btcValue;


    @Transient
    private OkHttpClient client;
    @Transient
    private Gson gson;


    public Cex(){
        this.client = new OkHttpClient();
        this.gson = new Gson();
    }


    public double updateValue(String value){
        String url;

        switch (value){
            case "usd":
                url = "https://cex.io/api/ticker/ETH/USD";
                break;
            case "eur":
                url = "https://cex.io/api/ticker/ETH/EUR";
                break;
            case "gbp":
                url = "https://cex.io/api/ticker/ETH/GBP";
                break;
            case "btc":
                url = "https://cex.io/api/ticker/ETH/BTC";
                break;
            default:
                return 0;
        }

        Request request = new Request.Builder()
                .url(url)
                .build();

        Logger logger = Logger.getLogger(Cex.class.getName());


        try {
            Response response = this.client.newCall(request).execute();
            CexPojo cexPojo = this.gson.fromJson(response.body().charStream(), CexPojo.class);

            logger.info("Price in " + value + ": " + cexPojo.getLast());

            return cexPojo.getLast();

        }catch (Exception ex){
            logger.warning("Failed to get price("+value+"):\n" +
                    ex.getStackTrace().toString());
            return 0;
        }

    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getUsdValue() {
        return usdValue;
    }

    public void setUsdValue(double usdValue) {
        this.usdValue = usdValue;
    }

    public double getEurValue() {
        return eurValue;
    }

    public void setEurValue(double eurValue) {
        this.eurValue = eurValue;
    }

    public double getGbpValue() {
        return gbpValue;
    }

    public void setGbpValue(double gbpValue) {
        this.gbpValue = gbpValue;
    }

    public double getBtcValue() {
        return btcValue;
    }

    public void setBtcValue(double btcValue) {
        this.btcValue = btcValue;
    }

}
