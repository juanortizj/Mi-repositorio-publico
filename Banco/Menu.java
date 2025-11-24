package Banco;

import java.util.Scanner;

public class Menu {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);   // Para leer entradas del usuario
        Banco banco = new Banco();                  // Instancia del banco con sus funciones

        System.out.println("Bienvenido al banco");

        // --- LOGIN ---
        login(scanner);  // Método de validación de contraseña antes de usar el menú

        int opcion;
        boolean continuar = true;

        // --- MENÚ PRINCIPAL ---
        while (continuar) {
            mostrarMenuOpciones();         // Muestra el menú en pantalla
            opcion = leerEntero(scanner);  // Lee la opción ingresada

            // Switch con expresiones "->" de Java 14+
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
                case 10 -> continuar = false;      // Finaliza el programa
                default -> System.out.println("Opción inválida.");
            }

            // Pregunta si se desea seguir usando el sistema
            if (continuar) {
                System.out.println("¿Desea continuar? (si/no)");
                continuar = !scanner.nextLine().equalsIgnoreCase("no");
            }
        }

        System.out.println("Gracias por usar el banco.");
    }

    // --- LOGIN ---
    private static void login(Scanner scanner) {
        int intento = -1;
        int clave = 615;    // Contraseña fija para acceder

        // Solicita contraseña hasta que coincida
        while (intento != clave) {
            System.out.println("Ingrese la contraseña:");
            intento = leerEntero(scanner);

            if (intento != clave)
                System.out.println("Acceso denegado.");
        }

        System.out.println("Acceso concedido.");
    }

    // Lee un número entero con control de error
    private static int leerEntero(Scanner scanner) {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1; // Valor inválido
        }
    }

    // Muestra el menú principal
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

    // --- OPCIÓN CONSIGNAR ---
    private static void menuConsignar(Scanner scanner, Banco banco) {
        System.out.println("Monto a consignar:");
        double monto = Double.parseDouble(scanner.nextLine());
        if (monto > 0) banco.consignar(monto); // Solo consigna montos positivos
    }

    // --- OPCIÓN RETIRAR ---
    private static void menuRetirar(Scanner scanner, Banco banco) {
        System.out.println("Monto a retirar:");
        double monto = Double.parseDouble(scanner.nextLine());
        if (!banco.retirar(monto))
            System.out.println("Saldo insuficiente.");
    }

    // --- CREACIÓN DE CUENTA ---
    private static void menuCrearCuenta(Scanner scanner, Banco banco) {
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Apellido: ");
        String apellido = scanner.nextLine();
        System.out.print("Saldo inicial: ");
        double saldo = Double.parseDouble(scanner.nextLine());

        banco.crearCuenta(nombre, apellido, saldo);
    }

    // --- ELIMINAR CUENTA ---
    private static void menuEliminarCuenta(Scanner scanner, Banco banco) {
        mostrarCuentas(banco); // Mostrar lista antes de eliminar
        System.out.println("Número de la cuenta a eliminar: ");
        int index = leerEntero(scanner) - 1; // Ajuste porque las listas empiezan en 0

        if (!banco.eliminarCuenta(index))
            System.out.println("Índice inválido.");
    }

    // --- MOSTRAR CUENTAS ---
    private static void mostrarCuentas(Banco banco) {
        if (banco.getCuentas().isEmpty()) {
            System.out.println("No hay cuentas.");
            return;
        }

        int i = 1;
        for (var c : banco.getCuentas()) {
            // Se imprimen las cuentas con número empezando en 1
            System.out.println(i++ + ". " + c.getNombre() + " " + c.getApellido());
        }
    }

    // --- OPCIÓN “DATOS PERSONALES” (aún no implementada) ---
    private static void ingresarDatosPersonales(Scanner scanner) {
        System.out.println("Función aún separada en el menú.");
    }

    // --- REALIZAR TRANSFERENCIA ---
    private static void menuTransferencia(Scanner scanner, Banco banco) {
        System.out.println("Monto:");
        double monto = Double.parseDouble(scanner.nextLine());
        System.out.println("Banco destino:");
        String bancoDestino = scanner.nextLine();

        if (!banco.transferencia(monto, bancoDestino))
            System.out.println("No fue posible realizar la transferencia.");
    }

    // --- MOSTRAR HISTORIAL DE MOVIMIENTOS ---
    private static void mostrarHistorial(Banco banco) {
        if (banco.getHistorial().isEmpty()) {
            System.out.println("No hay transacciones.");
            return;
        }

        banco.getHistorial().forEach(System.out::println); // Imprime cada registro
    }
}
