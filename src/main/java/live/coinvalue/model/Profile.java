package live.coinvalue.model;

import java.util.List;

public class Profile {
    /**
        This contains name of 'source' site
        and list of available currencies
     */


    private String site;
    private List<Currency> currencies;

    public Profile(){}

    public Profile(String site, List<Currency> currencies){
        this.site = site;
        this.currencies = currencies;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public List<Currency> getCurrencies() {
        return currencies;
    }

    public void setCurrencies(List<Currency> currencies) {
        this.currencies = currencies;
    }

}
