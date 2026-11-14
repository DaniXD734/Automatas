
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AnalizadorFuncion {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("=== ANALIZADOR DE FUNCIONES ===");
        System.out.println("Ejemplo: int sumar(int a, int b) { return a + b; }");
        System.out.println();

        System.out.println("Escribe una funcion:");
        String funcion = sc.nextLine();

        if (analizarFuncion(funcion)) {
            System.out.println("\nRESULTADO: Funcion correctamente escrita.");
        } else {
            System.out.println("\nRESULTADO: Funcion incorrecta.");
        }

        sc.close();
    }

    public static boolean analizarFuncion(String codigo) {

        String patron =
            "(public |private |protected )?" +
            "(static )?" +
            "(int|double|float|char|boolean|String|void)\\s+" +
            "[a-zA-Z_][a-zA-Z0-9_]*\\s*" +
            "\\([^()]*\\)\\s*" +
            "\\{[\\s\\S]*\\}";

        Pattern p = Pattern.compile(patron);
        Matcher m = p.matcher(codigo.trim());

        if (!m.matches()) {
            return false;
        }

        int llaves = 0;

        for (char c : codigo.toCharArray()) {
            if (c == '{') {
                llaves++;
            } else if (c == '}') {
                llaves--;

                if (llaves < 0) {
                    return false;
                }
            }
        }

        if (llaves != 0) {
            return false;
        }
        
        int parentesis = 0;

        for (char c : codigo.toCharArray()) {
            if (c == '(') parentesis++;
            if (c == ')') parentesis--;

            if (parentesis < 0) return false;
        }

        return parentesis == 0;
    }
}
