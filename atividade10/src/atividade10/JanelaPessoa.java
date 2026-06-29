package atividade10;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class JanelaPessoa extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtNome;
	private JTextField txtCPF;
	private JTable table;
	
	private JButton btnAdicionar;
	private JButton btnRemover;
	private JButton btnVisualizar;
	private JTextField txtEmail;
	private JLabel lblNewLabel_2;

	

	/**
	 * Create the frame.
	 */
	public JanelaPessoa() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 556, 466);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		GridBagLayout gbl = new GridBagLayout();
		contentPane.setLayout(gbl);

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(5, 5, 5, 5);

		gbc.gridx = 0; gbc.gridy = 0; gbc.anchor = GridBagConstraints.EAST;
		JLabel lblNewLabel = new JLabel("Nome:");
		contentPane.add(lblNewLabel, gbc);

		gbc.gridx = 1; gbc.gridy = 0; gbc.gridwidth = 3; gbc.fill = GridBagConstraints.HORIZONTAL;
		txtNome = new JTextField();
		contentPane.add(txtNome, gbc);
		txtNome.setColumns(10);
		gbc.gridwidth = 1; gbc.fill = GridBagConstraints.NONE;

		gbc.gridx = 0; gbc.gridy = 1; gbc.anchor = GridBagConstraints.EAST;
		JLabel lblNewLabel_1 = new JLabel("CPF:");
		contentPane.add(lblNewLabel_1, gbc);

		gbc.gridx = 1; gbc.gridy = 1; gbc.gridwidth = 3; gbc.fill = GridBagConstraints.HORIZONTAL;
		txtCPF = new JTextField();
		contentPane.add(txtCPF, gbc);
		txtCPF.setColumns(10);
		gbc.gridwidth = 1; gbc.fill = GridBagConstraints.NONE;

		gbc.gridx = 0; gbc.gridy = 2; gbc.anchor = GridBagConstraints.EAST;
		lblNewLabel_2 = new JLabel("Email:");
		contentPane.add(lblNewLabel_2, gbc);

		gbc.gridx = 1; gbc.gridy = 2; gbc.gridwidth = 3; gbc.fill = GridBagConstraints.HORIZONTAL;
		txtEmail = new JTextField();
		contentPane.add(txtEmail, gbc);
		txtEmail.setColumns(10);
		gbc.gridwidth = 1; gbc.fill = GridBagConstraints.NONE;

		gbc.gridx = 0; gbc.gridy = 3; gbc.anchor = GridBagConstraints.CENTER;
		btnAdicionar = new JButton("Adicionar");
		contentPane.add(btnAdicionar, gbc);

		gbc.gridx = 1; gbc.gridy = 3;
		btnRemover = new JButton("Remover");
		contentPane.add(btnRemover, gbc);

		gbc.gridx = 2; gbc.gridy = 3;
		btnVisualizar = new JButton("Visualizar");
		contentPane.add(btnVisualizar, gbc);

		gbc.gridx = 0; gbc.gridy = 4; gbc.gridwidth = 4;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 1.0; gbc.weighty = 1.0;
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, gbc);

		table = new JTable();
		scrollPane.setViewportView(table);

	}

	public JTextField getTxtNome() {
		return txtNome;
	}

	public JTextField getTxtCPF() {
		return txtCPF;
	}
	
	public JTextField getTxtEmail() {
		return txtEmail;
	}

	public JTable getTable() {
		return table;
	}

	public JButton getBtnAdicionar() {
		return btnAdicionar;
	}

	public JButton getBtnRemover() {
		return btnRemover;
	}
	
	public JButton getBtnVisualizar() {
		return btnVisualizar;
	}
	
	

}
