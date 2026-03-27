package modelo;

public class UsuarioATM extends Usuario {

    private String pin;
    private String numeroTarjeta;
    private double saldo;
    // Nuevo atributo
    private String codigoEmpleado;

    public UsuarioATM(String nombre, String correo, String usuario, String contrasena,
                      String pin, String numeroTarjeta, double saldo, String codigoEmpleado) {
        super(nombre, correo, usuario, contrasena);
        this.pin = pin;
        this.numeroTarjeta = numeroTarjeta;
        this.saldo = saldo;
        this.codigoEmpleado = codigoEmpleado;

    }

    public String getPin() {
        return pin;
    }
    public String getNumeroTarjeta() {
        return numeroTarjeta;
    }
    public double getSaldo() {
        return saldo;
    }
    public String getCodigoEmpleado() {
        return codigoEmpleado;
    }
    public void setCodigoEmpleado(String codigoEmpleado) {
        this.codigoEmpleado = codigoEmpleado;
    }

    public double retirar(double monto) {
        if (monto > 0 && monto <= saldo) {
            saldo -= monto;
            return monto;
        }
        return 0;
    }

    public void consultarSaldo() {
        System.out.println("Saldo actual: " + getSaldo());
    }
}