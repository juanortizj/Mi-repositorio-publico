import java.util.*;
import java.text.DecimalFormat;

public class Menú {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        DecimalFormat df = new DecimalFormat("#,###");
        ArrayList<Cuenta_2> listaCuentas = new ArrayList<>();

        int contrasena = 615;
        int intento;
        int saldo = 15000000;
        int opcion = 0;



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


        do {
            System.out.println("Bienvenido al menú principal de Visa.");
            System.out.println("1. Consultar saldo");
            System.out.println("2. Consignar dinero");
            System.out.println("3. Retirar saldo");
            System.out.println("4. Crear nueva cuenta");
            System.out.println("5. Eliminar cuenta");
            System.out.println("6. Ver cuentas creadas");
            System.out.println("7. Datos personales");
            System.out.println("8. Salir");

            try {
                opcion = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                opcion = -1;
            }

            switch (opcion) {
                case 1:
                    System.out.println("Su saldo es: " + df.format(saldo));
                    System.out.println("Para volver al menú principal escriba volver (o cualquier tecla para continuar):");
                    scanner.nextLine();
                    break;

                case 2:
                     System.out.println("Escribe el monto que deseas consignar: ");
                     double montoConsignar = Double.parseDouble(scanner.nextLine());
                     if (montoConsignar <= 0) {
                         System.out.println("El valor debe ser mayor a 0.");
                     } else {
                         saldo += montoConsignar;
                         System.out.println("Se ha consignado " + df.format(montoConsignar) + ". Su saldo es de: " + df.format(saldo));
                     }
                     break;

                case 3:
                    boolean retiroExitoso = false;
                    do {
                        System.out.println("Dime el monto que deseas retirar (0 para cancelar):");
                        double montoRetirar = 0;
                        try {
                            montoRetirar = Double.parseDouble(scanner.nextLine());
                        } catch (NumberFormatException e) {
                            System.out.println("Entrada inválida.");
                            continue;
                        }
                        
                        if (montoRetirar == 0) break;

                        if (montoRetirar < 0) {
                            System.out.println("Monto inválido.");
                        } else if (montoRetirar > saldo) {
                            System.out.println("Saldo insuficiente.");
                        } else {
                            saldo -= montoRetirar;
                            System.out.println("Retiro exitoso. Nuevo saldo: " + df.format(saldo));
                            retiroExitoso = true;
                        }
                    } while (!retiroExitoso);
                    break;

                case 4:
                    System.out.println("En este momento está creando una nueva cuenta, por favor digite cierta información importante: ");
                    System.out.println("Digite su nombre:");
                    String nombre = scanner.nextLine();
                    System.out.println("Digite su apellido:");
                    String apellido = scanner.nextLine();

                    System.out.println("Ingrese su saldo inicial:");
                    double saldoInicial = 0;
                    try {
                        saldoInicial = Double.parseDouble(scanner.nextLine());
                    } catch (NumberFormatException e) {
                        saldoInicial = 0;
                    }

                    Cuenta_2 nuevaCuenta = new Cuenta_2(nombre, apellido, saldo, saldoInicial);

                    listaCuentas.add(nuevaCuenta);

                    System.out.println("La cuenta ha sido creada con éxito.");
                    break;

                case 5:
                    System.out.println("La cuenta ha sido eliminada con éxito.");
                    break;

                case 6:

                    System.out.println("En este espacio se mostraran las cuentas creadas:");
                    if (listaCuentas.isEmpty()) {
                        System.out.println("No hay cuentas registradas aún.");
                    } else {
                        for (int i = 0; i < listaCuentas.size(); i++) {
                            Cuenta_2 c = listaCuentas.get(i);
                            System.out.println((i + 1) + ". Titular: " + c.getNombre() + " " + c.getApellido() + 
                                               " | Saldo: $" + df.format(c.getSaldo()));
                        }
                    }
                    break;
                    
                case 8:
                    System.out.println("Saliendo...");
                    break;

                case 7:
                    System.out.println("Por favor digitar algunos datos personales: ");
                    System.out.println("Por favor escribir el nombre: ");
                    String nombre1 = scanner.nextLine();
                    System.out.println("Por favor escribir el apellido: ");
                    String apellido1 = scanner.nextLine();
                    System.out.println("Ahora por favor escriba su cedula de ciudadania: ");
                    int cedula1 = Integer.parseInt(scanner.nextLine());
                    System.out.println("Ahora escriba la dirección de su residencia: ");
                    String direccion = scanner.nextLine();
                    System.out.println("Información personal:");
                    System.out.println("El nombre es: " + nombre1 + ", el apellido es: " + apellido1 + ", su cedula de residencia es: " + cedula1 + " y la dirección es: " + direccion);
                default:
                    System.out.println("Opción inválida.");
            }

        } while (opcion != 8);
        System.out.println("Espero que vuelva pronto.");


    }
}