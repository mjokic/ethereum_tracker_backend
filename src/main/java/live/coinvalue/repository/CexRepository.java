package live.coinvalue.repository;

import live.coinvalue.sources.cex_io.CexSource;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CexRepository extends CrudRepository<CexSource, Long> {



}
