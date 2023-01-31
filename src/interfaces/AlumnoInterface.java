package interfaces;

import java.util.ArrayList;

import modelo.Alumno;

public interface AlumnoInterface {

	public int registrar(Alumno a);

	public int eliminar(int codigo);

	public ArrayList<Alumno> listado();

	public int actualizar(Alumno a);

	public ArrayList<Alumno> buscarXCodigo(int codigo);
	
	public ArrayList<Alumno> listadoXEstado(String estado);

}
