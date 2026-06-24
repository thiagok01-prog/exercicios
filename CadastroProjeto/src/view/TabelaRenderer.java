package view;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

public class TabelaRenderer extends DefaultTableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                                                     boolean hasFocus, int row, int column) {
        Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        setHorizontalAlignment(CENTER);
        setFont(Tema.FONTE_TABELA);
        setBorder(BorderFactory.createEmptyBorder(6, 8, 6, 8));

        if (isSelected) {
            c.setBackground(Tema.DESTAQUE);
            c.setForeground(Color.WHITE);
        } else {
            c.setBackground(row % 2 == 0 ? Tema.FUNDO_CARD : Tema.FUNDO_PAINEL);
            c.setForeground(Tema.TEXTO_CLARO);
        }
        return c;
    }
}
