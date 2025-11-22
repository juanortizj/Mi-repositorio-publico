public class Cuenta_2 {
    private String nombre;
    private String apellido;
    private double saldo;


    public Cuenta_2(String nombre, String apellido,double saldo, double saldoInicial) {
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
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }
}
