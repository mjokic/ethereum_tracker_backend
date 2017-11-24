package live.coinvalue.sources.cex_io;

import com.google.gson.Gson;
import live.coinvalue.model.Currency;
import live.coinvalue.model.Price;
import live.coinvalue.model.Source;
import live.coinvalue.repository.PriceRepository;
import live.coinvalue.repository.SourceRepository;
import live.coinvalue.services.CurrencyService;
import live.coinvalue.services.PriceService;
import live.coinvalue.services.SourceService;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

public class Cex {

    private SourceService sourceService;
    private CurrencyService currencyService;
    private PriceService priceService;

    private String baseUrl = "https://cex.io/api/ticker/ETH/";
    private final OkHttpClient client;
    private final Logger logger;
    private final Gson gson;

    public Cex(SourceService sourceService,
               CurrencyService currencyService,
               PriceService priceService){
        client = new OkHttpClient();
        logger = Logger.getLogger(getClass().getName());
        gson = new Gson();

        this.sourceService = sourceService;
        this.currencyService = currencyService;
        this.priceService = priceService;
    }

    public void getPrice() {
        Source source = sourceService.getSourceBySite("cex");
        List<Currency> currencies = source.getCurrencies();
//        List<Currency> currencies = currencyService.getCUrrenciesBySource(source);
//        String[] currencies = new String[] {"USD", "EUR", "GBP", "BTC"};

        for (Currency currency : currencies){
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
                        CexPojo cexPojo = gson.fromJson(body.charStream(), CexPojo.class);
                        System.out.println(cexPojo);

                        save(cexPojo.getLast(), source, currency);
                    }
                }
            });

        }

    }


    private void save(double p, Source source, Currency currency){
        Price price = new Price(new Date(), p, source, currency);
        priceService.savePrice(price);
    }
}
