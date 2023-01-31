package interfaces;

import java.util.ArrayList;

import modelo.Matricula;

public interface MatriculaInterface {

	public int registrar(Matricula m);

	public int eliminar(int codigo, int codA);

	public ArrayList<Matricula> listado();

	public int actualizar(Matricula m);

	public ArrayList<Matricula> buscarXCodigo(int codigo);
	
	public ArrayList<Matricula> buscarCodAluXdni(int dni);
	
	public ArrayList<Matricula> buscarXCodAlu(int codigo);
	

}
