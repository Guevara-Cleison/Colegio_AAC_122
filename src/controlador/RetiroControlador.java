package controlador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import interfaces.RetiroInterface;
import modelo.Retiro;
import util.MySQLConexion;

public class RetiroControlador implements RetiroInterface {

	@Override
	public int registrar(Retiro r) {
		int resultado = 0;
		Connection conexion = null;
		PreparedStatement pst1 = null, pst2 = null;
		try {
			conexion = MySQLConexion.conectar();// ESTABLECER_CONEXION
			// SENTENCIA_SQL
			String sql1 = "call registrar_retiro (?,?, curdate(), curtime() )";
			// preparar_la_sentencia_sql
			pst1 = conexion.prepareStatement(sql1);
			// comienza_desde_el_1
			pst1.setInt(1, r.getNumReti());
			pst1.setInt(2, r.getNumMatricula());
			resultado = pst1.executeUpdate();
			// segunda_Sentencia_SQL
			//Ingresar_numMatricula
			String sql2 = "call actualizar_Estado_Registro(?)";
			pst2 = conexion.prepareStatement(sql2);
			pst2.setInt(1, r.getNumMatricula());
			resultado = pst2.executeUpdate();
			// se_realiza_el_commit_transaccional
			conexion.commit();

		} catch (Exception e) {
			try {
				conexion.rollback();
			} catch (Exception e2) {
				System.out.println("Error al restaurar " + e.getMessage());
			}
			System.out.println("Error en la sentencia-registrar Retiro " + e.getMessage());
		} finally {
			try {
				if (pst1 != null) {
					pst1.close();
				}
				if (pst2 != null) {
					pst2.close();
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
	public int eliminar(int codigo, int codM) {
		int valor = 0;
		Connection conexion = null;
		PreparedStatement pst1 = null, pst2 = null;
		try {
			// Establececr_conexion
			conexion = MySQLConexion.conectar();
			// sentencia_sql
			//Ingresar_numMatricula
			String sql1 = "call actualizar_Estado_Eliminar(?)";
			pst1 = conexion.prepareStatement(sql1);
			pst1.setInt(1, codM);
			valor = pst1.executeUpdate();
			//
			String sql2 = "call eliminar_retiro(?)";
			pst2 = conexion.prepareStatement(sql2);
			pst2.setInt(1, codigo);
			valor = pst2.executeUpdate();
			// commit
			conexion.commit();
		} catch (Exception e) {
			try {
				conexion.rollback();
			} catch (Exception e2) {
				System.out.println("Error al restaurar " + e.getMessage());
			}
			System.out.println("Error en la Sentencia-eliminar Retiro " + e.getMessage());
		} finally {
			try {
				if (pst1 != null) {
					pst1.close();
				}
				if (pst2 != null) {
					pst2.close();
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
	public ArrayList<Retiro> listado() {
		// METODO_RETORNA_UN_ARRAY_DE_ALUMNOS_DE_LA_BD
		// Creamno_el_array
		ArrayList<Retiro> lista = new ArrayList<Retiro>();
		// Conexion_
		Connection conexion = null;
		PreparedStatement pst = null;
		ResultSet resultado = null;
		try {
			// establecemos_conexion
			conexion = MySQLConexion.conectar();
			// Sentencia_SQL
			String sql = "call lista_retiro()";
			// preparamos_la_sentencia
			pst = conexion.prepareStatement(sql);
			// ejecutamos_sentencia
			resultado = pst.executeQuery();
			while (resultado.next()) {
				Retiro r = new Retiro(resultado.getInt(1), resultado.getInt(2), resultado.getString(3),
						resultado.getString(4));
				// agregamos_al_array
				lista.add(r);
			}

		} catch (Exception e) {
			System.out.println("Error-Instruccion SQL- Listar Retiro " + e.getMessage());
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
	public ArrayList<Retiro> buscarXCodigo(int codigo) {

		// Creamno_el_array
		ArrayList<Retiro> lista = new ArrayList<Retiro>();
		// Conexion_
		Connection conexion = null;
		PreparedStatement pst = null;
		ResultSet resultado = null;
		try {
			// _Conexion
			conexion = MySQLConexion.conectar();
			// Sentencia_SQL
			String sql = "SELECT * FROM retiro WHERE numReti ="+ codigo;
			// preparamos_la_sentencia
			pst = conexion.prepareStatement(sql);
			// pasamos_parametros
			// ejecutamos_sentencia
			resultado = pst.executeQuery();
			//
			while (resultado.next()) {
				Retiro r = new Retiro(resultado.getInt(1), resultado.getInt(2), resultado.getString(3),
						resultado.getString(4));
				// agregamos_al_array
				lista.add(r);
			}
		} catch (Exception e) {
			System.out.println("Error-Instruccion SQL- buscarXCodigo " + e.getMessage());
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
				System.out.println("Error al cerrar la BD " + e.getMessage());
			}
		}
		return lista;
	}

}
