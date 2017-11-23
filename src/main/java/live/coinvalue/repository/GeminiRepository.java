package live.coinvalue.repository;

import live.coinvalue.sources.gemini_com.Gemini;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GeminiRepository extends CrudRepository<Gemini, Long> {

//    @Modifying
//    @Transactional
//    @Query("UPDATE Gemini SET yesterday_price = :price")
//    void updateYesterdayPrice(@Param("price") double price);

}
