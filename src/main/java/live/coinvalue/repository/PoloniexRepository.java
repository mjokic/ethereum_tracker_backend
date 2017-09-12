package live.coinvalue.repository;

import live.coinvalue.model.poloniex.Poloniex;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PoloniexRepository extends CrudRepository<Poloniex, Long> {
}
