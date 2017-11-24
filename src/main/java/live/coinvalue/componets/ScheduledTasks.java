package live.coinvalue.componets;

import live.coinvalue.services.PriceService;
import live.coinvalue.services.SourceService;
import live.coinvalue.sources.cex_io.Cex;
import live.coinvalue.sources.gemini_com.Gemini;
import live.coinvalue.sources.poloniex_com.Poloniex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTasks {

    private SourceService sourceService;
    private PriceService priceService;

    @Autowired
    public ScheduledTasks(SourceService sourceService,
                          PriceService priceService) {
        this.sourceService = sourceService;
        this.priceService = priceService;
    }

    @Scheduled(fixedDelay = 60000)
    public void cex() {
        Cex cex = new Cex(sourceService, priceService);
        cex.getPrice();
    }

    @Scheduled(fixedDelay = 60000)
    public void gemini() {
        Gemini gemini = new Gemini(sourceService, priceService);
        gemini.getPrice();
    }

    @Scheduled(fixedDelay = 60000)
    public void poloniex() {
        Poloniex poloniex = new Poloniex(sourceService, priceService);
        poloniex.getPrice();
    }

}
