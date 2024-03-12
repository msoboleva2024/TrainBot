package AbstractComponent;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

import jakarta.ws.rs.core.UriBuilder;

public class TelegramNotifier {
	
    private static final String CHAT_ID = "-4170965128";
    private static final String TOKEN = "7131344518:AAG_4SJLBVtNQ4tCD5Rh0dAk4Bv0bQUZfr0";
 
    public void sendMsgToTelegram(String message) throws IOException, InterruptedException {
 
  
 
        HttpClient client = HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(5))
                .version(HttpClient.Version.HTTP_2)
                .build();
 
        UriBuilder builder = UriBuilder
                .fromUri("https://api.telegram.org")
                .path("/{token}/sendMessage")
                .queryParam("chat_id", CHAT_ID)
                .queryParam("text", message);
 
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(builder.build("bot" + TOKEN))
                .timeout(Duration.ofSeconds(5))
                .build();
 
        HttpResponse<String> response = client
          .send(request, HttpResponse.BodyHandlers.ofString());
 
        System.out.println(response.statusCode());
        System.out.println(response.body());
    }
}


