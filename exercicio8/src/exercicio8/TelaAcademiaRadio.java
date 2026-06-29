package exercicio8;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaAcademiaRadio extends JFrame {

    private JTextField campoNome;
    private JTextField campoTelefone;

    private JRadioButton radioBasico;
    private JRadioButton radioIntermediario;
    private JRadioButton radioPremium;

    private JRadioButton radioMensal;
    private JRadioButton radioSemestral;
    private JRadioButton radioAnual;

    private JRadioButton radio2x;
    private JRadioButton radio3x;
    private JRadioButton radio5x;

    private JLabel labelResultado;

    public TelaAcademiaRadio() {
        setTitle("Exercício 8 - Mensalidade da Academia (JRadioButton)");
        setSize(500, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        int linha = 0;

        gbc.gridx = 0; gbc.gridy = linha; gbc.gridwidth = 1;
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

        radioBasico = new JRadioButton("Básico");
        radioIntermediario = new JRadioButton("Intermediário");
        radioPremium = new JRadioButton("Premium");
        ButtonGroup grupoPlano = new ButtonGroup();
        grupoPlano.add(radioBasico);
        grupoPlano.add(radioIntermediario);
        grupoPlano.add(radioPremium);

        JPanel painelPlano = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        painelPlano.add(radioBasico);
        painelPlano.add(radioIntermediario);
        painelPlano.add(radioPremium);

        gbc.gridx = 1; gbc.gridy = linha;
        add(painelPlano, gbc);
        linha++;

        gbc.gridx = 0; gbc.gridy = linha;
        add(new JLabel("Duração do contrato:"), gbc);

        radioMensal = new JRadioButton("Mensal");
        radioSemestral = new JRadioButton("Semestral");
        radioAnual = new JRadioButton("Anual");
        ButtonGroup grupoDuracao = new ButtonGroup();
        grupoDuracao.add(radioMensal);
        grupoDuracao.add(radioSemestral);
        grupoDuracao.add(radioAnual);

        JPanel painelDuracao = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        painelDuracao.add(radioMensal);
        painelDuracao.add(radioSemestral);
        painelDuracao.add(radioAnual);

        gbc.gridx = 1; gbc.gridy = linha;
        add(painelDuracao, gbc);
        linha++;

        gbc.gridx = 0; gbc.gridy = linha;
        add(new JLabel("Frequência semanal:"), gbc);

        radio2x = new JRadioButton("2x por semana");
        radio3x = new JRadioButton("3x por semana");
        radio5x = new JRadioButton("5x por semana");
        ButtonGroup grupoFrequencia = new ButtonGroup();
        grupoFrequencia.add(radio2x);
        grupoFrequencia.add(radio3x);
        grupoFrequencia.add(radio5x);

        JPanel painelFrequencia = new JPanel(new GridLayout(3, 1));
        painelFrequencia.add(radio2x);
        painelFrequencia.add(radio3x);
        painelFrequencia.add(radio5x);

        gbc.gridx = 1; gbc.gridy = linha;
        add(painelFrequencia, gbc);
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

        String plano = obterSelecionado(radioBasico, "Básico",
                radioIntermediario, "Intermediário",
                radioPremium, "Premium");

        String duracao = obterSelecionado(radioMensal, "Mensal",
                radioSemestral, "Semestral",
                radioAnual, "Anual");

        String frequencia = obterSelecionado(radio2x, "2x",
                radio3x, "3x",
                radio5x, "5x");

        if (plano == null || duracao == null || frequencia == null) {
            JOptionPane.showMessageDialog(this,
                    "Selecione o plano, a duração do contrato e a frequência semanal.",
                    "Campos obrigatórios",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        Mensalidade mensalidade = new Mensalidade();
        double valor = mensalidade.calcularValor(plano, duracao, frequencia);

        labelResultado.setText(String.format(
                "<html>Aluno: %s | Plano: %s | Duração: %s | Frequência: %s<br>Valor da mensalidade: R$ %.2f</html>",
                nome, plano, duracao, frequencia, valor));
    }

    private String obterSelecionado(JRadioButton r1, String v1,
                                     JRadioButton r2, String v2,
                                     JRadioButton r3, String v3) {
        if (r1.isSelected()) return v1;
        if (r2.isSelected()) return v2;
        if (r3.isSelected()) return v3;
        return null;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new TelaAcademiaRadio().setVisible(true);
            }
        });
    }
}
