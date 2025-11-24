package Banco;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Stack;

public class Banco {

    // Saldo total que maneja el banco
    private double saldoGeneral = 15000000;

    // Lista de cuentas creadas en el banco
    private ArrayList<Cuenta> cuentas = new ArrayList<>();

    // Pila para almacenar el historial de transacciones (último en entrar, primero en salir)
    private Stack<String> historial = new Stack<>();

    // Formato para imprimir valores numéricos con separador de miles
    private DecimalFormat df = new DecimalFormat("#,###");

    // Retorna el saldo general formateado
    public String consultarSaldo() {
        return df.format(saldoGeneral);
    }

    // Aumenta el saldo general con el monto consignado
    public void consignar(double monto) {
        saldoGeneral += monto;
    }

    // Resta el monto solicitado del saldo general si hay suficiente dinero
    public boolean retirar(double monto) {
        if (monto > saldoGeneral) {
            return false; // No hay suficiente saldo
        }
        saldoGeneral -= monto;
        return true; // Retiro exitoso
    }

    // Crea una nueva cuenta y la agrega a la lista de cuentas del banco
    public void crearCuenta(String nombre, String apellido, double saldoInicial) {
        cuentas.add(new Cuenta(nombre, apellido, saldoInicial));
    }

    // Retorna la lista de cuentas creadas
    public ArrayList<Cuenta> getCuentas() {
        return cuentas;
    }

    // Elimina una cuenta según el índice indicado (si es válido)
    public boolean eliminarCuenta(int index) {
        if (index >= 0 && index < cuentas.size()) {
            cuentas.remove(index);
            return true; // Eliminación exitosa
        }
        return false; // Índice fuera de rango
    }

    // Realiza una transferencia restando el monto al saldo general.
    // También registra la transacción en el historial.
    public boolean transferencia(double monto, String bancoDestino) {
        if (monto > saldoGeneral) {
            return false; // No hay suficiente saldo
        }
        saldoGeneral -= monto;
        historial.push("Transferencia de " + df.format(monto) + " a " + bancoDestino);
        return true; // Transferencia exitosa
    }

    // Retorna el historial de transacciones
    public Stack<String> getHistorial() {
        return historial;
    }
}


