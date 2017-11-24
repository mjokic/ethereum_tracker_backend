package live.coinvalue;

import live.coinvalue.model.Currency;
import live.coinvalue.model.Source;
import live.coinvalue.repository.CurrencyRepository;
import live.coinvalue.repository.SourceRepository;
import live.coinvalue.sources.cex_io.Cex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;


@SpringBootApplication
@EnableScheduling
public class EthereumTrackerApplication
        extends SpringBootServletInitializer
        implements CommandLineRunner {

    @Autowired
    SourceRepository sourceRepository;

    @Autowired
    CurrencyRepository currencyRepository;

//    // Required when exporting as war
//    @Override
//    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
//        return builder.sources(EthereumTrackerApplication.class);
//    }

    public static void main(String[] args) {
		SpringApplication.run(EthereumTrackerApplication.class, args);
	}


    @Override
    public void run(String... strings) throws Exception {
        // running on start
        insertSources();
    }

    private HashMap<String, Currency> generateAllCurrencies(){
        HashMap<String, Currency> currencies = new HashMap<>();

        currencies.put("usd", new Currency("USD", "$"));
        currencies.put("eur", new Currency("EUR", "€"));
        currencies.put("gbp", new Currency("GBP", "£"));
        currencies.put("btc", new Currency("BTC", "฿"));
        return currencies;
    }

    private void insertSources(){
        HashMap<String, Currency> currencies = generateAllCurrencies();
        List<Source> sources = new ArrayList<>();

        Source cexSource = new Source("cex");
        List<Currency> cexCurrency = new ArrayList<>(Arrays.asList(
                currencies.get("usd").addSource(cexSource),
                currencies.get("eur").addSource(cexSource),
                currencies.get("gbp").addSource(cexSource),
                currencies.get("btc").addSource(cexSource)
        ));
        cexSource.addCurrencies(cexCurrency);
        sources.add(cexSource);


        Source geminiSource = new Source("gemini");
        List<Currency> geminiCurrency = new ArrayList<>(Arrays.asList(
                currencies.get("usd").addSource(geminiSource),
                currencies.get("btc").addSource(geminiSource)
        ));
        geminiSource.addCurrencies(geminiCurrency);
        sources.add(geminiSource);


        Source poloniexSource = new Source("poloniex");
        List<Currency> poloniexCurrency = new ArrayList<>(Arrays.asList(
                currencies.get("usd").addSource(poloniexSource),
                currencies.get("btc").addSource(poloniexSource)
        ));

        poloniexSource.addCurrencies(poloniexCurrency);
        sources.add(poloniexSource);

        sourceRepository.save(sources);
    }

}
