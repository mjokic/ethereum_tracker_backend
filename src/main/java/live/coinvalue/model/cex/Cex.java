package live.coinvalue.model.cex;


import com.google.gson.Gson;
import live.coinvalue.model.cex.pojo.CexPojo;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import javax.persistence.*;

@Entity(name = "cex")
public class Cex {

    @Id
    private long id = 1;

    @Column
    private double usdValue;
    @Column
    private double eurValue;
    @Column
    private double gbpValue;
    @Column
    private double btcValue;


    @Transient
    private OkHttpClient client;
    @Transient
    private Gson gson;


    public Cex(){
        this.client = new OkHttpClient();
        this.gson = new Gson();
    }

    public void updateUsdValue(){
        Request request = new Request.Builder()
                .url("https://cex.io/api/ticker/ETH/USD")
                .build();

        try {
            Response response = this.client.newCall(request).execute();
            CexPojo cexPojo = this.gson.fromJson(response.body().charStream(), CexPojo.class);

            System.out.println(response.code() + " <-- CODE!");
            System.out.print(cexPojo.getLast() + " <-- LAST PRICEE! USD");

            this.setUsdValue(Double.parseDouble(cexPojo.getLast()));

        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void updateEurValue(){
        Request request = new Request.Builder()
                .url("https://cex.io/api/ticker/ETH/USD")
                .build();

        try {
            Response response = this.client.newCall(request).execute();
            CexPojo cexPojo = this.gson.fromJson(response.body().charStream(), CexPojo.class);

            System.out.println(response.code() + " <-- CODE!");
            System.out.println(cexPojo.getLast() + " <-- Prajs!");

        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void updateGbpValue(){
        Request request = new Request.Builder()
                .url("https://cex.io/api/ticker/ETH/GBP")
                .build();

        try {
            Response response = this.client.newCall(request).execute();
            CexPojo cexPojo = this.gson.fromJson(response.body().charStream(), CexPojo.class);

            System.out.println(response.code() + " <-- CODE!");
            System.out.print(cexPojo.getLast() + " <-- LAST PRICEE! GBP");

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
