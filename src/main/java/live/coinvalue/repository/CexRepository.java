package live.coinvalue.repository;

import live.coinvalue.model.cex.Cex;
import live.coinvalue.model.cex.pojo.CexPojo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CexRepository extends CrudRepository<Cex, Long> {



}
