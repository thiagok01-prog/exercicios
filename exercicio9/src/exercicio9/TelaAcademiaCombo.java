package exercicio9;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class TelaAcademiaCombo extends JFrame {

    private JTextField campoNome;
    private JTextField campoTelefone;

    private JComboBox<Plano> comboPlano;
    private JComboBox<Duracao> comboDuracao;
    private JComboBox<Frequencia> comboFrequencia;

    private JLabel labelResultado;

    public TelaAcademiaCombo() {
        setTitle("Exercício 9 - Mensalidade da Academia (JComboBox + Enum)");
        setSize(500, 320);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        int linha = 0;


        gbc.gridx = 0; gbc.gridy = linha;
        add(new JLabel("Nome:"), gbc);
        campoNome = new JTextField(15);
        gbc.gridx = 1; gbc.gridy = linha;
        add(campoNome, gbc);
        linha++;

        gbc.gridx = 0; gbc.gridy = linha;
        add(new JLabel("Telefone:"), gbc);
        campoTelefone = new JTextField(15);
        gbc.gridx = 1; gbc.gridy = linha;
        add(campoTelefone, gbc);
        linha++;

        gbc.gridx = 0; gbc.gridy = linha;
        add(new JLabel("Plano:"), gbc);
        comboPlano = new JComboBox<>(Plano.values());
        gbc.gridx = 1; gbc.gridy = linha;
        add(comboPlano, gbc);
        linha++;

        gbc.gridx = 0; gbc.gridy = linha;
        add(new JLabel("Duração do contrato:"), gbc);
        comboDuracao = new JComboBox<>(Duracao.values());
        gbc.gridx = 1; gbc.gridy = linha;
        add(comboDuracao, gbc);
        linha++;

        gbc.gridx = 0; gbc.gridy = linha;
        add(new JLabel("Frequência semanal:"), gbc);
        comboFrequencia = new JComboBox<>(Frequencia.values());
        gbc.gridx = 1; gbc.gridy = linha;
        add(comboFrequencia, gbc);
        linha++;

        JButton botaoCalcular = new JButton("Calcular");
        gbc.gridx = 0; gbc.gridy = linha; gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;
        add(botaoCalcular, gbc);
        linha++;

        labelResultado = new JLabel(" ");
        labelResultado.setFont(new Font("Arial", Font.BOLD, 14));
        gbc.gridx = 0; gbc.gridy = linha; gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(labelResultado, gbc);

        botaoCalcular.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calcular();
            }
        });
    }

    private void calcular() {
        String nome = campoNome.getText().trim();
        String telefone = campoTelefone.getText().trim();

        if (nome.isEmpty() || telefone.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Preencha o nome e o telefone.",
                    "Campos obrigatórios",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        Plano plano = (Plano) comboPlano.getSelectedItem();
        Duracao duracao = (Duracao) comboDuracao.getSelectedItem();
        Frequencia frequencia = (Frequencia) comboFrequencia.getSelectedItem();

        Mensalidade mensalidade = new Mensalidade();
        double valor = mensalidade.calcularValor(plano, duracao, frequencia);

        labelResultado.setText(String.format(
                "<html>Aluno: %s | Plano: %s | Duração: %s | Frequência: %s<br>Valor da mensalidade: R$ %.2f</html>",
                nome, plano, duracao, frequencia, valor));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new TelaAcademiaCombo().setVisible(true);
            }
        });
    }
}
