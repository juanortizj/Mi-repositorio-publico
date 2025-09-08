import java.util.Scanner;

class Paquete {
    private String tipo;
    private String fecha;

    public Paquete(String tipo, String fecha) {
        this.tipo = tipo;
        this.fecha = fecha;
    }

    public String getTipo() {
        return tipo;
    }

    public String getFecha() {
        return fecha;
    }

    public String toString() {
        return tipo + " | Fecha: " + fecha;
    }
}

class Casilleros {
    private Paquete[] casilleros = new Paquete[16];
    private Scanner sc = new Scanner(System.in);

    public void menu() {
        int opcion;

        do {
            System.out.println("\n-- Menú Principal Casillero --");
            System.out.println("1. Registrar paquete");
            System.out.println("2. Ver casilleros disponibles");
            System.out.println("3. Consultar casillero");
            System.out.println("4. Salir");
            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1 -> registrarPaquete();
                case 2 -> mostrarDisponibles();
                case 3 -> infoCasillero();
                case 4 -> System.out.println("Muchas gracias por utilizar nuestro software, esperamos que vuelva pronto.");
                default -> System.out.println("Opción incorrecta, vuelva a intentar. ");
            }
        } while (opcion != 4);
    }

    private void registrarPaquete() {
        System.out.println("\n--- Registrar paquete ---");
        mostrarDisponibles();

        System.out.print("Ingrese número de casillero (0-15): ");
        int pos = sc.nextInt();
        sc.nextLine();

        if (pos < 0 || pos >= 16) {
            System.out.println("Este casillero no existe.");
            return;
        }

        if (casilleros[pos] != null) {
            System.out.println("Este casillero ya está ocupado. ");
            return;
        }

        System.out.print("Tipo de paquete (Grande/Mediano/Pequeño): ");
        String tipo = sc.nextLine();

        System.out.print("Fecha (dd/mm/aaaa): ");
        String fecha = sc.nextLine();

        casilleros[pos] = new Paquete(tipo, fecha);
        System.out.println("Paquete guardado en el casillero " + pos);
    }

    // Mostrar los casilleros vacíos
    private void mostrarDisponibles() {
        System.out.println("\n--- Casilleros disponibles ---");
        boolean hay = false;
        for (int i = 0; i < casilleros.length; i++) {
            if (casilleros[i] == null) {
                System.out.println("Casillero " + i + " → Libre");
                hay = true;
            }
        }
        if (!hay) {
            System.out.println("No hay casilleros disponibles. ");
        }
    }

    private void infoCasillero() {
        System.out.print("Ingrese número del casillero (0-15): ");
        int pos = sc.nextInt();
        sc.nextLine();

        if (pos < 0 || pos >= 16) {
            System.out.println("Este casillero no existe. ");
            return;
        }

        if (casilleros[pos] == null) {
            System.out.println("El casillero está vacío.");
        } else {
            System.out.println("Casillero " + pos + " → " + casilleros[pos]);
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Casilleros app = new Casilleros();
        app.menu();
    }
}