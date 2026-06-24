package view;

import model.Projeto;

import javax.swing.table.AbstractTableModel;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

public class ProjetoTableModel extends AbstractTableModel {

    private static final String[] COLUNAS = {
            "ID", "Nome", "Data inicio", "Orcamento", "Status", "Concluido"
    };

    private static final DateTimeFormatter FORMATO_DATA = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private List<Projeto> projetos;

    public ProjetoTableModel(List<Projeto> projetos) {
        this.projetos = projetos;
    }

    public void atualizarDados(List<Projeto> novosProjetos) {
        this.projetos = novosProjetos;
        fireTableDataChanged();
    }

    public Projeto getProjetoNaLinha(int linha) {
        if (linha < 0 || linha >= projetos.size()) {
            return null;
        }
        return projetos.get(linha);
    }

    @Override
    public int getRowCount() {
        return projetos.size();
    }

    @Override
    public int getColumnCount() {
        return COLUNAS.length;
    }

    @Override
    public String getColumnName(int column) {
        return COLUNAS[column];
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Projeto p = projetos.get(rowIndex);
        switch (columnIndex) {
            case 0: return p.getId();
            case 1: return p.getNome();
            case 2: return p.getDataInicio() != null ? p.getDataInicio().format(FORMATO_DATA) : "";
            case 3: return String.format(Locale.forLanguageTag("pt-BR"), "R$ %,.2f", p.getOrcamento());
            case 4: return p.getStatus() != null ? p.getStatus().toString() : "";
            case 5: return p.isConcluido() ? "Sim" : "Nao";
            default: return null;
        }
    }
}
