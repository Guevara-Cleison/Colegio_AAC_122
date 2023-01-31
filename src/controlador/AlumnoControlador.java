package controlador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import interfaces.AlumnoInterface;
import modelo.Alumno;
import util.MySQLConexion;

public class AlumnoControlador implements AlumnoInterface {

	@Override
	public int registrar(Alumno a) {
		int resultado = 0;
		Connection conexion = null;
		PreparedStatement pst = null;
		try {
			conexion = MySQLConexion.conectar();// ESTABLECER_CONEXION
			// SENTENCIA_SQL
			String sql = "call registrar_alumno (?,?,?,?,?,?,?)";
			// preparar_la_sentencia_sql
			pst = conexion.prepareStatement(sql);
			// comienza_desde_el_1
			// falta_el_codigo_correlativo
			pst.setInt(1, a.getId());
			pst.setString(2, a.getNombres());
			pst.setString(3, a.getApellidos());
			pst.setString(4, a.getDni());
			pst.setString(5, a.getEdad());
			pst.setString(6, a.getCelular());
			pst.setString(7, a.getEstado());
			resultado = pst.executeUpdate();

		} catch (Exception e) {
			System.out.println("Error en la sentencia-registrar Alumno " + e.getMessage());
		} finally {
			try {
				if (pst != null) {
					pst.close();
				}
				if (conexion != null) {
					conexion.close();
				}
			} catch (SQLException e2) {
				System.out.println("Error al cerrar la BD " + e2.getMessage());
			}
		}
		return resultado;
	}

	@Override
	public int eliminar(int codigo) {
		int valor = 0;
		Connection conexion = null;
		PreparedStatement pst = null;
		try {
			// Establececr_conexion
			conexion = MySQLConexion.conectar();
			// sentencia_sql
			String sql = "call eliminar_alumno(?)";
			// preparar_la_sentencia_sql
			pst = conexion.prepareStatement(sql);
			//
			pst.setInt(1, codigo);
			//
			valor = pst.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error en la Sentencia-eliminar Alumno " + e.getMessage());
		} finally {
			try {
				if (pst != null) {
					pst.close();
				}
				if (conexion != null) {
					conexion.close();
				}
			} catch (SQLException e2) {
				System.out.println("Error al cerrar la BD " + e2.getMessage());
			}
		}
		return valor;
	}

	@Override
	public ArrayList<Alumno> listado() {
		// METODO_RETORNA_UN_ARRAY_DE_ALUMNOS_DE_LA_BD
		// Creamno_el_array
		ArrayList<Alumno> lista = new ArrayList<Alumno>();
		// Conexion_
		Connection conexion = null;
		PreparedStatement pst = null;
		ResultSet resultado = null;
		try {
			// establecemos_conexion
			conexion = MySQLConexion.conectar();
			// Sentencia_SQL
			String sql = "call lista_alumnos()";
			// preparamos_la_sentencia
			pst = conexion.prepareStatement(sql);
			// ejecutamos_sentencia
			resultado = pst.executeQuery();
			while (resultado.next()) {
				Alumno a = new Alumno(resultado.getInt(1), resultado.getString(2), resultado.getString(3),
						resultado.getString(4), resultado.getString(5), resultado.getString(6), resultado.getString(7));
				// agregamos_al_array
				lista.add(a);
			}

		} catch (Exception e) {
			System.out.println("Error-Instruccion SQL- Listar Alumno " + e.getMessage());
		} finally {

			try {
				if (resultado != null)
					resultado.close();
				if (pst != null)
					pst.close();
				if (conexion != null)
					conexion.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println("Error al cerrar la BD" + e.getMessage());
			}
		}
		return lista;
	}

