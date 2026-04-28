package boletin02ej04;

import java.io.*;
import java.util.Scanner;

public class main {
    public static void main(String[] args) {
        String alfabeto = "abcdefghijklmnopqrstuvwxyz";
        String cifrado = "";

        // Leemos el alfabeto de cifrado de codec.txt 
        try (Scanner scCodec = new Scanner(new FileReader("codec.txt"))) {
            if (scCodec.hasNextLine()) scCodec.nextLine();
            if (scCodec.hasNextLine()) {
                cifrado = scCodec.nextLine().replace("Cifrado: ", "").replace(" ", "");
            }
        } catch (IOException e) {
            System.out.println("Error con el archivo de claves: " + e.getMessage());
            return;
        }

        // Leemos el original y creamos el cifrado usando BufferedWriter
        try (FileReader in = new FileReader("mensaje.txt");
             BufferedWriter out = new BufferedWriter(new FileWriter("mensaje_cifrado.txt"))) {
            
            int c;
            // Leemos caracter a caracter hasta el final (-1)
            while ((c = in.read()) != -1) {
                char caracterOriginal = (char) c;
                int indice = alfabeto.indexOf(Character.toLowerCase(caracterOriginal));
                
                if (indice != -1) {
                    out.write(cifrado.charAt(indice)); // Escribimos el caracter cifrado 
                } else {
                    out.write(caracterOriginal); // Si no está lo dejamos igual
                }
            }
            System.out.println("Archivo cifrado con éxito.");
            
        } catch (IOException e) {
            System.out.println("Error en la encriptación: " + e.getMessage());
        }
    }
}