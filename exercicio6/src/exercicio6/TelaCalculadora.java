package exercicio6;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaCalculadora extends JFrame {

    private JTextField campoNumero1;
    private JTextField campoNumero2;
    private JTextField campoResultado;

    public TelaCalculadora() {
        setTitle("Exercício 6 - Calculadora Simples");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        JPanel painelCampos = new JPanel(new GridLayout(3, 2, 10, 10));
        campoNumero1 = new JTextField();
        campoNumero2 = new JTextField();
        campoResultado = new JTextField();
        campoResultado.setEditable(false);

        painelCampos.add(new JLabel("Número 1:"));
        painelCampos.add(campoNumero1);
        painelCampos.add(new JLabel("Número 2:"));
        painelCampos.add(campoNumero2);
        painelCampos.add(new JLabel("Resultado:"));
        painelCampos.add(campoResultado);

        JPanel painelBotoes = new JPanel(new GridLayout(1, 4, 10, 10));
        JButton botaoSomar = new JButton("Somar");
        JButton botaoSubtrair = new JButton("Subtrair");
        JButton botaoMultiplicar = new JButton("Multiplicar");
        JButton botaoDividir = new JButton("Dividir");

        painelBotoes.add(botaoSomar);
        painelBotoes.add(botaoSubtrair);
        painelBotoes.add(botaoMultiplicar);
        painelBotoes.add(botaoDividir);

        add(painelCampos, BorderLayout.CENTER);
        add(painelBotoes, BorderLayout.SOUTH);

        Calculadora calculadora = new Calculadora();

        botaoSomar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Double[] valores = lerValores();
                if (valores != null) {
                    double resultado = calculadora.somar(valores[0], valores[1]);
                    campoResultado.setText(String.valueOf(resultado));
                }
            }
        });

        botaoSubtrair.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Double[] valores = lerValores();
                if (valores != null) {
                    double resultado = calculadora.subtrair(valores[0], valores[1]);
                    campoResultado.setText(String.valueOf(resultado));
                }
            }
        });

        botaoMultiplicar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Double[] valores = lerValores();
                if (valores != null) {
                    double resultado = calculadora.multiplicar(valores[0], valores[1]);
                    campoResultado.setText(String.valueOf(resultado));
                }
            }
        });

        botaoDividir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Double[] valores = lerValores();
                if (valores != null) {
                    try {
                        double resultado = calculadora.dividir(valores[0], valores[1]);
                        campoResultado.setText(String.valueOf(resultado));
                    } catch (ArithmeticException ex) {
                        JOptionPane.showMessageDialog(TelaCalculadora.this,
                                "Não é possível dividir por zero.",
                                "Erro",
                                JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
    }

    private Double[] lerValores() {
        String texto1 = campoNumero1.getText().trim();
        String texto2 = campoNumero2.getText().trim();

        if (texto1.isEmpty() || texto2.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Preencha os dois campos numéricos.",
                    "Campos vazios",
                    JOptionPane.WARNING_MESSAGE);
            return null;
        }

        try {
            double numero1 = Double.parseDouble(texto1);
            double numero2 = Double.parseDouble(texto2);
            return new Double[] { numero1, numero2 };
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this,
                    "Informe valores numéricos válidos.",
                    "Erro de entrada",
                    JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new TelaCalculadora().setVisible(true);
            }
        });
    }
}
