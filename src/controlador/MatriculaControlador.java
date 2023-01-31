package controlador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import interfaces.MatriculaInterface;
import modelo.Matricula;
import util.MySQLConexion;

public class MatriculaControlador implements MatriculaInterface {

	@Override
	public int registrar(Matricula m) {
		int resultado = 0;
		Connection conexion = null;
		PreparedStatement pst1 = null, pst2 = null;
		try {
			conexion = MySQLConexion.conectar();// ESTABLECER_CONEXION
			// SENTENCIA_SQL
			String sql1 = "call registrar_matricula (?,?,?, curdate(), curtime() )";
			// preparar_la_sentencia_sql
			pst1 = conexion.prepareStatement(sql1);
			// comienza_desde_el_1
			pst1.setInt(1, m.getNumMatricula());
			pst1.setInt(2, m.getCodAlumno());
			pst1.setInt(3, m.getCodCurso());
			resultado = pst1.executeUpdate();
			// segunda_Sentencia_SQL
			String sql2 = "update alumno set estado='matriculado' where id =?";
			pst2 = conexion.prepareStatement(sql2);
			pst2.setInt(1, m.getCodAlumno());
			resultado = pst2.executeUpdate();
			// se_realiza_el_commit_transaccional
			conexion.commit();

		} catch (Exception e) {
			try {
				conexion.rollback();
			} catch (Exception e2) {
				System.out.println("Error al restaurar " + e.getMessage());
			}
			System.out.println("Error en la sentencia-registrar Matricula " + e.getMessage());
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
	public int eliminar(int codigo, int codA) {
		int valor = 0;
		Connection conexion = null;
		PreparedStatement pst1 = null, pst2 = null;
		try {
			// Establececr_conexion
			conexion = MySQLConexion.conectar();
			// sentencia_sql
			String sql1 = "UPDATE alumno SET estado='registrado' where id =?";
			pst1 = conexion.prepareStatement(sql1);
			pst1.setInt(1, codA);
			valor = pst1.executeUpdate();
			//
			String sql2 = "call eliminar_matricula(?)";
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
			System.out.println("Error en la Sentencia-eliminar Matricula " + e.getMessage());
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
	public ArrayList<Matricula> listado() {
		// METODO_RETORNA_UN_ARRAY_DE_ALUMNOS_DE_LA_BD
		// Creamno_el_array
		ArrayList<Matricula> lista = new ArrayList<Matricula>();
		// Conexion_
		Connection conexion = null;
		PreparedStatement pst = null;
		ResultSet resultado = null;
		try {
			// establecemos_conexion
			conexion = MySQLConexion.conectar();
			// Sentencia_SQL
			String sql = "call lista_matricula()";
			// preparamos_la_sentencia
			pst = conexion.prepareStatement(sql);
			// ejecutamos_sentencia
			resultado = pst.executeQuery();
			while (resultado.next()) {
				Matricula m = new Matricula(resultado.getInt(1), resultado.getInt(2), resultado.getInt(3),
						resultado.getString(4), resultado.getString(5));
				// agregamos_al_array
				lista.add(m);
			}

		} catch (Exception e) {
			System.out.println("Error-Instruccion SQL- Listar Matricula " + e.getMessage());
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
	public int actualizar(Matricula m) {
		int valor = 0;
		Connection conexion = null;
		PreparedStatement pst = null;
		try {
			// establecer_conexion
			conexion = MySQLConexion.conectar();
			// sentencia_sql
			String sql = "call actualizar_matricula(?,?)";
			// preparamos_sentencia
			pst = conexion.prepareStatement(sql);
			// pasamos_parametros
			pst.setInt(1, m.getCodCurso());
			pst.setInt(2, m.getNumMatricula());
			// ejecutamos_sentencia
			valor = pst.executeUpdate();
		} catch (Exception e) {
			System.out.println("Error-Instruccion SQL-Actualizar Matricula " + e.getMessage());
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
	public ArrayList<Matricula> buscarXCodigo(int codigo) {

		// Creamno_el_array
		ArrayList<Matricula> lista = new ArrayList<Matricula>();
		// Conexion_
		Connection conexion = null;
		PreparedStatement pst = null;
		ResultSet resultado = null;
		try {
			// _Conexion
			conexion = MySQLConexion.conectar();
			// Sentencia_SQL
			String sql = "SELECT * FROM matricula WHERE numMatri ="+codigo;
			// preparamos_la_sentencia
			pst = conexion.prepareStatement(sql);
			// pasamos_parametros
			// ejecutamos_sentencia
			resultado = pst.executeQuery();
			//
			while (resultado.next()) {
				Matricula m = new Matricula(resultado.getInt(1), resultado.getInt(2), resultado.getInt(3),
						resultado.getString(4), resultado.getString(5));
				// agregamos_al_array
				lista.add(m);
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

	@Override
	public ArrayList<Matricula> buscarCodAluXdni(int dni) {
		// Creamno_el_array
		ArrayList<Matricula> lista = new ArrayList<Matricula>();
		// Conexion_
		Connection conexion = null;
		PreparedStatement pst = null;
		ResultSet resultado = null;
		try {
			// _Conexion
			conexion = MySQLConexion.conectar();
			// Sentencia_SQL
			String sql = "SELECT id FROM alumno where dni =" + dni;
			// preparamos_la_sentencia
			pst = conexion.prepareStatement(sql);
			// pasamos_parametros
			// ejecutamos_sentencia
			resultado = pst.executeQuery();
			//
			while (resultado.next()) {
				Matricula m = new Matricula(resultado.getInt(1));
				// agregamos_al_array
				lista.add(m);
			}
		} catch (Exception e) {
			System.out.println("Error-Instruccion SQL- buscarCodAluXdni " + e.getMessage());
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
	
	public ArrayList<Matricula> buscarXCodAlu(int codigo){
		// Creamno_el_array
				ArrayList<Matricula> lista = new ArrayList<Matricula>();
				// Conexion_
				Connection conexion = null;
				PreparedStatement pst = null;
				ResultSet resultado = null;
				try {
					// _Conexion
					conexion = MySQLConexion.conectar();
					// Sentencia_SQL
					String sql = "SELECT codCurso FROM matricula where codAlu =" + codigo;
					// preparamos_la_sentencia
					pst = conexion.prepareStatement(sql);
					// pasamos_parametros
					// ejecutamos_sentencia
					resultado = pst.executeQuery();
					//
					while (resultado.next()) {
						Matricula m = new Matricula(resultado.getInt(1));
						// agregamos_al_array
						lista.add(m);
					}
				} catch (Exception e) {
					System.out.println("Error-Instruccion SQL- buscarXCodAlu " + e.getMessage());
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
