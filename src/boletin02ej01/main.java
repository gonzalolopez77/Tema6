package boletin02ej01;

import java.io.*;
import java.util.Scanner;

public class main {
    public static void main(String[] args) {
        int caracteres = 0;
        int lineas = 0;
        int palabras = 0;

        // Leemos el archivo carta.txt
        try (Scanner scLineas = new Scanner(new FileReader("carta.txt"))) {
            while (scLineas.hasNextLine()) {
                String linea = scLineas.nextLine();
                lineas++;
                caracteres += linea.length(); // Sumamos la longitud de la cadena
                
                // Usamos un segundo scanner para contar palabras en la linea actual
                Scanner scPalabras = new Scanner(linea);
                while (scPalabras.hasNext()) {
                    scPalabras.next();
                    palabras++;
                }
            }
            
            System.out.println("Caracteres: " + caracteres);
            System.out.println("Líneas: " + lineas);
            System.out.println("Palabras: " + palabras);
            
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }
    }
}