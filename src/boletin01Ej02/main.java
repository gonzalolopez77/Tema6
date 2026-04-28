package boletin01Ej02;

import java.io.FileReader;
import java.io.IOException;
import java.util.Locale;
import java.util.Scanner;

public class main {
    public static void main(String[] args) {
        int suma = 0;
        int contador = 0;

        try (Scanner sc = new Scanner(new FileReader("src/boletin01Ej02/NumEnteros.txt"))) {

            // Comprobamos si existen mas datos que leer
            while (sc.hasNextInt()) {
                int numero = sc.nextInt(); // Lee el siguiente numero
                suma += numero;
                contador++;
            }

            if (contador > 0) {
                double media = suma / contador;
                System.out.println("Suma total: " + suma);
                System.out.println("Media aritmetica: " + media);
            } else {
                System.out.println("El fichero esta vacio o no contiene numeros.");
            }

        } catch (IOException e) {
            // Error
            System.out.println("Error al acceder al archivo: " + e.getMessage());
        }
    }
}
