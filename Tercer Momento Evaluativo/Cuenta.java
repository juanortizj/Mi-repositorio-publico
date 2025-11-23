class Cuenta {
    private String numeroCuenta;
    private String titularCuenta;
    private double balance;

    public Cuenta(String numeroCuenta, String titularCuenta, double balance) {
        this.numeroCuenta = numeroCuenta;
        this.titularCuenta = titularCuenta;
        this.balance = balance;
    }

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public String getTitularCuenta() {
        return titularCuenta;
    }

    public double getBalance() {
        return balance;
    }

    public void setnumeroCuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    public void setTitularCuenta(String titularCuenta) {
        this.titularCuenta = titularCuenta;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    //---------------------------------------------------------------
    public void depositar(double montoDinero) {
        if (montoDinero > 0) {
            balance += montoDinero;
        }
    }

    public boolean retirarDinero(double montoDinero) {
        if (montoDinero > 0 && balance >= montoDinero) {
            balance -= montoDinero;
            return true;
        }
        return false;
    }
}