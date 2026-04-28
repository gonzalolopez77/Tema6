package boletin01ej05;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Pedimos los datos al usuario
        System.out.print("Introduce tu nombre: ");
        String nombre = sc.nextLine();
        System.out.print("Introduce tu edad: ");
        int edad = sc.nextInt();

        // El try-with-resources asegura el cierre automático
        try (BufferedWriter out = new BufferedWriter(new FileWriter("datos.txt", true))) {
            
            // Escribimos los datos en el fichero 
            out.write(nombre + " " + edad);
            
            // Añadimos un salto de línea para el próximo registro 
            out.newLine();
            
            System.out.println("Datos guardados correctamente en datos.txt");

        } catch (IOException e) {
            // La apertura de un FileWriter puede generar una IOException
            System.out.println("Error al escribir en el fichero: " + e.getMessage());
        }
        
        sc.close();
    }
}