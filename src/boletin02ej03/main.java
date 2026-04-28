package boletin02ej03;

import java.io.*;
import java.util.Scanner;

public class main {
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        
        // Usamos Scanner sobre FileReader para leer linea a linea 
        try (Scanner scFile = new Scanner(new FileReader("carta.txt"))) {
            int contadorLineas = 0;
            
            while (scFile.hasNextLine()) {
                System.out.println(scFile.nextLine());
                contadorLineas++;
                
                // Cada 24 líneas hacemos una pausa
                if (contadorLineas == 24) {
                    System.out.print("--- Pulsa Enter para continuar ---");
                    teclado.nextLine();
                    contadorLineas = 0;
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }
    }
}