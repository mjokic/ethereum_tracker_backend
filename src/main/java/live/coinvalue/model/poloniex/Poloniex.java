package live.coinvalue.model.poloniex;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import live.coinvalue.model.poloniex.pojo.PoloniexPojo;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.stereotype.Component;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.util.logging.Logger;

@Component
@Entity(name = "poloniex")
public class Poloniex {

    @Id
    private long id = 1;
    @Column(name = "usd")
    private double usdValue;
    @Column(name = "btc")
    private double btcValue;

    @Transient
    private OkHttpClient client;
    @Transient
    private Gson gson;


    public Poloniex(){
        this.client = new OkHttpClient();
        this.gson = new Gson();
    }


    public double updateValue(String value){
        String url = "https://poloniex.com/public?command=returnTicker";

        String objectName;

        switch (value){
            case "btc":
                objectName = "BTC_ETH";
                break;
            default:
                objectName = "USDT_ETH";
        }

        Request request = new Request.Builder()
                .url(url)
                .build();


        Logger logger = Logger.getLogger(Poloniex.class.getName());

        try {
            Response response = this.client.newCall(request).execute();
            String responseString = response.body().string();

            JsonParser parser = new JsonParser();
            JsonElement element = parser.parse(responseString);
            JsonObject object = element.getAsJsonObject();

            JsonElement znj = object.get(objectName);
            PoloniexPojo poloniexPojo = new Gson().fromJson(znj, PoloniexPojo.class);

//            System.out.println(poloniexPojo.getPercentChange() + "<-- PERCENT");

            return poloniexPojo.getLast();

        }catch (Exception ex){
            logger.warning("Failed to get price("+value+"):\n");
            ex.printStackTrace();
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

    public double getBtcValue() {
        return btcValue;
    }

    public void setBtcValue(double btcValue) {
        this.btcValue = btcValue;
    }
}
