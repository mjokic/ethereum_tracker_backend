package live.coinvalue.model.gemini;

import com.google.gson.Gson;
import live.coinvalue.model.gemini.pojo.GeminiPojo;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.stereotype.Component;
import sun.rmi.runtime.Log;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.util.logging.Logger;

@Component
@Entity(name = "gemini")
public class Gemini {

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


    public Gemini(){
        this.client = new OkHttpClient();
        this.gson = new Gson();
    }


    public double updateValue(String value){
        String url;

        switch (value){
            case "usd":
                url = "https://api.gemini.com/v1/pubticker/ethusd";
                break;
            case "btc":
                url = "https://api.gemini.com/v1/pubticker/ethbtc";
                break;
            default:
                return 0;
        }

        Request request = new Request.Builder()
                .url(url)
                .build();


        Logger logger = Logger.getLogger(Gemini.class.getName());

        try {
            Response response = this.client.newCall(request).execute();
            GeminiPojo geminiPojo = this.gson.fromJson(response.body().charStream(), GeminiPojo.class);

            logger.info("Price in " + value + ": " + geminiPojo.getLast());

            return geminiPojo.getLast();

        }catch (Exception ex){
            logger.warning("Failed to get price("+value+"):\n" +
                    ex.getStackTrace());
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
