import modelo.UsuarioATM;

public class Main {

    public static void main(String[] args) {

        UsuarioATM usuario = new UsuarioATM(
                "Juan Perez",
                "juan@mail.com",
                "1234",
                "9876543210",
                1000.0
        );

        // Probar login
        if (usuario.login("Juan Perez", "juan@mail.com")) {
            System.out.println("Login exitoso");
        }

        // Retirar dinero
        double retirado = usuario.retirar(200);
        System.out.println("Monto retirado: " + retirado);
        System.out.println("Saldo actual: " + usuario.getSaldo());
    }
}