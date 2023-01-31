package interfaces;

import java.util.ArrayList;

import modelo.Curso;

public interface CursoInterface {

	public int registrar(Curso c);

	public int eliminar(int codigo);

	public ArrayList<Curso> listado();

	public int actualizar(Curso c);

	public ArrayList<Curso> buscarXCodigo(int codigo);
	

}
