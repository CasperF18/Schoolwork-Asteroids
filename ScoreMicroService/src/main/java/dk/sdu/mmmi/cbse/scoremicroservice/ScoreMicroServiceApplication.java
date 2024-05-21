package dk.sdu.mmmi.cbse.scoremicroservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@CrossOrigin
public class ScoreMicroServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ScoreMicroServiceApplication.class, args);
	}

	private double score;

	@GetMapping("/score")
	public double getScore() {
		return score;
	}

	@GetMapping("/reset")
	public double resetScore() {
		score = 0;
		return score;
	}

	@GetMapping("/update")
	public double updateScore(@RequestParam(value = "value") double value) {
		score += value;
		return score;
	}

}
