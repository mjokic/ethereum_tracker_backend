package live.coinvalue.controllers;

import live.coinvalue.model.Currency;
import live.coinvalue.model.Price;
import live.coinvalue.model.Source;
import live.coinvalue.services.PriceService;
import live.coinvalue.services.SourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/graph")
public class GraphController {

    private SourceService sourceService;
    private PriceService priceService;

    @Autowired
    public GraphController(SourceService sourceService, PriceService priceService) {
        this.sourceService = sourceService;
        this.priceService = priceService;
    }


    @RequestMapping(method = RequestMethod.GET,
            path = "/{sourceSite}/{currencyName}/{days}")
    public ResponseEntity<?> getAllPrices(@PathVariable String sourceSite,
                                          @PathVariable String currencyName,
                                          @PathVariable int days) {

        List<Price> prices;

        switch (days) {
            case 1: // hour
                Source s1 = sourceService.getSourceBySite(sourceSite);
                Currency c1 = getCurrency(s1, currencyName);
                if (c1 == null) return
                        new ResponseEntity<>("Error", HttpStatus.BAD_REQUEST);
                prices = priceService.getPricesFromLastHour(s1, c1);
                break;
            case 24: // hours
                Source s2 = sourceService.getSourceBySite(sourceSite);
                Currency c2 = getCurrency(s2, currencyName);
                if (c2 == null) return
                        new ResponseEntity<>("Error", HttpStatus.BAD_REQUEST);
                prices = priceService.getPricesFromLastDay(s2, c2);
                break;
            case 7: // days
                Source s3 = sourceService.getSourceBySite(sourceSite);
                Currency c3 = getCurrency(s3, currencyName);
                if (c3 == null) return
                        new ResponseEntity<>("Error", HttpStatus.BAD_REQUEST);
                prices = priceService.getPricesFromWeekAgo(s3, c3);
                break;
            case 30: // days
                Source s4 = sourceService.getSourceBySite(sourceSite);
                Currency c4 = getCurrency(s4, currencyName);
                if (c4 == null) return
                        new ResponseEntity<>("Error", HttpStatus.BAD_REQUEST);
                prices = priceService.getPricesFromMonthAgo(s4, c4);
                break;
            default:
                return new ResponseEntity<>("Error", HttpStatus.BAD_REQUEST);
        }


        return new ResponseEntity<>(prices, HttpStatus.OK);
    }


    private Currency getCurrency(Source source, String currencyName) {
        Currency currency = null;

        for (Currency c : source.getCurrencies()) {
            if (c.getName().toLowerCase().equals(currencyName.toLowerCase())) {
                currency = c;
                break;
            }
        }

        return currency;
    }

}
