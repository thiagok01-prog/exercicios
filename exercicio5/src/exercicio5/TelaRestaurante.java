package exercicio5;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaRestaurante extends JFrame {

    private JTextField campoPeso;
    private JTextField campoValorQuilo;
    private JLabel labelTotal;

    public TelaRestaurante() {
        setTitle("Exercício 5 - Restaurante Fomelândia");
        setSize(400, 220);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(4, 2, 10, 10));

        campoPeso = new JTextField();
        campoValorQuilo = new JTextField("9.95");
        labelTotal = new JLabel("---");
        JButton botaoCalcular = new JButton("Calcular");

        add(new JLabel("Peso do prato (kg):"));
        add(campoPeso);
        add(new JLabel("Valor do quilo (R$):"));
        add(campoValorQuilo);
        add(new JLabel());
        add(botaoCalcular);
        add(new JLabel("Valor a pagar:"));
        add(labelTotal);

        botaoCalcular.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calcular();
            }
        });
    }

    private void calcular() {
        try {
            double peso = Double.parseDouble(campoPeso.getText());
            double valorQuilo = Double.parseDouble(campoValorQuilo.getText());

            CalculoRefeicao calculo = new CalculoRefeicao();
            double total = calculo.calcularValorTotal(peso, valorQuilo);

            labelTotal.setText(String.format("R$ %.2f", total));
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this,
                    "Informe valores numéricos válidos.",
                    "Erro de entrada",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new TelaRestaurante().setVisible(true);
            }
        });
    }
}
