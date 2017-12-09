package live.coinvalue.services;

import live.coinvalue.model.Currency;
import live.coinvalue.model.Price;
import live.coinvalue.model.Source;
import live.coinvalue.repository.PriceRepository;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class PriceService {

    private PriceRepository priceRepository;

    public PriceService(PriceRepository priceRepository) {
        this.priceRepository = priceRepository;
    }


    public void savePrice(Price price) {
        priceRepository.save(price);
    }


    public Price getLatestPrice(Source source, Currency currency) {
        return priceRepository.findFirstByCurrencyAndSourceOrderByIdDesc(currency, source);
    }


    public Price getYesterdayPrice(Currency currency,
                                   Source source,
                                   Price price) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(price.getDate());
        calendar.add(Calendar.HOUR, -24);
        Date yesterday = calendar.getTime();
        Date now = price.getDate();
        return priceRepository
                .findFirstBySourceAndCurrencyAndDateIsBetween(source, currency, yesterday, now);
    }


    public List<Price> getPricesFromLastHour(Source source, Currency currency) {
        return priceRepository.findAllHourAgo(source.getId(), currency.getName());
    }

    public List<Price> getPricesFromLastDay(Source source, Currency currency) {
        return priceRepository.findAllDayAgo(source.getId(), currency.getName());
    }

    public List<Price> getPricesFromWeekAgo(Source source, Currency currency) {
        return priceRepository.findAllWeekOrMonthAgo(source.getId(), currency.getName(), 7);
    }

    public List<Price> getPricesFromMonthAgo(Source source, Currency currency) {
        return priceRepository.findAllWeekOrMonthAgo(source.getId(), currency.getName(), 30);
    }

}
