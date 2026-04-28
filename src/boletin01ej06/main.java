package boletin01ej06;

import java.io.*;
import java.util.*;

public class main {
    public static void main(String[] args) {
        // Lista para almacenar los numeros
        List<Integer> numeros = new ArrayList<>();

        // Leemos el archivo
        try (Scanner sc = new Scanner(new FileReader("desordenados.txt"))) {
            // Mientras haya enteros los añadimos a la list
            while (sc.hasNextInt()) {
                numeros.add(sc.nextInt());
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }

        // Ordenamos la lista
        Collections.sort(numeros);

        // Guardamos en un archivo distinto
        try (BufferedWriter out = new BufferedWriter(new FileWriter("ordenados.txt"))) {
            for (Integer n : numeros) {
                out.write(String.valueOf(n));
                out.newLine();
            }
            System.out.println("Números ordenados guardados con éxito.");
            
        } catch (IOException e) {
            System.out.println("Error al escribir el archivo: " + e.getMessage()); 
        }
    }
}