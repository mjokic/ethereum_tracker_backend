package live.coinvalue.controllers;

import live.coinvalue.model.ResponsePojo;
import live.coinvalue.model.poloniex.Poloniex;
import live.coinvalue.repository.PoloniexRepository;
import live.coinvalue.util.Helper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/poloniex/")
public class PoloniexController {

    @Autowired
    PoloniexRepository poloniexRepository;


    @RequestMapping("/usd")
    public ResponsePojo poloniexUsdValue(){
        Poloniex poloniex1 = poloniexRepository.findOne((long) 1);
        Poloniex poloniex2 = poloniexRepository.findOne((long) 2);

        return Helper.generateResponsePojo(
                poloniex1.getUsdValue(), poloniex2.getUsdValue());
    }

    @RequestMapping("/btc")
    public ResponsePojo poloniexBtcValue(){
        Poloniex poloniex1 = poloniexRepository.findOne((long) 1);
        Poloniex poloniex2 = poloniexRepository.findOne((long) 2);

        return Helper.generateResponsePojo(
                poloniex1.getBtcValue(), poloniex2.getBtcValue());
    }

}
