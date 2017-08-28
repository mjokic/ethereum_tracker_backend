package live.coinvalue.repository;

import live.coinvalue.model.gemini.Gemini;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GeminiRepository extends CrudRepository<Gemini, Long> {


}
