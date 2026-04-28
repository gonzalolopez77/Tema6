package boletin01ej07;

import java.io.*;
import java.util.*;

public class main {
    // Definimos el nombre del archivo como una constante
    private static final String ARCHIVO = "agenda.txt";
    private static final int MAX_CONTACTOS = 20;

    public static void main(String[] args) {
        // TreeMap mantiene el orden alfabético automáticamente
        TreeMap<String, String> agenda = new TreeMap<>();
        Scanner sc = new Scanner(System.in);
        
        cargarDatos(agenda);

        int opcion = 0;
        do {
            System.out.println("\n--- AGENDA ---");
            System.out.println("1. Nuevo contacto.");
            System.out.println("2. Buscar por nombre.");
            System.out.println("3. Mostrar todos.");
            System.out.println("4. Salir.");
            System.out.print("Elige una opción: ");
            
            try {
                opcion = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                opcion = 0;
            }

            switch (opcion) {
                case 1: // nuevo contacto
                    if (agenda.size() >= MAX_CONTACTOS) {
                        System.out.println("Error: La agenda está llena.");
                    } else {
                        System.out.print("Nombre: ");
                        String nombre = sc.nextLine();
                        if (agenda.containsKey(nombre)) {
                            System.out.println("El nombre ya existe.");
                        } else {
                            System.out.print("Teléfono: ");
                            String telf = sc.nextLine();
                            agenda.put(nombre, telf);
                            System.out.println("Contacto guardado.");
                        }
                    }
                    break;

                case 2: // buscar
                    System.out.print("Nombre a buscar: ");
                    String busca = sc.nextLine();
                    if (agenda.containsKey(busca)) {
                        System.out.println("Teléfono: " + agenda.get(busca));
                    } else {
                        System.out.println("Contacto no encontrado.");
                    }
                    break;

                case 3: // mostrar todos
                    System.out.println("Listado (Orden Alfabético):");
                    // Al ser TreeMap, el entrySet ya viene ordenado
                    for (Map.Entry<String, String> entrada : agenda.entrySet()) {
                        System.out.println(entrada.getKey() + " - " + entrada.getValue());
                    }
                    break;

                case 4: // salir y guardar
                    guardarDatos(agenda);
                    System.out.println("Datos guardados. ¡Adiós!");
                    break;
                
                default:
                    System.out.println("Opción no válida.");
            }
        } while (opcion != 4);
    }

    // Método para cargar datos al iniciar
    private static void cargarDatos(TreeMap<String, String> agenda) {
        try (Scanner scFile = new Scanner(new FileReader(ARCHIVO))) {
            while (scFile.hasNextLine()) {
                String linea = scFile.nextLine();
                String[] partes = linea.split(":");
                if (partes.length == 2) {
                    agenda.put(partes[0], partes[1]);
                }
            }
        } catch (IOException e) {
            System.out.println("Iniciando agenda nueva (sin datos previos).");
        }
    }

    // Método para guardar datos al salir
    private static void guardarDatos(TreeMap<String, String> agenda) {
        try (BufferedWriter out = new BufferedWriter(new FileWriter(ARCHIVO))) {
            for (Map.Entry<String, String> entrada : agenda.entrySet()) {
                out.write(entrada.getKey() + ":" + entrada.getValue());
                out.newLine();
            }
        
        } catch (IOException e) {
            System.out.println("Error al guardar: " + e.getMessage());
        }
    }
}