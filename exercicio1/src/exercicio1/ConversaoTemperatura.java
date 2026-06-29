package exercicio1;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class ConversaoTemperatura extends JFrame {

    private JTextField campoFahrenheit;
    private JLabel labelResultado;
    private JButton botaoConverter;

    public ConversaoTemperatura() {
        setTitle("Conversor de Temperatura - Farhenheit para Celsius");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(3, 2, 10, 10));

        JLabel labelFahrenheit = new JLabel("Temperatura em Fahrenheit:");
        campoFahrenheit = new JTextField();

        botaoConverter = new JButton("Converter");

        JLabel labelTitulo = new JLabel("Resultado em Celsius:");
        labelResultado = new JLabel("---");

        add(labelFahrenheit);
        add(campoFahrenheit);
        add(new JLabel());
        add(botaoConverter);
        add(labelTitulo);
        add(labelResultado);

        botaoConverter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                converter();
            }
        });
    }

    private void converter() {
        try {
            double fahrenheit = Double.parseDouble(campoFahrenheit.getText());

            Conversao conversao = new Conversao();
            double celsius = conversao.calcularTemperatura(fahrenheit);

            labelResultado.setText(String.format("%.2f °C", celsius));
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this,
                    "Por favor, digite um valor numérico válido.",
                    "Erro de entrada",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                ConversaoTemperatura tela = new ConversaoTemperatura();
                tela.setVisible(true);
            }
        });
    }
}