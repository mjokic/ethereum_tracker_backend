package live.coinvalue.sources.gemini_com;

import com.google.gson.Gson;
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

public class Gemini {

    private final OkHttpClient client;
    private final Logger logger;
    private final Gson gson;
    private SourceService sourceService;
    private PriceService priceService;
    private String baseUrl = "https://api.gemini.com/v1/pubticker/eth";


    public Gemini(SourceService sourceService,
                  PriceService priceService) {
        client = new OkHttpClient();
        logger = Logger.getLogger(getClass().getName());
        gson = new Gson();

        this.sourceService = sourceService;
        this.priceService = priceService;
    }


    public void getPrice() {
        Source source = sourceService.getSourceBySite("gemini");
        List<Currency> currencies = source.getCurrencies();

        for (Currency currency : currencies) {
            String url = baseUrl + currency.getName();

            Request request = new Request.Builder()
                    .url(url)
                    .build();

            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    logger.info(e.getMessage());
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    if (response.code() != 200) return;

                    ResponseBody body = response.body();
                    if (body != null) {
                        GeminiPojo geminiPojo = gson.fromJson(body.charStream(), GeminiPojo.class);
                        System.out.println(geminiPojo);

                        save(geminiPojo.getLast(), source, currency);
                    }
                }
            });

        }
    }

    private void save(double p, Source source, Currency currency) {
        Price price = new Price(new Date(), p, source, currency);
        priceService.savePrice(price);
    }

}
