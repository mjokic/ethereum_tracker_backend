package live.coinvalue.repository;

import live.coinvalue.model.Currency;
import live.coinvalue.model.Price;
import live.coinvalue.model.Source;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
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


    @Query(value = "SELECT * FROM price WHERE source = ?1 AND currency = ?2 " +
            "ORDER BY id DESC LIMIT 60", nativeQuery = true)
    List<Price> findAllHourAgo(long sourceId, String currency);

    @Query(value = "SELECT * FROM price " +
            "WHERE source = ?1 AND currency = ?2 AND date > now() - INTERVAL 1 DAY " +
            "GROUP BY EXTRACT(HOUR FROM date) ORDER BY id DESC", nativeQuery = true)
    List<Price> findAllDayAgo(long sourceId, String currency);

    @Query(value = "SELECT * FROM price WHERE source = ?1 AND currency = ?2 " +
            "GROUP BY EXTRACT(DAY FROM date) ORDER BY id DESC LIMIT ?3",
            nativeQuery = true)
    List<Price> findAllWeekOrMonthAgo(long sourceId, String currency, int limit);
}
