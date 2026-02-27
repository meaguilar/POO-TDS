import modelo.Empleado;
import modelo.UsuarioATM;

public class Main {

    public static void main(String[] args) {

        UsuarioATM cliente = new UsuarioATM(
                "Juan Perez",
                "juan@mail.com",
                "juan123",
                "1234",
                "9999",
                "1234567890",
                1000.0
        );

        if (cliente.login("juan123", "1234")) {
            cliente.consultarSaldo();
            cliente.retirar(200);
            cliente.consultarSaldo();
        }

        Empleado empleado = new Empleado(
                "Ana Lopez",
                "ana@mail.com",
                "EMP001",
                "Gerente",
                "anaAdmin",
                "admin123"
        );

        System.out.println("Login empleado: " +
                empleado.login("anaAdmin", "admin123"));
    }
}