import com.google.gson.annotations.SerializedName;

public class ExchangeCurrency {
    public static class ExchangeRateResponse {
        @SerializedName("result")
        private final String result;

        @SerializedName("base_code")
        private final String baseCode;

        @SerializedName("target_code")
        private final String targetCode;

        @SerializedName("conversion_rate")
        private final double conversionRate;

        @SerializedName("conversion_result")
        private final double conversionResult;

        // Constructor
        public ExchangeRateResponse(String result, String baseCode, String targetCode, double conversionRate, double conversionResult) {
            this.result = result;
            this.baseCode = baseCode;
            this.targetCode = targetCode;
            this.conversionRate = conversionRate;
            this.conversionResult = conversionResult;
        }

        // Getters
        public String getResult() {
            return result;
        }

        public String getBaseCode() {
            return baseCode;
        }

        public String getTargetCode() {
            return targetCode;
        }

        public double getConversionRate() {
            return conversionRate;
        }

        public double getConversionResult() {
            return conversionResult;
        }

        @Override
        public String toString() {
            return "ExchangeRateResponse{" +
                    "result='" + result + '\'' +
                    ", baseCode='" + baseCode + '\'' +
                    ", targetCode='" + targetCode + '\'' +
                    ", conversionRate=" + conversionRate +
                    ", conversionResult=" + conversionResult +
                    '}';
        }
    }
}
