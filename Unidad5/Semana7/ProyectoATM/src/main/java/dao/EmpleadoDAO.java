package dao;
import patrones.factoria.CreadorEmpleado;

import java.util.List;

public interface EmpleadoDAO {
    void guardar(CreadorEmpleado empleado);
    void actualizar(CreadorEmpleado empleado);
    void eliminar(String codigo);
    CreadorEmpleado buscarPorCodigo(String codigo);
    List<CreadorEmpleado> listarTodos();
}
