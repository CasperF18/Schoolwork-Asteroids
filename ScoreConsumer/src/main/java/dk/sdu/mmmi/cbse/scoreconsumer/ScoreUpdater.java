package dk.sdu.mmmi.cbse.scoreconsumer;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ScoreUpdater {
    private static final String UPDATE_SCORE_URL = "http://localhost:8081/update-score?value=1";
    private HttpClient client;

    public ScoreUpdater() {
        this.client = HttpClient.newHttpClient();
    }

    public void updateScore() {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(UPDATE_SCORE_URL))
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println("Score updated: " + response.body());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
