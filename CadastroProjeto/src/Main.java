import view.Tema;
import view.TelaPrincipal;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Tema.aplicarConfiguracoesGlobais();
            TelaPrincipal tela = new TelaPrincipal();
            tela.setVisible(true);
        });
    }
}