	@Override
	public int actualizar(Alumno a) {
		int valor = 0;
		Connection conexion = null;
		PreparedStatement pst = null;
		try {
			// establecer_conexion
			conexion = MySQLConexion.conectar();
			// no_se_puede_cambiar_ni_codigo, ni_estado, porque_es_automatico
			// sentencia_sql
			String sql = "call actualizar_alumno(?,?,?,?,?,?)";
			// preparamos_sentencia
			pst = conexion.prepareStatement(sql);
			// pasamos_parametros
			pst.setString(1, a.getNombres());
			pst.setString(2, a.getApellidos());
			pst.setString(3, a.getDni());
			pst.setString(4, a.getEdad());
			pst.setString(5, a.getCelular());
			pst.setInt(6, a.getId());
			// ejecutamos_sentencia
			valor = pst.executeUpdate();
		} catch (Exception e) {
			System.out.println("Error-Instruccion SQL-Actualizar Alumno " + e.getMessage());
		} finally {
			try {
				if (pst != null)
					pst.close();
				if (conexion != null)
					conexion.close();
			} catch (Exception e2) {
				System.out.println("Error al cerrar la BD " + e2.getMessage());
			}
		}
		return valor;
	}

	@Override
	public ArrayList<Alumno> buscarXCodigo(int codigo) {

		// METODO_RETORNA_UN_ARRAY_DE_ALUMNOS_DE_LA_BD
		// Creamno_el_array
		ArrayList<Alumno> lista = new ArrayList<Alumno>();
		// Conexion_
		Connection conexion = null;
		PreparedStatement pst = null;
		ResultSet resultado = null;
		try {
			//_Conexion
			conexion = MySQLConexion.conectar();
			//Sentencia_SQL
			String sql="SELECT * FROM alumno WHERE id ="+codigo;
			//preparamos_la_sentencia
			pst=conexion.prepareStatement(sql);
			//pasamos_parametros
			//ejecutamos_sentencia
			resultado = pst.executeQuery();
			//
			while (resultado.next()) {
				Alumno a = new Alumno(resultado.getInt(1), resultado.getString(2), resultado.getString(3),
						resultado.getString(4), resultado.getString(5), resultado.getString(6), resultado.getString(7));
				// agregamos_al_array
				lista.add(a);
			}
		} catch (Exception e) {
			System.out.println("Error-Instruccion SQL- buscarXCodigo "+e.getMessage());
		}finally {
			
				try {
					if(resultado!=null)
						resultado.close();
					if(pst!=null)
						pst.close();
					if(conexion!=null)
						conexion.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					System.out.println("Error al cerrar la BD "+e.getMessage());
				}
		}
		return lista;
	}
	
	@Override
	public ArrayList<Alumno> listadoXEstado(String estado) {
		// METODO_RETORNA_UN_ARRAY_DE_ALUMNOS_DE_LA_BD
		// Creamno_el_array
		ArrayList<Alumno> lista = new ArrayList<Alumno>();
		// Conexion_
		Connection conexion = null;
		PreparedStatement pst = null;
		ResultSet resultado = null;
		try {
			// establecemos_conexion
			conexion = MySQLConexion.conectar();
			// Sentencia_SQL
			String sql = "SELECT * FROM alumno WHERE estado = '"+estado+"';";
			// preparamos_la_sentencia
			pst = conexion.prepareStatement(sql);
			// ejecutamos_sentencia
			resultado = pst.executeQuery();
			while (resultado.next()) {
				Alumno a = new Alumno(resultado.getInt(1), resultado.getString(2), resultado.getString(3),
						resultado.getString(4), resultado.getString(5), resultado.getString(6), resultado.getString(7));
				// agregamos_al_array
				lista.add(a);
			}

		} catch (Exception e) {
			System.out.println("Error-Instruccion SQL- Listar Alumno " + e.getMessage());
		} finally {

			try {
				if (resultado != null)
					resultado.close();
				if (pst != null)
					pst.close();
				if (conexion != null)
					conexion.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println("Error al cerrar la BD" + e.getMessage());
			}
		}
		return lista;
	}


}
