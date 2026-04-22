package exerc1;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JButton;

public class Tela1 extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Tela1 frame = new Tela1();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Tela1() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[][][grow][grow][][][][]", "[][][][][][][]"));
		
		JLabel lblNewLabel = new JLabel("Cadastro de Cliente - Academia TREINO FIT");
		contentPane.add(lblNewLabel, "cell 0 0 8 1,alignx center");
		
		JLabel lblNewLabel_1 = new JLabel("Nome");
		contentPane.add(lblNewLabel_1, "cell 0 1,alignx trailing");
		
		textField = new JTextField();
		contentPane.add(textField, "cell 1 1 6 1,growx");
		textField.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Telefone");
		contentPane.add(lblNewLabel_2, "cell 0 2,alignx trailing");
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		contentPane.add(textField_1, "cell 1 2 6 1,growx");
		
		JLabel lblNewLabel_3 = new JLabel("Tipo de Plano:");
		contentPane.add(lblNewLabel_3, "cell 0 3,alignx trailing");
		
		JRadioButton rdbtnNewRadioButton = new JRadioButton("Básico");
		contentPane.add(rdbtnNewRadioButton, "flowx,cell 1 3,alignx left");
		
		JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("Intermediário");
		contentPane.add(rdbtnNewRadioButton_1, "cell 2 3");
		
		JRadioButton rdbtnNewRadioButton_2 = new JRadioButton("Premium");
		contentPane.add(rdbtnNewRadioButton_2, "cell 3 3");
		
		JLabel lblNewLabel_4 = new JLabel("Duração:");
		contentPane.add(lblNewLabel_4, "cell 0 4,alignx trailing");
		
		JRadioButton rdbtnMensal = new JRadioButton("Mensal");
		contentPane.add(rdbtnMensal, "cell 1 4");
		
		JRadioButton rdbtnSemestral = new JRadioButton("Semestral");
		contentPane.add(rdbtnSemestral, "cell 2 4");
		
		JRadioButton rdbtnAnual = new JRadioButton("Anual");
		contentPane.add(rdbtnAnual, "cell 3 4");
		
		JLabel lblNewLabel_5 = new JLabel("Frequência semanal");
		contentPane.add(lblNewLabel_5, "cell 0 5,alignx trailing");
		
		JRadioButton rdbtnxPorSemana = new JRadioButton("2x por semana");
		contentPane.add(rdbtnxPorSemana, "cell 1 5");
		
		JRadioButton rdbtnxPorSemana_1 = new JRadioButton("3x por semana");
		contentPane.add(rdbtnxPorSemana_1, "cell 2 5");
		
		JRadioButton rdbtnxPorSemana_2 = new JRadioButton("5x por semana");
		contentPane.add(rdbtnxPorSemana_2, "cell 3 5");
		
		JButton btnNewButton = new JButton("New button");
		contentPane.add(btnNewButton, "cell 1 6,alignx center");
		
		JLabel lblNewLabel_6 = new JLabel("Valor final:");
		contentPane.add(lblNewLabel_6, "cell 2 6,alignx right");
		
		JLabel lblNewLabel_7 = new JLabel("R$ 10,00");
		contentPane.add(lblNewLabel_7, "cell 3 6,alignx left");

	}

}
