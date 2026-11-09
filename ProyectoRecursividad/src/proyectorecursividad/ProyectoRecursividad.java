
package proyectorecursividad;

public class ProyectoRecursividad {
    private static String texto;
    private static int indice = 0;

    public static int resolver(String ecuacion) throws Exception {
        if (ecuacion == null || ecuacion.isEmpty()) {
            throw new Exception("vacio");
        }
        texto = ecuacion.replaceAll("\\s+", "");
        indice = 0;
        int resultado = sumarRestar();
        if (indice < texto.length()) {
            throw new Exception("error");
        }
        return resultado;
    }

    private static int sumarRestar() throws Exception {
        int valor = multiplicar();
        while (indice < texto.length()) {
            char operador = texto.charAt(indice);
            if (operador == '+') {
                indice++;
                valor += multiplicar();
            } else if (operador == '-') {
                indice++;
                valor -= multiplicar();
            } else {
                break;
            }
        }
        return valor;
    }

    private static int multiplicar() throws Exception {
        int valor = factor();
        while (indice < texto.length()) {
            char operador = texto.charAt(indice);
            if (operador == '*') {
                indice++;
                valor *= factor();
            } else if (operador == '/') {
                throw new Exception("no soportado");
            } else {
                break;
            }
        }
        return valor;
    }

    private static int factor() throws Exception {
        if (indice >= texto.length()) {
            throw new Exception("fin inesperado");
        }
        char actual = texto.charAt(indice);
        if (actual == '+' || actual == '-') {
            throw new Exception("unario invalido");
        }
        if (actual == '(') {
            indice++;
            int valor = sumarRestar();
            if (indice >= texto.length() || texto.charAt(indice) != ')') {
                throw new Exception("parentesis");
            }
            indice++;
            return valor;
        }
        if (actual >= '0' && actual <= '9') {
            int numero = 0;
            while (indice < texto.length() && texto.charAt(indice) >= '0' && texto.charAt(indice) <= '9') {
                numero = numero * 10 + (texto.charAt(indice) - '0');
                indice++;
            }
            return numero;
        }
        throw new Exception("caracter invalido");
    }

    public static void main(String[] args) {
        try {
            System.out.println(resolver("7+10+2"));
            System.out.println(resolver("1+5*(7-1)+10"));
            System.out.println(resolver("5+7+(1+2*(8-9)+2)-10"));
            System.out.println(resolver("9-2*(75-9+(6+3+(1+2)*1)+7)+9"));
            System.out.println(resolver("5++1+10"));
            System.out.println(resolver("-5+10"));
            System.out.println(resolver("74-7/7"));
            System.out.println(resolver("10+9-A"));
        } catch (Exception e) {
            System.out.println("error");
        }
        
        try {
            System.out.println(resolver("12+4-(3+5+9-12)"));
            System.out.println(resolver("5-3-5+17*(2)"));
            System.out.println(resolver("6-B"));
        } catch (Exception e) {
            System.out.println("error");
        }
    }
}