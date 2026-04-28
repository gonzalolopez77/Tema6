package boletin02ej02;

import java.io.*;
import java.util.*;

public class main {
    private static final String ARCHIVO = "firmas.txt";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        TreeSet<String> firmas = new TreeSet<>();

        // Carga inicial de datos
        try (Scanner scFile = new Scanner(new FileReader(ARCHIVO))) {
            while (scFile.hasNextLine()) {
                firmas.add(scFile.nextLine());
            }
        } catch (IOException e) {
            System.out.println("Creando nuevo libro de firmas...");
        }

        System.out.println("1. Mostrar libro de firmas.");
        System.out.println("2. Insertar nuevo nombre.");
        System.out.print("Opción: ");
        int opcion = Integer.parseInt(sc.nextLine());

        if (opcion == 1) {
            for (String f : firmas) System.out.println(f);
        } else if (opcion == 2) {
            System.out.print("Introduce nombre: ");
            String nombre = sc.nextLine();
            if (firmas.contains(nombre)) {
                System.out.println("El nombre ya existe.");
            } else {
                // Guardamos en el archivo usando append
                try (BufferedWriter out = new BufferedWriter(new FileWriter(ARCHIVO, true))) {
                    out.write(nombre);
                    out.newLine(); 
                    System.out.println("Nombre añadido.");
                } catch (IOException e) {
                    System.out.println("Error al guardar.");
                }
            }
        }
    }
}