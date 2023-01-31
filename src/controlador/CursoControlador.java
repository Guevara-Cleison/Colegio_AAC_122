package controlador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import interfaces.CursoInterface;
import modelo.Curso;
import util.MySQLConexion;

public class CursoControlador implements CursoInterface {

	@Override
	public int registrar(Curso c) {
		int resultado = 0;
		Connection conexion = null;
		PreparedStatement pst = null;
		try {
			conexion = MySQLConexion.conectar();// ESTABLECER_CONEXION
			// SENTENCIA_SQL
			String sql = "call registrar_curso (?,?,?,?,?)";
			// preparar_la_sentencia_sql
			pst = conexion.prepareStatement(sql);
			// comienza_desde_el_1
			pst.setInt(1, c.getId());
			pst.setString(2, c.getAsignatura());
			pst.setString(3, c.getCiclo());
			pst.setString(4, c.getCreditos());
			pst.setString(5, c.getHoras());
			resultado = pst.executeUpdate();

		} catch (Exception e) {
			System.out.println("Error en la sentencia-registrar Curso " + e.getMessage());
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
			String sql = "call eliminar_curso(?)";
			// preparar_la_sentencia_sql
			pst = conexion.prepareStatement(sql);
			//
			pst.setInt(1, codigo);
			//
			valor = pst.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error en la Sentencia-eliminar Curso " + e.getMessage());
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
	public ArrayList<Curso> listado() {
		// METODO_RETORNA_UN_ARRAY_DE_ALUMNOS_DE_LA_BD
		// Creamno_el_array
		ArrayList<Curso> lista = new ArrayList<Curso>();
		// Conexion_
		Connection conexion = null;
		PreparedStatement pst = null;
		ResultSet resultado = null;
		try {
			// establecemos_conexion
			conexion = MySQLConexion.conectar();
			// Sentencia_SQL
			String sql = "call lista_curso()";
			// preparamos_la_sentencia
			pst = conexion.prepareStatement(sql);
			// ejecutamos_sentencia
			resultado = pst.executeQuery();
			while (resultado.next()) {
				Curso c = new Curso(resultado.getInt(1), resultado.getString(2), resultado.getString(3),
						resultado.getString(4), resultado.getString(5));
				// agregamos_al_array
				lista.add(c);
			}

		} catch (Exception e) {
			System.out.println("Error-Instruccion SQL- Listar Curso " + e.getMessage());
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
	public int actualizar(Curso c) {
		int valor = 0;
		Connection conexion = null;
		PreparedStatement pst = null;
		try {
			// establecer_conexion
			conexion = MySQLConexion.conectar();
			// no_se_puede_cambiar_ni_codigo, ni_estado, porque_es_automatico
			// sentencia_sql
			String sql = "call actualizar_curso(?,?,?,?,?)";
			// preparamos_sentencia
			pst = conexion.prepareStatement(sql);
			// pasamos_parametros
			pst.setString(1, c.getAsignatura());
			pst.setString(2, c.getCiclo());
			pst.setString(3, c.getCreditos());
			pst.setString(4, c.getHoras());
			pst.setInt(5, c.getId());
			// ejecutamos_sentencia
			valor = pst.executeUpdate();
		} catch (Exception e) {
			System.out.println("Error-Instruccion SQL-Actualizar Curso " + e.getMessage());
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
	public ArrayList<Curso> buscarXCodigo(int codigo) {

		// Creamno_el_array
		ArrayList<Curso> lista = new ArrayList<Curso>();
		// Conexion_
		Connection conexion = null;
		PreparedStatement pst = null;
		ResultSet resultado = null;
		try {
			//_Conexion
			conexion = MySQLConexion.conectar();
			//Sentencia_SQL
			String sql="SELECT * FROM curso WHERE id ="+codigo;
			//preparamos_la_sentencia
			pst=conexion.prepareStatement(sql);
			//pasamos_parametros
			//ejecutamos_sentencia
			resultado = pst.executeQuery();
			//
			while (resultado.next()) {
				Curso c = new Curso(resultado.getInt(1), resultado.getString(2), resultado.getString(3),
						resultado.getString(4), resultado.getString(5));
				// agregamos_al_array
				lista.add(c);
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

	

}
