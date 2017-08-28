package live.coinvalue.controllers;

import live.coinvalue.model.cex.Cex;
import live.coinvalue.model.cex.pojo.CexPojo;
import live.coinvalue.repository.CexRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {


    @Autowired
    CexRepository cexRepository;


    @RequestMapping("/")
    public String index(){
        return "Nothing here";
    }


    @RequestMapping("/cex/usd")
    public double cexUsdValue(){
        Cex cex = cexRepository.findOne((long) 1);
        return cex.getUsdValue();
    }


    @RequestMapping("/cex/eur")
    public double cexEurValue(){
        Cex cex = cexRepository.findOne((long) 1);
        return cex.getEurValue();
    }

}
