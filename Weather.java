import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.URI;
import org.json.JSONObject;

public class Weather {
    public static void main(String[] args) {
        String apiKey = "your_api_key_here";
        String city = "Chicago";
        String units = "metric";
        String url = String.format("https://api.openweathermap.org/data/2.5/weather?q=%s&units=%s&appid=%s", city, units, apiKey);

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 200) {
                JSONObject jsonResponse = new JSONObject(response.body());
                String weather = jsonResponse.getJSONArray("weather").getJSONObject(0).getString("description");
                double temperature = jsonResponse.getJSONObject("main").getDouble("temp");
                System.out.printf("The weather in %s is %s with a temperature of %.1f°C%n", city, weather, temperature);
            } else {
                System.out.println("Error: Unable to fetch weather data. Status Code: " + response.statusCode());
            }
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }
}


// name of the public class ex. test must be the same as the name of 
// the file that precedes .java in this context