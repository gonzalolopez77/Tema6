package boletin02ej07;
import java.time.LocalDate;
import java.time.Period;

public class Cliente implements Comparable<Cliente> {
    String dni, nombre;
    LocalDate fechaNacimiento;
    double saldo;

    public Cliente(String dni, String nombre, LocalDate fechaNacimiento, double saldo) {
        this.dni = dni;
        this.nombre = nombre;
        this.fechaNacimiento = fechaNacimiento;
        this.saldo = saldo;
    }

    public int getEdad() {
        return Period.between(fechaNacimiento, LocalDate.now()).getYears();
    }

    @Override
    public int compareTo(Cliente otro) {
        return this.dni.compareTo(otro.dni);
    }
}