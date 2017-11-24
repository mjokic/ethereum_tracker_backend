package live.coinvalue.services;

import live.coinvalue.model.Currency;
import live.coinvalue.model.Source;
import live.coinvalue.repository.CurrencyRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CurrencyService {

    private CurrencyRepository currencyRepository;

    public CurrencyService(CurrencyRepository currencyRepository){
        this.currencyRepository = currencyRepository;
    }

    public List<Currency> getCUrrenciesBySource(Source source){
        return currencyRepository.findCurrenciesBySource(source);
    }

}
