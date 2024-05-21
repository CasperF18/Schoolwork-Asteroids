package dk.sdu.mmmi.cbse.scoreconsumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@RestController
public class ScoreConsumerApplication {
	private final RestTemplate restTemplate;

	public ScoreConsumerApplication(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	public static void main(String[] args) {
		SpringApplication.run(ScoreConsumerApplication.class, args);
	}

	@GetMapping("/consume-score")
	public double consumeScore() {
		String scoreServiceUrl = "http://localhost:8080/score";
		return restTemplate.getForObject(scoreServiceUrl, Double.class);
	}

	@GetMapping("/reset-score")
	public double resetScore() {
		String resetServiceUrl = "http://localhost:8080/reset";
		return restTemplate.getForObject(resetServiceUrl, Double.class);
	}

	@GetMapping("/update-score")
	public double updateScore(double value) {
		String updateServiceUrl = "http://localhost:8080/update?value=" + value;
		return restTemplate.getForObject(updateServiceUrl, Double.class);
	}

}
