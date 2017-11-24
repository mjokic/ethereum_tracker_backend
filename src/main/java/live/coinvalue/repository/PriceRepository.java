package live.coinvalue.repository;

import live.coinvalue.model.Currency;
import live.coinvalue.model.Price;
import live.coinvalue.model.Source;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface PriceRepository extends JpaRepository<Price, Long> {

    Price findFirstByCurrencyAndSourceOrderByIdDesc(Currency currency, Source source);

    Price findFirstBySourceAndCurrencyAndDateIsBetween(Source source,
                                                       Currency currency,
                                                       Date date1,
                                                       Date date2);
}
