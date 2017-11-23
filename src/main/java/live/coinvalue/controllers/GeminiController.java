package live.coinvalue.controllers;

import live.coinvalue.model.ResponsePojo;
import live.coinvalue.model.gemini.Gemini;
import live.coinvalue.repository.GeminiRepository;
import live.coinvalue.util.Helper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/gemini/")
public class GeminiController {


    @Autowired
    GeminiRepository geminiRepository;


    @RequestMapping("/usd")
    public ResponsePojo geminiUsdValue(){
        Gemini gemini1 = geminiRepository.findOne((long) 1);
        Gemini gemini2 = geminiRepository.findOne((long) 2);

        return Helper.generateResponsePojo(gemini2.getUsdValue(), gemini1.getUsdValue());
    }


    @RequestMapping("/btc")
    public ResponsePojo geminiBtcValue(){
        Gemini gemini1 = geminiRepository.findOne((long) 1);
        Gemini gemini2 = geminiRepository.findOne((long) 2);

        return Helper.generateResponsePojo(gemini2.getBtcValue(), gemini1.getBtcValue());
    }



}
