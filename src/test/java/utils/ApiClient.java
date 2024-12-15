package utils;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import io.qameta.allure.Allure;
import java.net.http.*;
import java.net.URI;

public class ApiClient {
    static String endpoint = "https://api.dictionaryapi.dev/api/v2/entries/en/Playwright";
    private static final HttpClient client = HttpClient.newHttpClient();

    public static JsonObject sendGetRequest() {
        Allure.attachment("URL",endpoint);
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(endpoint)).GET().build();
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            Allure.addAttachment("API Response", response.body()); // Add response to Allure report
            JsonElement jsonElement = JsonParser.parseString(response.body());
            if (jsonElement.isJsonArray()) {
                return jsonElement.getAsJsonArray().get(0).getAsJsonObject(); // Handle JSON Array response
            }
            return jsonElement.getAsJsonObject();
        } catch (Exception e) {
            throw new RuntimeException("API Request failed", e);
        }
    }
}
