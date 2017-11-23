package live.coinvalue.controllers;

import live.coinvalue.model.ResponsePojo;
import live.coinvalue.model.cex.Cex;
import live.coinvalue.repository.CexRepository;
import live.coinvalue.util.Helper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/cex/")
public class CexController {

    @Autowired
    CexRepository cexRepository;


    @RequestMapping("/usd")
    public ResponsePojo cexUsdValue(){
        Cex cex1 = cexRepository.findOne((long) 1);
        Cex cex2 = cexRepository.findOne((long) 2);

        return Helper.generateResponsePojo(cex2.getUsdValue(), cex1.getUsdValue());
    }


    @RequestMapping("/eur")
    public ResponsePojo cexEurValue(){
        Cex cex1 = cexRepository.findOne((long) 1);
        Cex cex2 = cexRepository.findOne((long) 2);

        return Helper.generateResponsePojo(cex2.getEurValue(), cex1.getEurValue());
    }


    @RequestMapping("/gbp")
    public ResponsePojo cexGbpValue(){
        Cex cex1 = cexRepository.findOne((long) 1);
        Cex cex2 = cexRepository.findOne((long) 2);

        return Helper.generateResponsePojo(cex2.getGbpValue(), cex1.getGbpValue());
    }


    @RequestMapping("/btc")
    public ResponsePojo cexBtcValue(){
        Cex cex1 = cexRepository.findOne((long) 1);
        Cex cex2 = cexRepository.findOne((long) 2);

        return Helper.generateResponsePojo(cex2.getBtcValue(), cex1.getBtcValue());
    }


}
