package Banco;

public class NodoCuenta {
    Cuenta cuenta;
    NodoCuenta izquierda;
    NodoCuenta derecha;

    public NodoCuenta(Cuenta cuenta) {
        this.cuenta = cuenta;
    }
}
