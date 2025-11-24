package Banco;

public class Cuenta {

    // Nombre del titular de la cuenta
    private String nombre;

    // Apellido del titular de la cuenta
    private String apellido;

    // Saldo actual de la cuenta
    private double saldo;

    // Constructor: inicializa los datos de la cuenta
    public Cuenta(String nombre, String apellido, double saldo) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.saldo = saldo;
    }

    // Retorna el nombre del titular
    public String getNombre() {
        return nombre;
    }

    // Retorna el apellido del titular
    public String getApellido() {
        return apellido;
    }

    // Retorna el saldo actual de la cuenta
    public double getSaldo() {
        return saldo;
    }

    // Consigna (añade) un monto al saldo actual
    public void consignar(double monto) {
        saldo += monto;
    }

    // Retirar un monto del saldo si hay suficiente dinero
    public boolean retirar(double monto) {
        if (monto > saldo) {
            return false; // No hay suficiente saldo
        }
        saldo -= monto;  // Retiro exitoso
        return true;
    }
}
