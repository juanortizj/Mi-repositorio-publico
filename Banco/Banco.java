package Banco;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Stack;

public class Banco {

    private double saldoGeneral = 15000000;
    private ArrayList<Cuenta> cuentas = new ArrayList<>();
    private Stack<String> historial = new Stack<>();
    private DecimalFormat df = new DecimalFormat("#,###");

    // Árbol Binario de Cuentas
    private ArbolCuentas arbol = new ArbolCuentas();

    public String consultarSaldo() { return df.format(saldoGeneral); }

    public void consignar(double monto) { saldoGeneral += monto; }

    public boolean retirar(double monto) {
        if (monto > saldoGeneral) return false;
        saldoGeneral -= monto;
        return true;
    }

    public void crearCuenta(String nombre, String apellido, double saldoInicial) {
        Cuenta nueva = new Cuenta(nombre, apellido, saldoInicial);
        cuentas.add(nueva);
        arbol.insertar(nueva);  // Insertamos en el árbol
    }

    public ArrayList<Cuenta> getCuentas() { return cuentas; }

    public boolean eliminarCuenta(int index) {
        if (index >= 0 && index < cuentas.size()) {
            cuentas.remove(index);
            return true;
        }
        return false;
    }

    public boolean transferencia(double monto, String bancoDestino) {
        if (monto > saldoGeneral) return false;
        saldoGeneral -= monto;
        historial.push("Transferencia de " + df.format(monto) + " a " + bancoDestino);
        return true;
    }

    public Stack<String> getHistorial() { return historial; }

    // Buscar una cuenta usando el árbol
    public Cuenta buscarCuenta(String nombre, String apellido) {
        return arbol.buscar(nombre, apellido);
    }

    // Mostrar cuentas ordenadas del árbol
    public void mostrarCuentasOrdenadas() {
        arbol.imprimirEnOrden();
    }
}
