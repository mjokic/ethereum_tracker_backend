package live.coinvalue.componets;

import live.coinvalue.sources.cex_io.CexSource;
import live.coinvalue.repository.CexRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component
public class CexScheduledTasks {

    private static Logger logger = Logger.getLogger(CexScheduledTasks.class.getName());


    @Autowired
    CexRepository cexRepository;
    @Autowired
    CexSource cex;


    @Scheduled(fixedDelay = 30000)
    public void getUsdValue(){
        double usd = cex.updateValue("usd");
        cex.setUsdValue(usd);
        cexRepository.save(cex);
    }


    @Scheduled(fixedDelay = 30000)
    public void getEurValue(){
        double eur = cex.updateValue("eur");
        cex.setEurValue(eur);
        cexRepository.save(cex);
    }



    @Scheduled(fixedDelay = 30000)
    public void getGbpValue(){
        double gbp = cex.updateValue("gbp");
        cex.setGbpValue(gbp);
        cexRepository.save(cex);
    }



    @Scheduled(fixedDelay = 30000)
    public void getBtcValue(){
        double btc = cex.updateValue("btc");
        cex.setBtcValue(btc);
        cexRepository.save(cex);
    }


    @Scheduled(fixedDelay = 86400000)
//    @Scheduled(fixedDelay = 600000)
    public void _24hrChange(){
        CexSource cex = cexRepository.findOne((long) 1);
        cex.setId(2);
        cexRepository.save(cex);

        logger.info("Saved 24 hour price");
    }

}
