package vistas;

import java.awt.BorderLayout;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import java.awt.Color;
import javax.swing.table.DefaultTableModel;

import controlador.AlumnoControlador;
import controlador.CursoControlador;
import controlador.MatriculaControlador;
import controlador.RetiroControlador;
import modelo.Alumno;
import modelo.Curso;
import modelo.Matricula;
import modelo.Retiro;

import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class JD_matriculas_retiros extends JDialog implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	//
	public static DefaultTableModel modelo;
	//
	public static RetiroControlador re = new RetiroControlador();
	private JButton button;
	private JLabel lblCodAlumno;
	private JTextField txtNumMatricula;
	private JButton btnConsultarMatricula;
	private JTextArea txtMatriculaSalida;
	private JScrollPane scrollPane;
	private JLabel lblNRetiro;
	private JTextField txtNumRetiro;
	private JButton btnConsultarRetiro;
	private JTextArea txtRetiroSalida;
	private JScrollPane scrollPane_1;
	//
	AlumnoControlador alu = new AlumnoControlador();
	CursoControlador cur = new CursoControlador();
	MatriculaControlador mat = new MatriculaControlador();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			JD_matriculas_retiros dialog = new JD_matriculas_retiros();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	@SuppressWarnings({})
	public JD_matriculas_retiros() {
		setModal(true);
		setIconImage(
				Toolkit.getDefaultToolkit().getImage(JD_matriculas_retiros.class.getResource("/imagenes/aac.jpg")));
		setResizable(false);
		setTitle("CONSULTA |Matriculas y Retiros");
		setBounds(100, 100, 1000, 700);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(240, 255, 240));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		button = new JButton("SALIR");
		button.addActionListener(this);
		button.setIcon(new ImageIcon(JD_matriculas_retiros.class.getResource("/imagenes/salir.png")));
		button.setFont(new Font("Tahoma", Font.BOLD, 14));
		button.setBounds(779, 19, 205, 42);
		contentPanel.add(button);

		lblCodAlumno = new JLabel("N\u00B0 Matricula :");
		lblCodAlumno.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblCodAlumno.setBounds(10, 29, 135, 25);
		contentPanel.add(lblCodAlumno);

		txtNumMatricula = new JTextField();
		txtNumMatricula.setFont(new Font("Tahoma", Font.BOLD, 13));
		txtNumMatricula.setEnabled(false);
		txtNumMatricula.setColumns(10);
		txtNumMatricula.setBounds(155, 32, 200, 25);
		contentPanel.add(txtNumMatricula);

		btnConsultarMatricula = new JButton("CONSULTAR");
		btnConsultarMatricula.addActionListener(this);
		btnConsultarMatricula.setIcon(new ImageIcon(JD_matriculas_retiros.class.getResource("/imagenes/buscar.png")));
		btnConsultarMatricula.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnConsultarMatricula.setEnabled(true);
		btnConsultarMatricula.setBounds(394, 22, 205, 36);
		contentPanel.add(btnConsultarMatricula);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 65, 974, 260);
		contentPanel.add(scrollPane);

		txtMatriculaSalida = new JTextArea();
		txtMatriculaSalida.setFont(new Font("Monospaced", Font.BOLD, 15));
		scrollPane.setViewportView(txtMatriculaSalida);

		lblNRetiro = new JLabel("N\u00B0 Retiro :");
		lblNRetiro.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNRetiro.setBounds(10, 348, 135, 25);
		contentPanel.add(lblNRetiro);

		txtNumRetiro = new JTextField();
		txtNumRetiro.setFont(new Font("Tahoma", Font.BOLD, 13));
		txtNumRetiro.setEnabled(false);
		txtNumRetiro.setColumns(10);
		txtNumRetiro.setBounds(155, 351, 200, 25);
		contentPanel.add(txtNumRetiro);

		btnConsultarRetiro = new JButton("CONSULTAR");
		btnConsultarRetiro.addActionListener(this);
		btnConsultarRetiro.setIcon(new ImageIcon(JD_matriculas_retiros.class.getResource("/imagenes/buscar.png")));
		btnConsultarRetiro.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnConsultarRetiro.setEnabled(true);
		btnConsultarRetiro.setBounds(394, 341, 205, 36);
		contentPanel.add(btnConsultarRetiro);

		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(11, 385, 972, 260);
		contentPanel.add(scrollPane_1);

		txtRetiroSalida = new JTextArea();
		txtRetiroSalida.setFont(new Font("Monospaced", Font.BOLD, 15));
		scrollPane_1.setViewportView(txtRetiroSalida);

		//
		txtNumMatricula.setEnabled(true);
		txtMatriculaSalida.setEditable(false);
		txtNumRetiro.setEnabled(true);
		txtRetiroSalida.setEditable(false);
	}

	void consultarMatricula() {
		try {
			ArrayList<Matricula> ma = mat.buscarXCodigo(LeerNumMatricula());
			for (Matricula m : ma) {
				int alumno = m.getCodAlumno();
				ArrayList<Alumno> al = alu.buscarXCodigo(alumno);
				for (Alumno a : al) {
					int curso = m.getCodCurso();
					ArrayList<Curso> cu = cur.buscarXCodigo(curso);
					for (Curso c : cu) {
						txtMatriculaSalida.setText("");
						imprimirMatricula("Nº Matricula  :" + m.getNumMatricula());
						imprimirMatricula("====================================");
						imprimirMatricula("Codigo Alumno :" + a.getId());
						imprimirMatricula("Nombres       :" + a.getNombres());
						imprimirMatricula("Apellidos     :" + a.getApellidos());
						imprimirMatricula("DNI           :" + a.getDni());
						imprimirMatricula("Edad          :" + a.getEdad());
						imprimirMatricula("Celular       :" + a.getCelular());
						imprimirMatricula("Estado        :" + a.getEstado());
						imprimirMatricula("====================================");
						imprimirMatricula("Codigo Curso  :" + c.getId());
						imprimirMatricula("Asignatura    :" + c.getAsignatura());
						imprimirMatricula("Ciclo         :" + c.getCiclo());
						imprimirMatricula("Creditos      :" + c.getCreditos());
						imprimirMatricula("Horas         :" + c.getHoras());

					}
				}
			}

		} catch (Exception e) {
			mensaje("Error de ingreso");
			txtNumMatricula.setText("");
			txtMatriculaSalida.setText("");
		}

	}

	void consultarRetiro() {
		try {
			ArrayList<Retiro> ret = re.buscarXCodigo(LeerNumRetiro());
			for (Retiro r : ret) {
				int matricula = r.getNumMatricula();
				ArrayList<Matricula> ma = mat.buscarXCodigo(matricula);
				for (Matricula m : ma) {
					int alumno = m.getCodAlumno();
					ArrayList<Alumno> al = alu.buscarXCodigo(alumno);
					for (Alumno a : al) {
						int curso = m.getCodCurso();
						ArrayList<Curso> cu = cur.buscarXCodigo(curso);
						for (Curso c : cu) {
							txtRetiroSalida.setText("");
							imprimirRetiro("Nº retiro           :" + r.getNumReti());
							imprimirRetiro("=======================================");
							imprimirRetiro("Codigo Alumno       :" + a.getId());
							imprimirRetiro("Nombres             :" + a.getNombres());
							imprimirRetiro("Apellidos           :" + a.getApellidos());
							imprimirRetiro("DNI                 :" + a.getDni());
							imprimirRetiro("Edad                :" + a.getEdad());
							imprimirRetiro("Celular             :" + a.getCelular());
							imprimirRetiro("Estado              :" + a.getEstado());
							imprimirRetiro("=======================================");
							imprimirRetiro("Codigo de curso     :" + c.getId());
							imprimirRetiro("Asignatura          :" + c.getAsignatura());
							imprimirRetiro("Ciclo               :" + c.getCiclo());
							imprimirRetiro("Creditos            :" + c.getCreditos());
							imprimirRetiro("Horas               :" + c.getHoras());
							imprimirRetiro("");
						}
					}
				}
			}

		} catch (Exception e) {
			mensaje("Error de ingreso");
			txtRetiroSalida.setText("");
			txtNumRetiro.setText("");
		}
	}

	//
	void imprimirMatricula(String s) {
		txtMatriculaSalida.append(s + "\n");
	}

	void imprimirRetiro(String s) {
		txtRetiroSalida.append(s + "\n");
	}

	int LeerNumMatricula() {
		return Integer.parseInt(txtNumMatricula.getText());
	}

	int LeerNumRetiro() {
		return Integer.parseInt(txtNumRetiro.getText());
	}

	void mensaje(String m) {
		JOptionPane.showMessageDialog(this, m);
	}

	// ************************************************************

	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == button) {
			actionPerformedButton(arg0);
		}
		if (arg0.getSource() == btnConsultarRetiro) {
			actionPerformedBtnConsultarCur(arg0);
		}
		if (arg0.getSource() == btnConsultarMatricula) {
			actionPerformedBtnConsultarAlu(arg0);
		}
	}

	protected void actionPerformedBtnConsultarAlu(ActionEvent arg0) {
		consultarMatricula();
	}

	protected void actionPerformedBtnConsultarCur(ActionEvent arg0) {
		consultarRetiro();
	}

	protected void actionPerformedButton(ActionEvent arg0) {
		dispose();
	}
}
