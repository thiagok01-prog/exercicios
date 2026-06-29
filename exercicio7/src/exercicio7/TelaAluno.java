package exercicio7;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaAluno extends JFrame {

    private JTextField campoNome;
    private JTextField campoNota1;
    private JTextField campoNota2;
    private JLabel labelMedia;
    private JLabel labelSituacao;

    public TelaAluno() {
        setTitle("Exercício 7 - Média Final do Aluno");
        setSize(400, 280);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(6, 2, 10, 10));

        campoNome = new JTextField();
        campoNota1 = new JTextField();
        campoNota2 = new JTextField();
        labelMedia = new JLabel("---");
        labelSituacao = new JLabel("---");
        JButton botaoCalcular = new JButton("Calcular Média");

        add(new JLabel("Nome do aluno:"));
        add(campoNome);
        add(new JLabel("Nota 1:"));
        add(campoNota1);
        add(new JLabel("Nota 2:"));
        add(campoNota2);
        add(new JLabel());
        add(botaoCalcular);
        add(new JLabel("Média:"));
        add(labelMedia);
        add(new JLabel("Situação:"));
        add(labelSituacao);

        botaoCalcular.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calcular();
            }
        });
    }

    private void calcular() {
        String nome = campoNome.getText().trim();

        if (nome.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Informe o nome do aluno.",
                    "Campo obrigatório",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            double nota1 = Double.parseDouble(campoNota1.getText());
            double nota2 = Double.parseDouble(campoNota2.getText());

            Aluno aluno = new Aluno(nome, nota1, nota2);
            double media = aluno.calcularMedia();
            String situacao = aluno.calcularSituacao();

            labelMedia.setText(String.format("%.2f", media));
            labelSituacao.setText(situacao);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this,
                    "Informe notas numéricas válidas.",
                    "Erro de entrada",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new TelaAluno().setVisible(true);
            }
        });
    }
}
