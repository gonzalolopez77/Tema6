package boletin02ej05;

import java.io.*;

public class main {
    public static void main(String[] args) {
        // Usamos BufferedReader para leer líneas
        try (BufferedReader br1 = new BufferedReader(new FileReader("archivo1.txt"));
             BufferedReader br2 = new BufferedReader(new FileReader("archivo2.txt"))) {
            
            String linea1, linea2;
            int numLinea = 1;
            boolean iguales = true;

            while ((linea1 = br1.readLine()) != null && (linea2 = br2.readLine()) != null) {
                if (!linea1.equals(linea2)) {
                    int minLongitud = Math.min(linea1.length(), linea2.length());
                    int posCaracter = 1;
                    
                    for (int i = 0; i < minLongitud; i++) {
                        if (linea1.charAt(i) != linea2.charAt(i)) {
                            break;
                        }
                        posCaracter++;
                    }
                    
                    System.out.println("Diferencia encontrada en línea " + numLinea + ", carácter " + posCaracter);
                    iguales = false;
                    break;
                }
                numLinea++;
            }

            if (iguales) {
                System.out.println("Los archivos son idénticos.");
            }

        } catch (IOException e) {
            System.out.println("Error al comparar: " + e.getMessage());
        }
    }
}