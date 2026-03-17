package modelo;

public class UsuarioATM extends Usuario {

    private String pin;
    private String numeroTarjeta;
    private double saldo;

    public UsuarioATM(String nombre, String correo, String usuario, String contrasena,
                      String pin, String numeroTarjeta, double saldo) {
        super(nombre, correo, usuario, contrasena);
        this.pin = pin;
        this.numeroTarjeta = numeroTarjeta;
        this.saldo = saldo;
    }

    public double retirar(double monto) {
        if (monto > 0 && monto <= saldo) {
            saldo -= monto;
            return monto;
        }
        return 0;
    }

    public void consultarSaldo() {
        System.out.println("Saldo actual: " + saldo);
    }
}