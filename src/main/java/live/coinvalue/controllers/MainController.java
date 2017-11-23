package live.coinvalue.controllers;

import live.coinvalue.model.Currency;
import live.coinvalue.model.Source;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping("/api/")
public class MainController {


    @RequestMapping("/")
    public String index(){
        return "Nothing here";
    }


    /**
     * Get all sources from the database
     * Every source contains site name and currency list
     */
    @RequestMapping("/info")
    public List<Source> info(){
//        Currency usd = new Currency("usd", "$");
//        Currency eur = new Currency("eur", "€");
//        Currency gbp = new Currency("gbp", "£");
//        Currency btc = new Currency("btc", "฿");
//
//        Source p1 = new Source("cex", Arrays.asList(usd,eur,gbp,btc));
//        Source p2 = new Source("gemini", Arrays.asList(usd,btc));
//        Source p3 = new Source("poloniex", Arrays.asList(usd,btc));
//
//        return Arrays.asList(p1, p2, p3);
        return null;
    }

}
