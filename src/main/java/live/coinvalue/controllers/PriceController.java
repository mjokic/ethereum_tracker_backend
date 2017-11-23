package live.coinvalue.controllers;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/")
public class PriceController {

    /**
     * Getting ETH price from selected source for selected currency
     * @param source Selected source
     * @param currency Selected currency
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, path = "/{source}/{currency}")
    public String test(@PathVariable String source,
                       @PathVariable String currency){

        System.out.println(source);
        System.out.println(currency);
        return "yey!";
    }

}
