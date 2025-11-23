import java.util.*;
import java.text.DecimalFormat;

public class Menu {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        DecimalFormat df = new DecimalFormat("#,###");
        ArrayList<Cuenta_2> listaCuentas = new ArrayList<>();
        Stack<String> historialTransferencias = new Stack<>();

        int contrasena = 615;
        int intento;
        double saldo = 15000000;
        int opcion = 0;

        //Este el el login al banco
        do {
            System.out.println("Ingrese la contraseña:");
            String input = scanner.nextLine();

            try {
                intento = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                intento = -1;
            }

            if (intento == contrasena) {
                System.out.println("Acceso concedido.");
            } else {
                System.out.println("Acceso denegado. Intente de nuevo.");
            }

        } while (intento != contrasena);

        // Este es el menú principal del banco
        do {
            System.out.println("Bienvenido al menú principal de Visa.");
            System.out.println("1. Consultar saldo");
            System.out.println("2. Consignar dinero");
            System.out.println("3. Retirar saldo");
            System.out.println("4. Crear nueva cuenta");
            System.out.println("5. Eliminar cuenta");
            System.out.println("6. Ver cuentas creadas");
            System.out.println("7. Datos personales");
            System.out.println("8. Transacciones");
            System.out.println("9. Historial de transacciones");
            System.out.println("10. Salir");
            System.out.print("Seleccione una opción: ");

            try {
                opcion = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                opcion = -1;
            }

            //Aca comienza el comando se switch para el menú
            switch (opcion) {

                case 1:
                    System.out.println("Su saldo es: " + df.format(saldo));
                    break;

                case 2:
                    System.out.print("Escriba el monto que desea consignar: ");

                    try {
                        double monto = Double.parseDouble(scanner.nextLine());
                        if (monto <= 0) {
                            System.out.println("El valor debe ser mayor a 0.");
                        } else {
                            saldo += monto;
                            System.out.println("Se consignó " + df.format(monto) +
                                    ". Nuevo saldo: " + df.format(saldo));
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Error: ingrese un número válido.");
                    }
                    break;

                case 3:
                    System.out.println("Dime el monto que deseas retirar (0 para cancelar):");
                    try {
                        double montoRetirar = Double.parseDouble(scanner.nextLine());

                        if (montoRetirar == 0) {
                            break;
                        } else if (montoRetirar < 0) {
                            System.out.println("Monto inválido.");
                        } else if (montoRetirar > saldo) {
                            System.out.println("Saldo insuficiente.");
                        } else {
                            saldo -= montoRetirar;
                            System.out.println("Retiro exitoso. Nuevo saldo: " + df.format(saldo));
                        }

                    } catch (NumberFormatException e) {
                        System.out.println("Error: ingrese un número válido.");
                    }
                    break;

                case 4:
                    System.out.println("Creando una nueva cuenta…");
                    System.out.print("Digite su nombre: ");
                    String nombre = scanner.nextLine();
                    System.out.print("Digite su apellido: ");
                    String apellido = scanner.nextLine();

                    System.out.print("Ingrese su saldo inicial: ");
                    double saldoInicial;

                    try {
                        saldoInicial = Double.parseDouble(scanner.nextLine());
                    } catch (NumberFormatException e) {
                        saldoInicial = 0;
                    }

                    Cuenta_2 nueva = new Cuenta_2(nombre, apellido, saldoInicial);
                    listaCuentas.add(nueva);

                    System.out.println("La cuenta ha sido creada con éxito.");
                    break;

                case 5:
                    if (listaCuentas.isEmpty()) {
                        System.out.println("No hay cuentas para eliminar.");
                        break;
                    }

                    System.out.println("Seleccione la cuenta a eliminar:");
                    for (int i = 0; i < listaCuentas.size(); i++) {
                        System.out.println((i + 1) + ". " +
                                listaCuentas.get(i).getNombre() + " " +
                                listaCuentas.get(i).getApellido());
                    }

                    try {
                        int eliminar = Integer.parseInt(scanner.nextLine());
                        if (eliminar > 0 && eliminar <= listaCuentas.size()) {
                            listaCuentas.remove(eliminar - 1);
                            System.out.println("Cuenta eliminada con éxito.");
                        } else {
                            System.out.println("Número inválido.");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Error: ingrese un número válido.");
                    }
                    break;

                case 6:
                    System.out.println("Cuentas registradas:");
                    if (listaCuentas.isEmpty()) {
                        System.out.println("No hay cuentas registradas aún.");
                    } else {
                        for (int i = 0; i < listaCuentas.size(); i++) {
                            Cuenta_2 c = listaCuentas.get(i);
                            System.out.println((i + 1) + ". " + c.getNombre() + " " +
                                    c.getApellido() + " | Saldo: $" + df.format(c.getSaldo()));
                        }
                    }
                    break;

                case 7:
                    System.out.println("Ingrese sus datos personales:");
                    System.out.print("Nombre: ");
                    String nombre1 = scanner.nextLine();
                    System.out.print("Apellido: ");
                    String apellido1 = scanner.nextLine();

                    System.out.print("Cédula: ");
                    int cedula = Integer.parseInt(scanner.nextLine());

                    System.out.print("Dirección: ");
                    String direccion = scanner.nextLine();

                    System.out.println("Información personal registrada:");
                    System.out.println("Nombre: " + nombre1 +
                            ", Apellido: " + apellido1 +
                            ", Cédula: " + cedula +
                            ", Dirección: " + direccion);
                    break;

                case 8:
                    System.out.println("Que monto desea consignar? ");
                    try{
                        double montoTransaccion = Integer.parseInt(scanner.nextLine());
                        System.out.println("Ahora escriba a que banco se realiza la transferencia: ");
                        String banco = scanner.nextLine();
                        if (montoTransaccion <= saldo) {
                            System.out.println("El valor debe ser mayor a 0.");
                        } else if (montoTransaccion > saldo) {
                            System.out.println("Saldo insuficiente para realizar esta transferencia.");
                        } else {
                            saldo -= montoTransaccion;
                            String registro = "Transferencia: " + df.format(montoTransaccion) + " -> Banco: " + banco;
                            historialTransferencias.push(registro);
                            System.out.println("El monto que se consignó fué: " + df.format(montoTransaccion) +
                                    " al banco " + banco + " y el nuevo saldo es de: " + df.format(saldo));
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Por favor ingrese un numero valido");
                    }
                    break;


                case 9:
                    System.out.println("El historial de transferencias es: ");

                    if (historialTransferencias.isEmpty()) {
                        System.out.println("No hay transferencias registradas.");
                    } else {

                        for (String movimiento : historialTransferencias) {
                            System.out.println(movimiento);
                        }
                    }
                    break;

                case 10:
                    System.out.println("Espero que vuelvas pronto. ");
                    System.out.println("Que tengas buen dia. ");

                default:
                    System.out.println("Opción inválida.");
            }

        } while (opcion != 10);

        System.out.println("Espero que vuelva pronto.");
    }
}
