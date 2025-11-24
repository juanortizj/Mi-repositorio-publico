package Banco;

public class ArbolCuentas {

    private NodoCuenta raiz;

    // Insertar en el árbol
    public void insertar(Cuenta cuenta) {
        raiz = insertarRec(raiz, cuenta);
    }

    private NodoCuenta insertarRec(NodoCuenta nodo, Cuenta cuenta) {
        if (nodo == null) return new NodoCuenta(cuenta);

        String claveNueva = cuenta.getNombre() + cuenta.getApellido();
        String claveActual = nodo.cuenta.getNombre() + nodo.cuenta.getApellido();

        if (claveNueva.compareToIgnoreCase(claveActual) < 0)
            nodo.izquierda = insertarRec(nodo.izquierda, cuenta);
        else
            nodo.derecha = insertarRec(nodo.derecha, cuenta);

        return nodo;
    }

    // Buscar cuenta
    public Cuenta buscar(String nombre, String apellido) {
        return buscarRec(raiz, nombre + apellido);
    }

    private Cuenta buscarRec(NodoCuenta nodo, String clave) {
        if (nodo == null) return null;

        String claveActual = nodo.cuenta.getNombre() + nodo.cuenta.getApellido();

        if (clave.equalsIgnoreCase(claveActual))
            return nodo.cuenta;

        if (clave.compareToIgnoreCase(claveActual) < 0)
            return buscarRec(nodo.izquierda, clave);

        return buscarRec(nodo.derecha, clave);
    }

    // Mostrar cuentas ordenadas alfabéticamente
    public void imprimirEnOrden() {
        imprimirRec(raiz);
    }

    private void imprimirRec(NodoCuenta nodo) {
        if (nodo != null) {
            imprimirRec(nodo.izquierda);
            System.out.println(nodo.cuenta.getNombre() + " " + nodo.cuenta.getApellido());
            imprimirRec(nodo.derecha);
        }
    }
}
