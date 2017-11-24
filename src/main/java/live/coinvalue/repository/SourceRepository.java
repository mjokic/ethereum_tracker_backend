package live.coinvalue.repository;

import live.coinvalue.model.Source;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SourceRepository extends JpaRepository<Source, Long> {

    Source findSourceBySite(String site);

}
