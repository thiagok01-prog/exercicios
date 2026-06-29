package exercicio2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NomeCompleto extends JFrame {

    private JTextField campoNome;
    private JTextField campoSobrenome;
    private JLabel labelResultado;

    public NomeCompleto() {
        setTitle("Exercício 2 - Nome Completo");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(4, 2, 10, 10));

        campoNome = new JTextField();
        campoSobrenome = new JTextField();
        labelResultado = new JLabel("---");
        JButton botaoMostrar = new JButton("Mostrar nome completo");

        add(new JLabel("Nome:"));
        add(campoNome);
        add(new JLabel("Sobrenome:"));
        add(campoSobrenome);
        add(new JLabel());
        add(botaoMostrar);
        add(new JLabel("Nome completo:"));
        add(labelResultado);

        botaoMostrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nome = campoNome.getText().trim();
                String sobrenome = campoSobrenome.getText().trim();
                labelResultado.setText(nome + " " + sobrenome);
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new NomeCompleto().setVisible(true);
            }
        });
    }
}
