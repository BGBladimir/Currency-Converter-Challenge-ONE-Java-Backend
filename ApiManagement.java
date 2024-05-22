import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.text.NumberFormat;
import java.time.Duration;
import java.util.Locale;

public class ApiManagement {

    private static final String apiKey = "67c5df84039dd462f697616a";
    private static final String addressURL = "https://v6.exchangerate-api.com/v6/" + apiKey + "/pair/";

    public static void ExecuteManagement(String baseCurrencyCode, String destinationCurrencyCode, Locale locale, double amount) {
        URI address = buildURI(baseCurrencyCode, destinationCurrencyCode, amount);

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = buildHttpRequest(address);

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            handleResponse(response, locale, amount);
        } catch (JsonSyntaxException e) {
            handleError("JSON Parsing Error: ", e);
        } catch (Exception e) {
            handleError("Request Error: ", e);
        }
    }

    private static URI buildURI(String baseCurrencyCode, String destinationCurrencyCode, double amount) {
        return URI.create(addressURL + baseCurrencyCode + "/" + destinationCurrencyCode + "/" + amount);
    }

    private static HttpRequest buildHttpRequest(URI address) {
        return HttpRequest.newBuilder()
                .uri(address)
                .timeout(Duration.ofMinutes(1)) // Establecer tiempo de espera para la solicitud
                .build();
    }

    private static void handleResponse(HttpResponse<String> response, Locale locale, double amount) {
        if (response.statusCode() == 200) {
            parseAndDisplayResponse(response.body(), locale, amount);
        } else {
            System.err.println("HTTP Error: " + response.statusCode());
        }
    }

    private static void parseAndDisplayResponse(String responseBody, Locale locale, double amount) {
        ExchangeCurrency.ExchangeRateResponse exchangeRateResponse = new Gson().fromJson(responseBody, ExchangeCurrency.ExchangeRateResponse.class);
        if ("success".equals(exchangeRateResponse.getResult())) {
            displayResult(exchangeRateResponse, locale, amount);
        } else {
            System.err.println("Error in response: " + exchangeRateResponse.getResult());
        }
    }

    private static void displayResult(ExchangeCurrency.ExchangeRateResponse exchangeRateResponse, Locale locale, double amount) {
        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(locale);
        String formattedConversionResult = currencyFormatter.format(exchangeRateResponse.getConversionResult());
        String yellow = "\033[0;33m";
        String reset = "\033[0m";

        System.out.println("************************************************************************");
        System.out.println("Resultado: " + "- " + yellow + formattedConversionResult + " " + exchangeRateResponse.getTargetCode() + reset + " -");
        System.out.println("Operación: " + amount + " " + exchangeRateResponse.getBaseCode() + " x " + exchangeRateResponse.getConversionRate() + " " + exchangeRateResponse.getTargetCode() + " = " + exchangeRateResponse.getConversionResult() + " " + exchangeRateResponse.getTargetCode());
    }

    private static void handleError(String message, Exception e) {
        System.err.println(message + e.getMessage());
        e.printStackTrace();
    }
}

