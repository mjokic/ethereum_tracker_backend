package live.coinvalue.componets;

import live.coinvalue.sources.poloniex_com.Poloniex;
import live.coinvalue.repository.PoloniexRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component
public class PoloniexScheduledTasks {

    private static Logger logger = Logger.getLogger(PoloniexScheduledTasks.class.getName());

    @Autowired
    Poloniex poloniex;

    @Autowired
    PoloniexRepository poloniexRepository;



    @Scheduled(fixedDelay = 30000)
    public void getUsdValue(){
        double price = poloniex.updateValue("usd");
        poloniex.setUsdValue(price);
        poloniexRepository.save(poloniex);
    }


    @Scheduled(fixedDelay = 30000)
    public void getBtcValue(){
        double price = poloniex.updateValue("btc");
        poloniex.setBtcValue(price);
        poloniexRepository.save(poloniex);
    }



    @Scheduled(fixedDelay = 86400000)
    public void _24hrChange(){
        Poloniex poloniex = poloniexRepository.findOne((long) 1);
        poloniex.setId(2);
        poloniexRepository.save(poloniex);

        logger.info("Saved 24 hour price");
    }
}
