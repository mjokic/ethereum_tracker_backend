package live.coinvalue;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class EthereumTrackerApplication {

	public static void main(String[] args) {
		SpringApplication.run(EthereumTrackerApplication.class, args);
	}
}
