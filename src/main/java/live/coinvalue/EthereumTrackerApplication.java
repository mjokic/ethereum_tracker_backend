package live.coinvalue;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication
@EnableScheduling
public class EthereumTrackerApplication
        extends SpringBootServletInitializer
        implements CommandLineRunner {


//    // Required when exporting as war
//    @Override
//    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
//        return builder.sources(EthereumTrackerApplication.class);
//    }

    public static void main(String[] args) {
		SpringApplication.run(EthereumTrackerApplication.class, args);
	}


    @Override
    public void run(String... strings) throws Exception {
        // running on start
    }
}
