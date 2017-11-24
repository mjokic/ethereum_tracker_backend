package live.coinvalue.services;

import live.coinvalue.model.Currency;
import live.coinvalue.model.Price;
import live.coinvalue.model.Source;
import live.coinvalue.repository.PriceRepository;
import org.springframework.stereotype.Service;

@Service
public class PriceService {

    private PriceRepository priceRepository;

    public PriceService(PriceRepository priceRepository){
        this.priceRepository = priceRepository;
    }


    public void savePrice(Price price){
        priceRepository.save(price);
    }


    public Price getLatestPrice(Currency currency, Source source){
        return priceRepository.findFirstByCurrencyAndSourceOrderByIdDesc(currency, source);
    }

}
