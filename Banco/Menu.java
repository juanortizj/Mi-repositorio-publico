package Banco;

import java.util.Scanner;

public class Menu {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Banco banco = new Banco();

        System.out.println("Bienvenido al banco");

        // --- LOGIN ---
        login(scanner);

        int opcion;
        boolean continuar = true;

        while (continuar) {
            mostrarMenuOpciones();
            opcion = leerEntero(scanner);

            switch (opcion) {
                case 1 -> System.out.println("Saldo: " + banco.consultarSaldo());
                case 2 -> menuConsignar(scanner, banco);
                case 3 -> menuRetirar(scanner, banco);
                case 4 -> menuCrearCuenta(scanner, banco);
                case 5 -> menuEliminarCuenta(scanner, banco);
                case 6 -> mostrarCuentas(banco);
                case 7 -> ingresarDatosPersonales(scanner);
                case 8 -> menuTransferencia(scanner, banco);
                case 9 -> mostrarHistorial(banco);
                case 10 -> continuar = false;
                default -> System.out.println("Opción inválida.");
            }

            if (continuar) {
                System.out.println("¿Desea continuar? (si/no)");
                continuar = !scanner.nextLine().equalsIgnoreCase("no");
            }
        }

        System.out.println("Gracias por usar el banco.");
    }
    private static void login(Scanner scanner) {
    int intento = -1;
    int clave = 615;

    while (intento != clave) {
        System.out.println("Ingrese la contraseña:");
        intento = leerEntero(scanner);

        if (intento != clave)
            System.out.println("Acceso denegado.");
    }

    System.out.println("Acceso concedido.");
}

private static int leerEntero(Scanner scanner) {
    try {
        return Integer.parseInt(scanner.nextLine());
    } catch (NumberFormatException e) {
        return -1;
    }
}

private static void mostrarMenuOpciones() {
    System.out.println("""
            
            --- MENÚ PRINCIPAL ---
            1. Consultar saldo
            2. Consignar
            3. Retirar
            4. Crear cuenta
            5. Eliminar cuenta
            6. Ver cuentas
            7. Datos personales
            8. Realizar transferencia
            9. Historial de movimientos
            10. Salir
            """);
}

private static void menuConsignar(Scanner scanner, Banco banco) {
    System.out.println("Monto a consignar:");
    double monto = Double.parseDouble(scanner.nextLine());
    if (monto > 0) banco.consignar(monto);
}

private static void menuRetirar(Scanner scanner, Banco banco) {
    System.out.println("Monto a retirar:");
    double monto = Double.parseDouble(scanner.nextLine());
    if (!banco.retirar(monto))
        System.out.println("Saldo insuficiente.");
}

private static void menuCrearCuenta(Scanner scanner, Banco banco) {
    System.out.print("Nombre: ");
    String nombre = scanner.nextLine();
    System.out.print("Apellido: ");
    String apellido = scanner.nextLine();
    System.out.print("Saldo inicial: ");
    double saldo = Double.parseDouble(scanner.nextLine());
    banco.crearCuenta(nombre, apellido, saldo);
}

private static void menuEliminarCuenta(Scanner scanner, Banco banco) {
    mostrarCuentas(banco);
    System.out.println("Número de la cuenta a eliminar: ");
    int index = leerEntero(scanner) - 1;

    if (!banco.eliminarCuenta(index))
        System.out.println("Índice inválido.");
}

private static void mostrarCuentas(Banco banco) {
    if (banco.getCuentas().isEmpty()) {
        System.out.println("No hay cuentas.");
        return;
    }

    int i = 1;
    for (var c : banco.getCuentas()) {
        System.out.println(i++ + ". " + c.getNombre() + " " + c.getApellido());
    }
}

private static void ingresarDatosPersonales(Scanner scanner) {
    System.out.println("Función aún separada en el menú.");
}

private static void menuTransferencia(Scanner scanner, Banco banco) {
    System.out.println("Monto:");
    double monto = Double.parseDouble(scanner.nextLine());
    System.out.println("Banco destino:");
    String bancoDestino = scanner.nextLine();

    if (!banco.transferencia(monto, bancoDestino))
        System.out.println("No fue posible realizar la transferencia.");
}

private static void mostrarHistorial(Banco banco) {
    if (banco.getHistorial().isEmpty()) {
        System.out.println("No hay transacciones.");
        return;
    }

    banco.getHistorial().forEach(System.out::println);
}
}

