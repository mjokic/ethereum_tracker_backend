package live.coinvalue.controllers;

import live.coinvalue.model.Currency;
import live.coinvalue.model.Price;
import live.coinvalue.model.Source;
import live.coinvalue.services.PriceService;
import live.coinvalue.services.SourceService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/")
public class PriceController {

    private SourceService sourceService;
    private PriceService priceService;

    public PriceController(SourceService sourceService, PriceService priceService){
        this.sourceService = sourceService;
        this.priceService = priceService;
    }

    /**
     * Getting ETH price from selected source for selected currency
     * @param sourceSite Selected source
     * @param currencyName Selected currency
     * @return ...
     */
    @RequestMapping(method = RequestMethod.GET, path = "/{sourceSite}/{currencyName}")
    public Price test(@PathVariable String sourceSite,
                      @PathVariable String currencyName){

        Source s = sourceService.getSourceBySite(sourceSite);
        Currency c = null;

        for (Currency currency : s.getCurrencies()){
            if (currency.getName().toLowerCase().equals(currencyName.toLowerCase())){
                c = currency;
                break;
            }
        }

        if (c == null) return null;

        return priceService.getLatestPrice(c, s);
    }

}
