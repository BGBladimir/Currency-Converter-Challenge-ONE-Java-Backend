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
    public static void ExecuteManagement(String baseCurrencyCode, String destinationCurrencyCode,Locale locale, double amount) {
        String apiKey = "67c5df84039dd462f697616a";  // Sustituye con tu clave de API real
        URI address = URI.create("https://v6.exchangerate-api.com/v6/" + apiKey + "/pair/" + baseCurrencyCode + "/" + destinationCurrencyCode + "/" + amount);

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(address)
                .timeout(Duration.ofMinutes(1))  // Establecer tiempo de espera para la solicitud
                .build();

        try {
            //Enviar la solicitud y obtener la respuesta
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 200) {
                // Analizar la respuesta JSON
                ExchangeCurrency.ExchangeRateResponse exchangeRateResponse = new Gson().fromJson(response.body(), ExchangeCurrency.ExchangeRateResponse.class);
                if ("success".equals(exchangeRateResponse.result)) {
                    // Formatear los valores monetarios
                    NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(locale);

                    String formattedConversionResult = currencyFormatter.format(exchangeRateResponse.conversion_result);

                    String yellow = "\033[0;33m";
                    String reset = "\033[0m";

                    System.out.println("************************************************************************");
                    System.out.println("Resultado: " + "- " + yellow + formattedConversionResult + " " + exchangeRateResponse.target_code + reset + " -" );
                    System.out.println("Operación: " + amount + " " + exchangeRateResponse.base_code + " x " + exchangeRateResponse.conversion_rate + " " + exchangeRateResponse.target_code + " = " + exchangeRateResponse.conversion_result + " " + exchangeRateResponse.target_code);
                } else {
                    System.err.println("Error in response: " + exchangeRateResponse.result);
                }
            } else {
                System.err.println("HTTP Error: " + response.statusCode());
            }
        } catch (JsonSyntaxException e) {
            System.err.println("JSON Parsing Error: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Request Error: " + e.getMessage());
            e.printStackTrace();  // Para obtener más detalles del error
        }
    }

}

