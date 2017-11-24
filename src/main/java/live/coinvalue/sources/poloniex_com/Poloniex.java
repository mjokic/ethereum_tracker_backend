package live.coinvalue.sources.poloniex_com;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import live.coinvalue.model.Currency;
import live.coinvalue.model.Price;
import live.coinvalue.model.Source;
import live.coinvalue.services.PriceService;
import live.coinvalue.services.SourceService;
import okhttp3.*;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

public class Poloniex {

    private final OkHttpClient client;
    private final Logger logger;
    private final Gson gson;
    private SourceService sourceService;
    private PriceService priceService;
    private String baseUrl = "https://poloniex.com/public?command=returnTicker";


    public Poloniex(SourceService sourceService, PriceService priceService) {
        client = new OkHttpClient();
        logger = Logger.getLogger(getClass().getName());
        gson = new Gson();

        this.sourceService = sourceService;
        this.priceService = priceService;
    }

    public void getPrice() {
        Source source = sourceService.getSourceBySite("poloniex");
        List<Currency> currencies = source.getCurrencies();

        for (Currency currency : currencies) {

            String objectName;
            switch (currency.getName()) {
                case "BTC":
                    objectName = "BTC_ETH";
                    break;
                default:
                    objectName = "USDT_ETH";
            }

            Request request = new Request.Builder()
                    .url(baseUrl)
                    .build();

            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    logger.info(e.getMessage());
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    if (response.code() != 200) return;

                    JsonParser parser = new JsonParser();
                    JsonElement element = parser.parse(response.body().string());
                    JsonObject object = element.getAsJsonObject();

                    JsonElement znj = object.get(objectName);

                    PoloniexPojo poloniexPojo = gson.fromJson(znj, PoloniexPojo.class);
                    System.out.println(poloniexPojo);

                    save(poloniexPojo.getLast(), source, currency);
                }
            });

        }
    }


    private void save(double p, Source source, Currency currency) {
        Price price = new Price(new Date(), p, source, currency);
        priceService.savePrice(price);
    }

}
