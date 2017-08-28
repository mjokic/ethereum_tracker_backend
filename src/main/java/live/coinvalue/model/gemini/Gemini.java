package live.coinvalue.model.gemini;

import com.google.gson.Gson;
import live.coinvalue.model.gemini.pojo.GeminiPojo;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity
public class Gemini {

    @Id
    private long id = 1;
    @Column
    private double usdValue;
    @Column
    private double btcValue;


    @Transient
    private OkHttpClient client;
    @Transient
    private Gson gson;


    public Gemini(){
        this.client = new OkHttpClient();
        this.gson = new Gson();
    }

    public void updateUsdValue(){
        Request request = new Request.Builder()
                .url("https://api.gemini.com/v1/pubticker/ethusd")
                .build();

        try {
            Response response = this.client.newCall(request).execute();
            GeminiPojo geminiPojo = this.gson.fromJson(response.body().charStream(), GeminiPojo.class);

            System.out.println(response.code() + " <-- CODE!");
            System.out.print(geminiPojo.getLast() + " <-- LAST PRICEE! USD");

            this.setUsdValue(Double.parseDouble(geminiPojo.getLast()));

        }catch (Exception ex){
            ex.printStackTrace();
        }
    }


    public void updateBtcValue(){
        Request request = new Request.Builder()
                .url("https://api.gemini.com/v1/pubticker/ethbtc")
                .build();

        try {
            Response response = this.client.newCall(request).execute();
            GeminiPojo geminiPojo = this.gson.fromJson(response.body().charStream(), GeminiPojo.class);

            System.out.println(response.code() + " <-- CODE!");
            System.out.print(geminiPojo.getLast() + " <-- LAST PRICEE! USD");

        }catch (Exception ex){
            ex.printStackTrace();
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
