package boletin01ej04;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String cadena;

        // Usamos try-with-resources para el cierre automático
        try (BufferedWriter out = new BufferedWriter(new FileWriter("CadenasUsuario.txt", true))) {
            
            System.out.println("Introduce frases (escribe 'fin' para terminar):");
            
            do {
                System.out.print("> ");
                cadena = sc.nextLine();

                if (!cadena.equalsIgnoreCase("fin")) {
                    out.write(cadena);      // Escribe la cadena
                    out.newLine();          // Añade el salto de línea 
                }

            } while (!cadena.equalsIgnoreCase("fin"));

            System.out.println("Datos guardados correctamente.");

        } catch (IOException e) {
            System.out.println("Error al escribir en el fichero: " + e.getMessage());
        }
        
        sc.close();
    }
}