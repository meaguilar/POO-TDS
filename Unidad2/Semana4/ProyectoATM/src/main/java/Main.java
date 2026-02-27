import modelo.CreadorEmpleado;
import modelo.UsuarioATM;
import modelo.Usuario;
import modelo.CreadorUsuarioATM;

public class Main {

    public static void main(String[] args) {

        CreadorEmpleado creadorEmpleado = new CreadorUsuarioATM(
                "Ana Lopez",
                "ana@mail.com",
                "EMP001",
                "Gerente",
                "anaAdmin",
                "admin123"
        );

        Usuario cliente = creadorEmpleado.crearUsuario(
                "Juan Perez",
                "juan@mail.com",
                "juan123",
                "1234",
                "9999",
                "1234567890",
                1000.0
        );

        if (cliente.login("juan123", "1234")) {
            ((UsuarioATM) cliente).consultarSaldo();
            ((UsuarioATM) cliente).retirar(200);
            ((UsuarioATM) cliente).consultarSaldo();
        }

        creadorEmpleado.bloquearCuenta(cliente);

        System.out.println("Intento login cliente bloqueado: " +
                cliente.login("juan123", "1234"));
    }
}