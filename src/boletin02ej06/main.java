package boletin02ej06;

import java.io.*;
import java.util.Scanner;

public class main {
    public static void main(String[] args) {
        String nombreMaxEdad = "", nombreMaxPeso = "", nombreMaxEstatura = "";
        int maxEdad = 0;
        double maxPeso = 0, maxEstatura = 0;

        // Abrimos el flujo de lectura
        try (Scanner sc = new Scanner(new FileReader("deportistas.txt"))) {
            // Saltamos la línea de encabezamiento
            if (sc.hasNextLine()) sc.nextLine();

            while (sc.hasNextLine()) {
                String linea = sc.nextLine();
                String[] partes = linea.split("\\t+"); 

                if (partes.length >= 4) {
                    String nombre = partes[0];
                    int edad = Integer.parseInt(partes[1]);
                    // Reemplazamos coma por punto para Double.parseDouble
                    double peso = Double.parseDouble(partes[2].replace(",", "."));
                    double estatura = Double.parseDouble(partes[3].replace(",", "."));

                    // Comprobaciones de máximos
                    if (edad > maxEdad) { maxEdad = edad; nombreMaxEdad = nombre; }
                    if (peso > maxPeso) { maxPeso = peso; nombreMaxPeso = nombre; }
                    if (estatura > maxEstatura) { maxEstatura = estatura; nombreMaxEstatura = nombre; }
                }
            }

            System.out.println("Deportista de mayor edad: " + nombreMaxEdad);
            System.out.println("Deportista de mayor peso: " + nombreMaxPeso);
            System.out.println("Deportista de mayor estatura: " + nombreMaxEstatura);

        } catch (IOException e) {
            System.out.println("Error al acceder al archivo: " + e.getMessage());
        }
    }
}