package view;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;

public class Tema {

    public static final Color FUNDO_PRINCIPAL = new Color(0x1B, 0x24, 0x2C);   // azul-chumbo escuro
    public static final Color FUNDO_PAINEL    = new Color(0x24, 0x30, 0x3B);   // azul-chumbo medio
    public static final Color FUNDO_CARD      = new Color(0x2C, 0x39, 0x45);   // card levemente mais claro
    public static final Color FUNDO_CAMPO     = new Color(0xF5, 0xF7, 0xFA);   // quase branco (campos de texto)
    public static final Color DESTAQUE        = new Color(0x2E, 0xC4, 0xB6);   // teal vibrante
    public static final Color DESTAQUE_ESCURO = new Color(0x21, 0x9E, 0x93);
    public static final Color PERIGO          = new Color(0xE7, 0x4C, 0x3C);
    public static final Color PERIGO_ESCURO   = new Color(0xC0, 0x39, 0x2B);
    public static final Color SUCESSO         = new Color(0x2E, 0xC4, 0x71);
    public static final Color TEXTO_CLARO     = new Color(0xEC, 0xF0, 0xF3);
    public static final Color TEXTO_SECUNDARIO= new Color(0xA8, 0xB4, 0xBD);
    public static final Color TEXTO_ESCURO    = new Color(0x20, 0x28, 0x2E);
    public static final Color BORDA           = new Color(0x3A, 0x49, 0x55);

    public static final Font FONTE_TITULO   = new Font("Segoe UI", Font.BOLD, 22);
    public static final Font FONTE_SUBTITULO= new Font("Segoe UI", Font.PLAIN, 13);
    public static final Font FONTE_LABEL    = new Font("Segoe UI", Font.BOLD, 13);
    public static final Font FONTE_CAMPO    = new Font("Segoe UI", Font.PLAIN, 14);
    public static final Font FONTE_BOTAO    = new Font("Segoe UI", Font.BOLD, 13);
    public static final Font FONTE_TABELA   = new Font("Segoe UI", Font.PLAIN, 13);
    public static final Font FONTE_TABELA_HEADER = new Font("Segoe UI", Font.BOLD, 13);

    private Tema() {
    }

    public static void aplicarConfiguracoesGlobais() {
        UIManager.put("ToolTip.background", FUNDO_CARD);
        UIManager.put("ToolTip.foreground", TEXTO_CLARO);
        UIManager.put("OptionPane.background", FUNDO_PAINEL);
        UIManager.put("Panel.background", FUNDO_PAINEL);
    }

    public static JTextField criarCampoTexto() {
        JTextField campo = new JTextField();
        estilizarCampo(campo);
        return campo;
    }

    public static JTextArea criarAreaTexto(int linhas) {
        JTextArea area = new JTextArea(linhas, 20);
        area.setLineWrap(true);
        area.setWrapStyleWord(true);
        area.setFont(FONTE_CAMPO);
        area.setBackground(FUNDO_CAMPO);
        area.setForeground(TEXTO_ESCURO);
        area.setBorder(new EmptyBorder(8, 10, 8, 10));
        area.setCaretColor(TEXTO_ESCURO);
        return area;
    }

    public static void estilizarCampo(JTextField campo) {
        campo.setFont(FONTE_CAMPO);
        campo.setBackground(FUNDO_CAMPO);
        campo.setForeground(TEXTO_ESCURO);
        campo.setCaretColor(TEXTO_ESCURO);
        campo.setBorder(new CompoundBorder(
                new LineBorder(BORDA, 1, true),
                new EmptyBorder(8, 10, 8, 10)
        ));
    }

    public static void marcarErro(JComponent campo) {
        campo.setBorder(new CompoundBorder(
                new LineBorder(PERIGO, 2, true),
                new EmptyBorder(7, 9, 7, 9)
        ));
    }

    public static void limparErro(JComponent campo) {
        campo.setBorder(new CompoundBorder(
                new LineBorder(BORDA, 1, true),
                new EmptyBorder(8, 10, 8, 10)
        ));
    }

    public static JComboBox<?> estilizarComboBox(JComboBox<?> combo) {
        combo.setFont(FONTE_CAMPO);
        combo.setBackground(FUNDO_CAMPO);
        combo.setForeground(TEXTO_ESCURO);
        combo.setBorder(new EmptyBorder(4, 8, 4, 8));
        return combo;
    }

    public static JLabel criarLabel(String texto) {
        JLabel label = new JLabel(texto);
        label.setFont(FONTE_LABEL);
        label.setForeground(TEXTO_CLARO);
        return label;
    }

    public static JLabel criarLabelErro() {
        JLabel label = new JLabel(" ");
        label.setFont(new Font("Segoe UI", Font.ITALIC, 11));
        label.setForeground(PERIGO);
        return label;
    }

    public static JButton criarBotao(String texto, Color corBase, Color corHover) {
        JButton botao = new JButton(texto) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                Color cor = getModel().isRollover() ? corHover : corBase;
                if (getModel().isPressed()) {
                    cor = corHover.darker();
                }
                g2.setColor(cor);
                g2.fill(new RoundRectangle2D.Float(0, 0, getWidth() - 1, getHeight() - 1, 10, 10));
                g2.dispose();
                super.paintComponent(g);
            }
        };
        botao.setFont(FONTE_BOTAO);
        botao.setForeground(Color.WHITE);
        botao.setFocusPainted(false);
        botao.setContentAreaFilled(false);
        botao.setBorder(new EmptyBorder(10, 22, 10, 22));
        botao.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        return botao;
    }

    public static class PainelCard extends JPanel {
        private final Color cor;
        private final int raio;

        public PainelCard(Color cor, int raio) {
            this.cor = cor;
            this.raio = raio;
            setOpaque(false);
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(cor);
            g2.fill(new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), raio, raio));
            g2.dispose();
            super.paintComponent(g);
        }
    }

    public static void adicionarEfeitoHover(JComponent componente, Color corNormal, Color corHover) {
        componente.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                componente.setForeground(corHover);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                componente.setForeground(corNormal);
            }
        });
    }
}
