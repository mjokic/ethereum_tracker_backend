package live.coinvalue.controllers;

import live.coinvalue.model.Source;
import live.coinvalue.services.SourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping
public class MainController {

    private SourceService sourceService;

    @Autowired
    public MainController(SourceService sourceService) {
        this.sourceService = sourceService;
    }

    @RequestMapping("/")
    public String index() {
        return "Nothing here";
    }


    /**
     * Get all sources from the database
     * Every source contains site name and currency list
     */
    @RequestMapping("/info")
    public List<Source> info() {
        return sourceService.getAllSources();
    }

}
