package interfaces;

import java.util.ArrayList;

import modelo.Retiro;

public interface RetiroInterface {

	public int registrar(Retiro r);

	public int eliminar(int codigo, int codC);

	public ArrayList<Retiro> listado();

	public ArrayList<Retiro> buscarXCodigo(int codigo);
	

}
