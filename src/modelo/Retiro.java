package modelo;

public class Retiro {

	private int numReti, numMatricula;
	private String fecha, hora;

	public Retiro() {
	}

	public Retiro(int numReti, int numMatricula, String fecha, String hora) {
		this.numReti = numReti;
		this.numMatricula = numMatricula;
		this.fecha = fecha;
		this.hora = hora;
	}

	public int getNumReti() {
		return numReti;
	}

	public void setNumReti(int numReti) {
		this.numReti = numReti;
	}

	public int getNumMatricula() {
		return numMatricula;
	}

	public void setNumMatricula(int numMatricula) {
		this.numMatricula = numMatricula;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getHora() {
		return hora;
	}

	public void setHora(String hora) {
		this.hora = hora;
	}
	
}
