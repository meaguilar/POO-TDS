package modelo;

public class UsuarioATM extends Usuario {

    private String pin;
    private String numeroTarjeta;
    private double saldo;

    public UsuarioATM(String nombre, String correo,
                      String pin, String numeroTarjeta, double saldoInicial) {
        super(nombre, correo);
        this.pin = pin;
        this.numeroTarjeta = numeroTarjeta;
        this.saldo = saldoInicial;
    }

    public double retirar(double monto) {
        if (monto > 0 && monto <= saldo) {
            saldo -= monto;
            return monto;
        }
        return 0;
    }

    // Getters
    public String getNumeroTarjeta() {
        return numeroTarjeta;
    }

    public double getSaldo() {
        return saldo;
    }
}