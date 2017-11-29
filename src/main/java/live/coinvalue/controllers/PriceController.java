package live.coinvalue.controllers;

import live.coinvalue.dto.PriceDTO;
import live.coinvalue.model.Currency;
import live.coinvalue.model.Price;
import live.coinvalue.model.Source;
import live.coinvalue.services.PriceService;
import live.coinvalue.services.SourceService;
import org.apache.commons.math3.util.Precision;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class PriceController {

    private SourceService sourceService;
    private PriceService priceService;

    @Autowired
    public PriceController(SourceService sourceService, PriceService priceService){
        this.sourceService = sourceService;
        this.priceService = priceService;
    }

    /**
     * Getting ETH price from selected source for selected currency
     * @param sourceSite Selected source
     * @param currencyName Selected currency
     * @return Latest price
     */
    @RequestMapping(method = RequestMethod.GET, path = "/{sourceSite}/{currencyName}")
    public ResponseEntity<?> getPrice(@PathVariable String sourceSite,
                                   @PathVariable String currencyName){

        Source source = sourceService.getSourceBySite(sourceSite);

        Currency currency = null;

        for (Currency c : source.getCurrencies()){
            if (c.getName().toLowerCase().equals(currencyName.toLowerCase())){
                currency = c;
                break;
            }
        }

        if (currency == null) return new ResponseEntity<>("Error", HttpStatus.BAD_REQUEST);

        Price price = priceService.getLatestPrice(source, currency);
        Price yesterdayPrice = priceService.getYesterdayPrice(currency, source, price);
        PriceDTO priceDTO = new PriceDTO(price,
                currency,
                calculateChangePercent(yesterdayPrice.getPrice(), price.getPrice()));

        return new ResponseEntity<PriceDTO>(priceDTO, HttpStatus.OK);
    }


    private double calculateChangePercent(double oldPrice, double currentPrice){
        return Precision.round(((oldPrice * 100 / currentPrice) - 100) * (-1),2);
    }

}
