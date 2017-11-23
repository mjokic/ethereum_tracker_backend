package live.coinvalue.repository;

import live.coinvalue.sources.poloniex_com.Poloniex;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PoloniexRepository extends CrudRepository<Poloniex, Long> {
}
