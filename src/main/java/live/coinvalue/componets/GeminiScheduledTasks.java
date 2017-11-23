package live.coinvalue.componets;

import live.coinvalue.sources.gemini_com.Gemini;
import live.coinvalue.repository.GeminiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component
public class GeminiScheduledTasks {

    private static Logger logger = Logger.getLogger(GeminiScheduledTasks.class.getName());


    @Autowired
    GeminiRepository geminiRepository;
    @Autowired
    Gemini gemini;


    @Scheduled(fixedDelay = 30000)
    public void getUsdValue(){
        double usd = gemini.updateValue("usd");
        gemini.setUsdValue(usd);
        geminiRepository.save(gemini);
    }


    @Scheduled(fixedDelay = 30000)
    public void getBtcValue(){
        double btc = gemini.updateValue("btc");
        gemini.setBtcValue(btc);
        geminiRepository.save(gemini);
    }


    @Scheduled(fixedDelay = 86400000)
//    @Scheduled(fixedDelay = 600000)
    public void _24hrChange(){
        Gemini gemini = geminiRepository.findOne((long) 1);
        gemini.setId(2);
        geminiRepository.save(gemini);

        logger.info("Saved 24 hour price");
    }

}
