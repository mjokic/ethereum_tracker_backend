package live.coinvalue.controllers;

import live.coinvalue.model.Profile;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Array;
import java.util.*;

@RestController
public class MainController {


    @RequestMapping("/")
    public String index(){
        return "Nothing here";
    }


    @RequestMapping("/info")
    public List<Profile> info(){

        Profile p1 = new Profile("cex", Arrays.asList("usd", "eur", "gbp", "btc"));
        Profile p2 = new Profile("gemini", Arrays.asList("usd", "btc"));
        Profile p3 = new Profile("poloniex", Arrays.asList("usd", "btc"));

        return Arrays.asList(p1, p2, p3);
    }

}
