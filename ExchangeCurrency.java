import com.google.gson.JsonObject;

public class ExchangeCurrency {
    public static class ExchangeRateResponse {
        String result;
        String base_code;
        String target_code;
        //JsonObject conversion_rate;  // Usar JsonObject para almacenar tasas de conversión
        double conversion_rate;
        double conversion_result;
    }
}
