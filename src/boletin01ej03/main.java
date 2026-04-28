package boletin01ej03;

import java.io.FileReader;
import java.io.IOException;
import java.util.Locale;
import java.util.Scanner;

public class main {
    public static void main(String[] args) {
        double sumaEdades = 0;
        double sumaEstaturas = 0;
        int contador = 0;

        try (Scanner sc = new Scanner(new FileReader("Alumnos.txt"))) {

            sc.useLocale(Locale.US);

            System.out.println("Lista de alumnos:");
            
            // Leemos mientras haya lineas
            while (sc.hasNextLine()) {
                String linea = sc.nextLine();
                
                // Dividimos la linea en partes
                String[] partes = linea.trim().split("\\s+");

                if (partes.length == 3) {
                    String nombre = partes[0];
                    // Convertimos los String
                    int edad = Integer.parseInt(partes[1]);
                    double estatura = Double.parseDouble(partes[2]);

                    System.out.println("- " + nombre);

                    sumaEdades += edad;
                    sumaEstaturas += estatura;
                    contador++;
                }
            }

            // Calculamos y mostramos medias
            if (contador > 0) {
                System.out.println("\n--- Estadísticas ---");
                System.out.println("Media de edad: " + (sumaEdades / contador));
                System.out.println("Media de estatura: " + (sumaEstaturas / contador));
            } else {
                System.out.println("No se encontraron datos de alumnos.");
            }

        } catch (IOException e) {
            System.out.println("Error al acceder al archivo: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Error en el formato de los números: " + e.getMessage());
        }
    }
}