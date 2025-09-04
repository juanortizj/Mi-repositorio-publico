import java.util.Scanner;

class Casilleros {

    private String[] puestos = new String[16];
    private int[] tiempos = new int[16];
    private Scanner scanner = new Scanner(System.in);

    public void menu() {
        int opcion;

        do {
            System.out.println("\nRegistrador Paquetes");
            System.out.println("1. Registrar paquete");
            System.out.println("2. Consultar Casilleros disponibles");
            System.out.println("3. Información de casillero");
            System.out.println("4. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    registrarPaquetes();
                    break;
                case 2:
                    mostrarPuestosDisponibles();
                    break;
                case 3:
                    informacionCasillero();
                    break;
                case 4:
                    System.out.println("Saliendo del sistema...");
                    break;
                default:
                    System.out.println("Opción no válida, intente de nuevo.");
                    break;
            }
        } while (opcion != 4);
    }

    private void registrarPaquetes() {
        System.out.println("\n--Registrar paquetes--");
        mostrarPuestosDisponibles();
        System.out.println(
            "Ingrese el puesto (0 - 15) en el cual quiere colocar su paquete,\n" +
            "los primeros 4 casilleros son para paquetería grande (42 × 35 × 32 cm),\n" +
            "los siguientes 4 casilleros son para la paquetería mediana (40,6 × 30,5 × 35,6 cm),\n" +
            "y los 8 casilleros restantes son para la paquetería pequeña (20 × 15 × 10 cm).\n"
        );
        int puesto = scanner.nextInt();
        scanner.nextLine();

        if (puesto < 0 || puesto >= 16) {
            System.out.println("Puesto inválido.");
            return;
        }

        if (puestos[puesto] != null) {
            System.out.println(" Ese puesto ya está ocupado.");
            return;
        }

        System.out.print("Ingrese el tipo de paquete (Grande/Mediano/Pequeño): ");
        String tamanio = scanner.nextLine();


        System.out.print("Ingrese la fecha actual en números: ");
        String fecha = scanner.nextLine();
        scanner.nextLine();

        puestos[puesto] = tamanio + " | Fecha: " + fecha;
        System.out.println("✅ Paquete registrado en el puesto " + puesto);
    }

    private void mostrarPuestosDisponibles() {
        System.out.println("\n--Casilleros disponibles--");
        for (int i = 0; i < 16; i++) {
            if (puestos[i] == null) {
                System.out.println("Puesto " + i + " → Disponible");
            }
        }
    }

    private void informacionCasillero() {
        System.out.print("Ingrese el número del casillero (0 - 15): ");
        int puesto = scanner.nextInt();
        scanner.nextLine();

        if (puesto < 0 || puesto >= 16) {
            System.out.println("Puesto inválido.");
            return;
        }

        if (puestos[puesto] == null) {
            System.out.println("Casillero vacío.");
        } else {
            System.out.println("Casillero " + puesto +
                               "  Paquete: " + puestos[puesto] +
                               ", Fecha: " + tiempos[puesto]);
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Casilleros casillero = new Casilleros();
        casillero.menu();
    }
}