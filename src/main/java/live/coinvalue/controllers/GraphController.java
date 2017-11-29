package live.coinvalue.controllers;

import com.sun.org.apache.xerces.internal.xs.StringList;
import live.coinvalue.dto.PriceDTO;
import live.coinvalue.model.Currency;
import live.coinvalue.model.Price;
import live.coinvalue.model.Source;
import live.coinvalue.services.PriceService;
import live.coinvalue.services.SourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/graph")
public class GraphController {

    private SourceService sourceService;
    private PriceService priceService;

    @Autowired
    public GraphController(SourceService sourceService, PriceService priceService){
        this.sourceService = sourceService;
        this.priceService = priceService;
    }


    @RequestMapping(method = RequestMethod.GET, path = "/{sourceSite}/{currencyName}")
    public ResponseEntity<?> getAllPrices(@PathVariable String sourceSite,
                                    @PathVariable String currencyName){

        Source source = sourceService.getSourceBySite(sourceSite);

        Currency currency = null;

        for (Currency c : source.getCurrencies()){
            if (c.getName().toLowerCase().equals(currencyName.toLowerCase())){
                currency = c;
                break;
            }
        }

        if (currency == null) return new ResponseEntity<String>("Error", HttpStatus.BAD_REQUEST);

        List<Price> prices = priceService.getAllPriceBySourceAndCurrency(source, currency);

        return new ResponseEntity<List<Price>>(prices, HttpStatus.OK);
    }

}
