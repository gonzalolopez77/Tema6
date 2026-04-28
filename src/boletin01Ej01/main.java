package boletin01Ej01;

import java.io.FileReader;
import java.io.IOException;
import java.util.Locale;
import java.util.Scanner;

public class main {
    public static void main(String[] args) {
        double suma = 0;
        int contador = 0;

        try (Scanner sc = new Scanner(new FileReader("src/NumerosReales.txt"))) {
            
            // Usamos el punto como separador decimal
            sc.useLocale(Locale.US);

            // Comprobamos si existen mas datos que leer
            while (sc.hasNextDouble()) {
                double numero = sc.nextDouble(); // Lee el siguiente numnero
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