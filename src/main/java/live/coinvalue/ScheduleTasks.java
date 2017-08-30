package live.coinvalue;

import com.google.gson.Gson;
import live.coinvalue.model.gemini.Gemini;
import live.coinvalue.model.cex.pojo.CexPojo;
import live.coinvalue.model.cex.Cex;
import live.coinvalue.repository.CexRepository;
import live.coinvalue.repository.GeminiRepository;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class SceduleTasks {

    @Autowired
    CexRepository cexRepository;
    @Autowired
    GeminiRepository geminiRepository;


    @Scheduled(fixedRate = 30000)
    public void test3(){
        Cex cex = new Cex();
        cex.updateUsdValue();
        cex.updateEurValue();
        cex.updateGbpValue();

        cexRepository.save(cex);
    }


    @Scheduled(fixedRate = 30000)
    public void test4(){
        Gemini gemini = new Gemini();
        gemini.updateUsdValue();
        gemini.updateBtcValue();
        geminiRepository.save(gemini);
    }


}
