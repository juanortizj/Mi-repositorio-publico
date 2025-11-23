package Banco;

public class Cuenta {
    private String nombre;
    private String apellido;
    private double saldo;

    public Cuenta(String nombre, String apellido, double saldo) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.saldo = saldo;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public double getSaldo() {
        return saldo;
    }

    public void consignar(double monto) {
        saldo += monto;
    }

    public boolean retirar(double monto) {
        if (monto > saldo) {
            return false;
        }
        saldo -= monto;
        return true;
    }
}

