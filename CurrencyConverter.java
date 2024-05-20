import java.text.DecimalFormat;
import java.util.Scanner;

public class CurrencyConverter {

    public static void EnterParityAndAmount(String baseCurrencyCode, String destinationCurrencyCode, double amount){
        Scanner captureData = new Scanner(System.in);
        System.out.println("Introduzca la cantidad a convertir, por favor: ");
        amount = captureData.nextDouble();
        captureData.nextLine();

        ApiManagement.ExecuteManagement(baseCurrencyCode, destinationCurrencyCode, amount);
    }

    public static void Converter(){
        Scanner captureData = new Scanner(System.in);
        String currencyToConvert="";
        double amount = 0;

        while (!currencyToConvert.equalsIgnoreCase("8")){
            System.out.println("******************************************************************************");
            System.out.println("--- BIENVENIDO(A) AL CONVERSOR DE MONEDAS ---");
            System.out.println("Trabajamos con 7 tipos de monedas, las cuales listamos a continuación");
            System.out.println("en sus diferentes paridades.");
            System.out.println("******************************************************************************");
            System.out.println(" ");

            System.out.println("1) Dólar americano =>> Peso dominicano");
            System.out.println("2) Peso dominicano =>> Dólar americano");
            System.out.println("3) Dólar americano =>> Peso argentino");
            System.out.println("4) Dólar americano =>> Real brásileño");
            System.out.println("5) Real brásileño =>> Dólar americano");
            System.out.println("6) Dólar americano =>> Peso colombiano");
            System.out.println("7) Peso colombiano =>> Dólar americano");
            System.out.println("8) Salir");

            System.out.println(" ");
            System.out.println("Introduzca el número en la cual se ubica la paridad con la que desea trabajar:");
            currencyToConvert = captureData.nextLine();
            System.out.println("******************************************************************************");
            System.out.println(" ");

            switch (currencyToConvert){
                case "1":
                    System.out.println("Has elegido convertir de Dolar americano a Peso dominicano");
                    EnterParityAndAmount("USD", "DOP", amount);
                    break;

                case "2":
                    System.out.println("Has elegido convertir de Peso dominicano a Dólar americano");
                    EnterParityAndAmount("DOP", "USD", amount);
                    break;

                case "3":
                    System.out.println("Has elegido convertir Dólar americano a Peso argentino.");
                    EnterParityAndAmount("USD", "ARS", amount);
                    break;
                case "4":
                    System.out.println("Has elegido convertir Dólar americano a Real brasileño.");
                    EnterParityAndAmount("USD", "BRL", amount);
                    break;
                case "5":
                    System.out.println("Has elegido convertir Real brasileño a Dólar americano.");
                    EnterParityAndAmount("BRL", "USD", amount);
                    break;
                case "6":
                    System.out.println("Has elegido convertir Dólar americano a Peso colombiano.");
                    EnterParityAndAmount("USD", "COP", amount);
                    break;
                case "7":
                    System.out.println("Has elegido convertir Peso colombiano a Dólar americano.");
                    EnterParityAndAmount("COP", "USD", amount);
                    break;

                case "8":
                    System.out.println("Gracias por utilizar nuestro conversor de monedas");
                    break;
                default:
                    System.out.println("Por favor elija una opción del 1 al 8");
                    break;
            }

            // Esperar a que el usuario presione Enter para continuar
            if (!currencyToConvert.equalsIgnoreCase("8")) {
                System.out.println(" ");
                System.out.println("Presione Enter para continuar...");
                captureData.nextLine();
            }

        }

        captureData.close();
    }
}
