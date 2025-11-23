package Banco;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Stack;

public class Banco {

    private double saldoGeneral = 15000000;
    private ArrayList<Cuenta> cuentas = new ArrayList<>();
    private Stack<String> historial = new Stack<>();
    private DecimalFormat df = new DecimalFormat("#,###");

    public String consultarSaldo() {
        return df.format(saldoGeneral);
    }

    public void consignar(double monto) {
        saldoGeneral += monto;
    }

    public boolean retirar(double monto) {
        if (monto > saldoGeneral) {
            return false;
        }
        saldoGeneral -= monto;
        return true;
    }

    public void crearCuenta(String nombre, String apellido, double saldoInicial) {
        cuentas.add(new Cuenta(nombre, apellido, saldoInicial));
    }

    public ArrayList<Cuenta> getCuentas() {
        return cuentas;
    }

    public boolean eliminarCuenta(int index) {
        if (index >= 0 && index < cuentas.size()) {
            cuentas.remove(index);
            return true;
        }
        return false;
    }

    public boolean transferencia(double monto, String bancoDestino) {
        if (monto > saldoGeneral) {
            return false;
        }
        saldoGeneral -= monto;
        historial.push("Transferencia de " + df.format(monto) + " a " + bancoDestino);
        return true;
    }

    public Stack<String> getHistorial() {
        return historial;
    }
}

