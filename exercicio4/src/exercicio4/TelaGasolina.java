package exercicio4;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaGasolina extends JFrame {

    private JTextField campoPrecoLitro;
    private JTextField campoValorPago;
    private JLabel labelLitros;

    public TelaGasolina() {
        setTitle("Exercício 4 - Litros de Gasolina");
        setSize(400, 220);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(4, 2, 10, 10));

        campoPrecoLitro = new JTextField();
        campoValorPago = new JTextField();
        labelLitros = new JLabel("---");
        JButton botaoCalcular = new JButton("Calcular");

        add(new JLabel("Preço do litro (R$):"));
        add(campoPrecoLitro);
        add(new JLabel("Valor a pagar (R$):"));
        add(campoValorPago);
        add(new JLabel());
        add(botaoCalcular);
        add(new JLabel("Litros possíveis:"));
        add(labelLitros);

        botaoCalcular.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calcular();
            }
        });
    }

    private void calcular() {
        try {
            double precoLitro = Double.parseDouble(campoPrecoLitro.getText());
            double valorPago = Double.parseDouble(campoValorPago.getText());

            if (precoLitro <= 0) {
                JOptionPane.showMessageDialog(this,
                        "O preço do litro deve ser maior que zero.",
                        "Erro de entrada",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            CalculoGasolina calculo = new CalculoGasolina();
            double litros = calculo.calcularLitros(precoLitro, valorPago);

            labelLitros.setText(String.format("%.2f L", litros));
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
                new TelaGasolina().setVisible(true);
            }
        });
    }
}
