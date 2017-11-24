package live.coinvalue.services;

import live.coinvalue.model.Source;
import live.coinvalue.repository.SourceRepository;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class SourceService {

    private SourceRepository sourceRepository;

    @Autowired
    public SourceService(SourceRepository sourceRepository) {
        this.sourceRepository = sourceRepository;
    }


    public Source getSourceBySite(String site) {
        return sourceRepository.findSourceBySite(site);
    }

    public void saveSource(List<Source> sources) {
        try {
            sourceRepository.save(sources);
        }catch (Exception ex){
            System.out.println("Source or Currency already exits");
        }
    }

    public List<Source> getAllSources(){
        return sourceRepository.findAll();
    }

}
