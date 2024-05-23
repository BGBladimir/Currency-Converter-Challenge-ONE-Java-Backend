import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;

public class CurrencyConverter {
    private static final Locale USA = Locale.US;
    private static final Locale RD = new Locale("es", "DO");
    private static final Locale ARG = new Locale("es", "AR");
    private static final Locale BRA = new Locale("pt", "BR");
    private static final Locale COL = new Locale("es", "CO");

    private static class ConversionInfo {
        String fromCurrency;
        String toCurrency;
        Locale locale;

        ConversionInfo(String fromCurrency, String toCurrency, Locale locale) {
            this.fromCurrency = fromCurrency;
            this.toCurrency = toCurrency;
            this.locale = locale;
        }
    }

    private static final Map<String, ConversionInfo> conversions = new HashMap<>();
    static {
        conversions.put("1", new ConversionInfo("USD", "DOP", RD));
        conversions.put("2", new ConversionInfo("DOP", "USD", USA));
        conversions.put("3", new ConversionInfo("USD", "ARS", ARG));
        conversions.put("4", new ConversionInfo("USD", "BRL", BRA));
        conversions.put("5", new ConversionInfo("BRL", "USD", USA));
        conversions.put("6", new ConversionInfo("USD", "COP", COL));
        conversions.put("7", new ConversionInfo("COP", "USD", USA));
    }

   /* public static void main(String[] args) {
        Converter();
    }*/

    public static void EnterParityAndAmount(String baseCurrencyCode, String destinationCurrencyCode, Locale locale, double amount) {
        Scanner captureData = new Scanner(System.in);
        System.out.println("Introduzca la cantidad a convertir, por favor: ");
        amount = captureData.nextDouble();
        captureData.nextLine();

        ApiManagement.ExecuteManagement(baseCurrencyCode, destinationCurrencyCode, locale, amount);
    }

    public static void DisplaySelect(String base, String destination) {
        System.out.println("HAS ELEGIDO CONVERTIR DE " + base + " A " + destination);
    }

    public static void Converter() {
        Scanner captureData = new Scanner(System.in);
        String currencyToConvert = "";

        while (!currencyToConvert.equals("8")) {
            DisplayMenu();

            currencyToConvert = captureData.nextLine();
            System.out.println("******************************************************************************");
            System.out.println(" ");

            if (conversions.containsKey(currencyToConvert)) {
                ConversionInfo info = conversions.get(currencyToConvert);
                DisplaySelect(info.fromCurrency, info.toCurrency);
                EnterParityAndAmount(info.fromCurrency, info.toCurrency, info.locale, 0); // amount se establece dentro del método
            } else if (currencyToConvert.equals("8")) {
                System.out.println("Gracias por utilizar nuestro conversor de monedas");
            } else {
                System.out.println("Por favor elija una opción del 1 al 8");
            }

            // Esperar a que el usuario presione Enter para continuar
            if (!currencyToConvert.equals("8")) {
                System.out.println(" ");
                System.out.println("Presione Enter para continuar...");
                captureData.nextLine();  // Esperar a que el usuario presione Enter
            }
        }

        captureData.close();
    }

    private static void DisplayMenu() {
        System.out.println("******************************************************************************");
        System.out.println("--- BIENVENIDO(A) AL CONVERSOR DE MONEDAS ---");
        System.out.println("Trabajamos con 7 tipos de intercambios con 4 tipos de monedas,");
        System.out.println("las cuales listamos a continuación en sus diferentes paridades.");
        System.out.println("******************************************************************************");
        System.out.println(" ");

        String RD = "Peso dominicano";
        String USA = "Dólar americano";
        String ARG = "Peso argentino";
        String BRA = "Real brasileño";
        String COL = "Peso colombiano";

        System.out.println("1) " + USA + " =>> " + RD);
        System.out.println("2) " + RD + " =>> " + USA);
        System.out.println("3) " + USA + " =>> " + ARG);
        System.out.println("4) " + USA + " =>> " + BRA);
        System.out.println("5) " + BRA + " =>> " + USA);
        System.out.println("6) " + USA + " =>> " + COL);
        System.out.println("7) " + COL + " =>> " + USA);
        System.out.println("8) Salir");

        System.out.println(" ");
        System.out.println("Introduzca el número en la cual se ubica la paridad con la que desea trabajar:");
    }
}
