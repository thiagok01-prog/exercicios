package exercicio3;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaRetangulo extends JFrame {

    private JTextField campoBase;
    private JTextField campoAltura;
    private JLabel labelPerimetro;
    private JLabel labelArea;

    public TelaRetangulo() {
        setTitle("Exercício 3 - Retângulo");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(5, 2, 10, 10));

        campoBase = new JTextField();
        campoAltura = new JTextField();
        labelPerimetro = new JLabel("---");
        labelArea = new JLabel("---");
        JButton botaoCalcular = new JButton("Calcular");

        add(new JLabel("Base:"));
        add(campoBase);
        add(new JLabel("Altura:"));
        add(campoAltura);
        add(new JLabel());
        add(botaoCalcular);
        add(new JLabel("Perímetro:"));
        add(labelPerimetro);
        add(new JLabel("Área:"));
        add(labelArea);

        botaoCalcular.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calcular();
            }
        });
    }

    private void calcular() {
        try {
            double base = Double.parseDouble(campoBase.getText());
            double altura = Double.parseDouble(campoAltura.getText());

            Retangulo retangulo = new Retangulo();
            double perimetro = retangulo.calcularPerimetro(base, altura);
            double area = retangulo.calcularArea(base, altura);

            labelPerimetro.setText(String.format("%.2f", perimetro));
            labelArea.setText(String.format("%.2f", area));
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this,
                    "Informe valores numéricos válidos para base e altura.",
                    "Erro de entrada",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new TelaRetangulo().setVisible(true);
            }
        });
    }
}
