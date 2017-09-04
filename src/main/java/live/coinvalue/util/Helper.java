package live.coinvalue.util;

import live.coinvalue.model.ResponsePojo;
import org.apache.commons.math3.util.Precision;

public class Helper {

    public static ResponsePojo generateResponsePojo(double oldPrice, double currentPrice){
        double change = Precision.round(((oldPrice * 100 / currentPrice) - 100) * (-1),2);
        return new ResponsePojo(currentPrice, change);
    }

}
