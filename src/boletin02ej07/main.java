package boletin02ej07;

import java.io.*;
import java.time.LocalDate;
import java.util.*;

public class main {
    private static final String ARCHIVO = "clientes.txt";

    public static void main(String[] args) {
        // TreeMap para mantener el orden por DNI
        TreeMap<String, Cliente> clientes = new TreeMap<>();
        Scanner sc = new Scanner(System.in);
        
        cargarDatos(clientes);

        int opcion = 0;
        do {
            System.out.println("\n1. Alta cliente\n2. Baja cliente\n3. Listar clientes\n4. Salir");
            System.out.print("Opción: ");
            opcion = Integer.parseInt(sc.nextLine());

            switch (opcion) {
                case 1 -> { // Alta
                    System.out.print("DNI: "); String dni = sc.nextLine();
                    System.out.print("Nombre: "); String nom = sc.nextLine();
                    System.out.print("Fecha Nac (aaaa-mm-dd): "); 
                    LocalDate fecha = LocalDate.parse(sc.nextLine()); // [cite: 83]
                    System.out.print("Saldo: "); double saldo = Double.parseDouble(sc.nextLine());
                    clientes.put(dni, new Cliente(dni, nom, fecha, saldo));
                }
                case 2 -> { // Baja
                    System.out.print("DNI a eliminar: ");
                    clientes.remove(sc.nextLine());
                }
                case 3 -> listarClientes(clientes); 
                case 4 -> guardarDatos(clientes); 
            }
        } while (opcion != 4);
    }

    private static void listarClientes(TreeMap<String, Cliente> clientes) {
        double suma = 0, max = -1, min = Double.MAX_VALUE;
        for (Cliente c : clientes.values()) {
            System.out.println(c.dni + " | " + c.nombre + " | Saldo: " + c.saldo + " | Edad: " + c.getEdad());
            suma += c.saldo;
            if (c.saldo > max) max = c.saldo;
            if (c.saldo < min) min = c.saldo;
        }
        if (!clientes.isEmpty()) {
            System.out.println("Saldo Máx: " + max + ", Mín: " + min + ", Promedio: " + (suma/clientes.size()));
        }
    }

    private static void cargarDatos(TreeMap<String, Cliente> clientes) {
        try (Scanner scFile = new Scanner(new FileReader(ARCHIVO))) {
            while (scFile.hasNextLine()) {
                String[] p = scFile.nextLine().split(";"); // 
                if (p.length == 4) {
                    clientes.put(p[0], new Cliente(p[0], p[1], LocalDate.parse(p[2]), Double.parseDouble(p[3])));
                }
            }
        } catch (IOException e) { System.out.println("No hay datos previos."); }
    }

    private static void guardarDatos(TreeMap<String, Cliente> clientes) {
        try (BufferedWriter out = new BufferedWriter(new FileWriter(ARCHIVO))) {
            for (Cliente c : clientes.values()) {
                out.write(c.dni + ";" + c.nombre + ";" + c.fechaNacimiento + ";" + c.saldo);
                out.newLine(); // [cite: 64, 74]
            }
        } catch (IOException e) { System.out.println("Error al guardar."); }
    }
}