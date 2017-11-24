package live.coinvalue.componets;

import live.coinvalue.services.CurrencyService;
import live.coinvalue.services.PriceService;
import live.coinvalue.services.SourceService;
import live.coinvalue.sources.cex_io.Cex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTasks {

    private SourceService sourceService;
    private CurrencyService currencyService;
    private PriceService priceService;

    @Autowired
    public ScheduledTasks(SourceService sourceService,
                          CurrencyService currencyService,
                          PriceService priceService){
        this.sourceService = sourceService;
        this.currencyService = currencyService;
        this.priceService = priceService;
    }

    @Scheduled(fixedDelay = 60000)
    public void cex() {
        Cex cex = new Cex(sourceService, currencyService, priceService);
        cex.getPrice();
    }

    @Scheduled(fixedDelay = 60000)
    public void gemini() {

    }

    @Scheduled(fixedDelay = 60000)
    public void poloniex() {

    }

}
