package boletin01ej08;

import java.io.*;
import java.time.LocalDate;
import java.util.Scanner;

public class main {
    private static final String ARCHIVO = "temperaturas.txt";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int opcion = 0;

        do {
            System.out.println("\n--- ESTACIÓN METEOROLÓGICA ---");
            System.out.println("1. Registrar nueva temperatura.");
            System.out.println("2. Mostrar historial de registros.");
            System.out.println("3. Salir.");
            System.out.print("Seleccione una opción: ");
            
            try {
                opcion = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                opcion = 0;
            }

            switch (opcion) {
                case 1 -> registrarTemperatura(sc);
                case 2 -> mostrarHistorial();
                case 3 -> System.out.println("Saliendo del programa...");
                default -> System.out.println("Opción no válida.");
            }
        } while (opcion != 3);
    }

    private static void registrarTemperatura(Scanner sc) {
        // Obtenemos la fecha actual usando LocalDate 
        LocalDate fechaHoy = LocalDate.now();
        
        System.out.print("Introduce temperatura máxima: ");
        int max = Integer.parseInt(sc.nextLine());
        System.out.print("Introduce temperatura mínima: ");
        int min = Integer.parseInt(sc.nextLine());

        try (BufferedWriter out = new BufferedWriter(new FileWriter(ARCHIVO, true))) {
            out.write(fechaHoy + "," + max + "," + min);
            out.newLine(); 
            System.out.println("Registro guardado con éxito.");
        } catch (IOException e) {
            System.out.println("Error al escribir en el archivo: " + e.getMessage());
        }
    }

    private static void mostrarHistorial() {
        File file = new File(ARCHIVO);
        if (!file.exists()) {
            System.out.println("No hay registros previos.");
            return;
        }

        int maxAbsoluta = Integer.MIN_VALUE;
        int minAbsoluta = Integer.MAX_VALUE;
        boolean hayDatos = false;

        System.out.println("\n--- HISTORIAL DE TEMPERATURAS ---");
        System.out.println("Fecha\t\tMáx\tMín");

        try (Scanner scFile = new Scanner(new FileReader(ARCHIVO))) {
            while (scFile.hasNextLine()) {
                String linea = scFile.nextLine();
                if (linea.isEmpty() || linea.startsWith("Fecha")) continue;

                String[] partes = linea.split(",");
                if (partes.length == 3) {
                    String fecha = partes[0];
                    int max = Integer.parseInt(partes[1]);
                    int min = Integer.parseInt(partes[2]);

                    System.out.println(fecha + "\t" + max + "º\t" + min + "º");

                    // Calculamos los valores extremos del historial
                    if (max > maxAbsoluta) maxAbsoluta = max;
                    if (min < minAbsoluta) minAbsoluta = min;
                    hayDatos = true;
                }
            }

            if (hayDatos) {
                System.out.println("---------------------------------");
                System.out.println("Máximo histórico: " + maxAbsoluta + "º");
                System.out.println("Mínimo histórico: " + minAbsoluta + "º");
            }

        } catch (IOException e) {
            System.out.println("Error al leer el historial: " + e.getMessage());
        }
    }
}