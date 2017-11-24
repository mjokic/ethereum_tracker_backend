package live.coinvalue.repository;

import live.coinvalue.model.Price;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface PriceRepository extends JpaRepository<Price, Long> {
}
