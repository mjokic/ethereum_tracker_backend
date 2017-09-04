package live.coinvalue.repository;

import live.coinvalue.model.gemini.Gemini;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface GeminiRepository extends CrudRepository<Gemini, Long> {

//    @Modifying
//    @Transactional
//    @Query("UPDATE Gemini SET yesterday_price = :price")
//    void updateYesterdayPrice(@Param("price") double price);

}
