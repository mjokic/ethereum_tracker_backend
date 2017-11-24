package live.coinvalue.services;

import live.coinvalue.model.Source;
import live.coinvalue.repository.SourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public void saveSource(Source source) {
        sourceRepository.save(source);
    }

    public List<Source> getAllSources(){
        return sourceRepository.findAll();
    }

}