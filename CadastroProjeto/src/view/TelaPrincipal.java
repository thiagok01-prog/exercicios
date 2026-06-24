package view;

import dao.ProjetoDAO;
import model.Projeto;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.util.List;

public class TelaPrincipal extends JFrame {

    private final ProjetoDAO dao;
    private ProjetoTableModel tableModel;
    private JTable tabela;
    private JTextField campoBusca;
    private JLabel labelTotal;

    public TelaPrincipal() {
        super("Cadastro de Projetos");
        this.dao = new ProjetoDAO();
        construirInterface();
        carregarDados(null);
        setLocationRelativeTo(null);
    }

    private void construirInterface() {
        getContentPane().setBackground(Tema.FUNDO_PRINCIPAL);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(960, 600);
        setMinimumSize(new Dimension(820, 480));

        add(criarCabecalho(), BorderLayout.NORTH);
        add(criarPainelCentral(), BorderLayout.CENTER);
        add(criarRodape(), BorderLayout.SOUTH);
    }

    private JPanel criarCabecalho() {
        JPanel cabecalho = new JPanel(new BorderLayout());
        cabecalho.setBackground(Tema.FUNDO_PAINEL);
        cabecalho.setBorder(new EmptyBorder(20, 28, 20, 28));

        JPanel textos = new JPanel();
        textos.setOpaque(false);
        textos.setLayout(new BoxLayout(textos, BoxLayout.Y_AXIS));
        JLabel titulo = new JLabel("Cadastro de Projetos");
        titulo.setFont(Tema.FONTE_TITULO);
        titulo.setForeground(Tema.TEXTO_CLARO);
        JLabel subtitulo = new JLabel("Gerencie os projetos do seu portfolio");
        subtitulo.setFont(Tema.FONTE_SUBTITULO);
        subtitulo.setForeground(Tema.TEXTO_SECUNDARIO);
        textos.add(titulo);
        textos.add(Box.createVerticalStrut(4));
        textos.add(subtitulo);

        cabecalho.add(textos, BorderLayout.WEST);

        JPanel painelBusca = new JPanel(new BorderLayout(8, 0));
        painelBusca.setOpaque(false);
        JLabel iconeBusca = new JLabel("Buscar:");
        iconeBusca.setForeground(Tema.TEXTO_SECUNDARIO);
        iconeBusca.setFont(Tema.FONTE_SUBTITULO);
        campoBusca = Tema.criarCampoTexto();
        campoBusca.setPreferredSize(new Dimension(240, campoBusca.getPreferredSize().height));
        campoBusca.setToolTipText("Buscar projeto pelo nome");
        campoBusca.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                carregarDados(campoBusca.getText());
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                carregarDados(campoBusca.getText());
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                carregarDados(campoBusca.getText());
            }
        });
        painelBusca.add(iconeBusca, BorderLayout.WEST);
        painelBusca.add(campoBusca, BorderLayout.CENTER);

        JPanel painelDireito = new JPanel();
        painelDireito.setOpaque(false);
        painelDireito.setLayout(new BoxLayout(painelDireito, BoxLayout.Y_AXIS));
        painelDireito.add(Box.createVerticalGlue());
        painelDireito.add(painelBusca);
        painelDireito.add(Box.createVerticalGlue());

        cabecalho.add(painelDireito, BorderLayout.EAST);
        return cabecalho;
    }

    private JPanel criarPainelCentral() {
        JPanel painel = new JPanel(new BorderLayout());
        painel.setBackground(Tema.FUNDO_PRINCIPAL);
        painel.setBorder(new EmptyBorder(20, 28, 10, 28));

        tableModel = new ProjetoTableModel(List.of());
        tabela = new JTable(tableModel);
        tabela.setRowHeight(34);
        tabela.setFont(Tema.FONTE_TABELA);
        tabela.setShowGrid(false);
        tabela.setIntercellSpacing(new Dimension(0, 0));
        tabela.setBackground(Tema.FUNDO_PAINEL);
        tabela.setForeground(Tema.TEXTO_CLARO);
        tabela.setSelectionBackground(Tema.DESTAQUE);
        tabela.setSelectionForeground(Color.WHITE);
        tabela.setFillsViewportHeight(true);
        tabela.setDefaultRenderer(Object.class, new TabelaRenderer());

        JTableHeader header = tabela.getTableHeader();
        header.setFont(Tema.FONTE_TABELA_HEADER);
        header.setBackground(Tema.DESTAQUE_ESCURO);
        header.setForeground(Color.WHITE);
        header.setPreferredSize(new Dimension(0, 38));
        header.setReorderingAllowed(false);

        tabela.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                if (e.getClickCount() == 2) {
                    editarSelecionado();
                }
            }
        });

        JScrollPane scroll = new JScrollPane(tabela);
        scroll.setBorder(BorderFactory.createLineBorder(Tema.BORDA, 1));
        scroll.getViewport().setBackground(Tema.FUNDO_PAINEL);

        painel.add(scroll, BorderLayout.CENTER);
        return painel;
    }

    private JPanel criarRodape() {
        JPanel rodape = new JPanel(new BorderLayout());
        rodape.setBackground(Tema.FUNDO_PAINEL);
        rodape.setBorder(new EmptyBorder(14, 28, 14, 28));

        labelTotal = new JLabel("0 projeto(s) cadastrado(s)");
        labelTotal.setFont(Tema.FONTE_SUBTITULO);
        labelTotal.setForeground(Tema.TEXTO_SECUNDARIO);
        rodape.add(labelTotal, BorderLayout.WEST);

        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        painelBotoes.setOpaque(false);

        JButton botaoExcluir = Tema.criarBotao("Excluir", Tema.PERIGO, Tema.PERIGO_ESCURO);
        botaoExcluir.addActionListener(e -> excluirSelecionado());

        JButton botaoEditar = Tema.criarBotao("Editar", new Color(0x3B, 0x82, 0xC4), new Color(0x2E, 0x6B, 0xA3));
        botaoEditar.addActionListener(e -> editarSelecionado());

        JButton botaoNovo = Tema.criarBotao("+ Novo projeto", Tema.DESTAQUE, Tema.DESTAQUE_ESCURO);
        botaoNovo.addActionListener(e -> novoProjeto());

        painelBotoes.add(botaoExcluir);
        painelBotoes.add(botaoEditar);
        painelBotoes.add(botaoNovo);

        rodape.add(painelBotoes, BorderLayout.EAST);
        return rodape;
    }

    private void carregarDados(String filtro) {
        List<Projeto> lista = (filtro == null || filtro.isBlank())
                ? dao.listarTodos()
                : dao.buscarPorNome(filtro);
        tableModel.atualizarDados(lista);
        labelTotal.setText(lista.size() + " projeto(s) cadastrado(s)" +
                (filtro != null && !filtro.isBlank() ? " (filtrado)" : ""));
    }

    private void novoProjeto() {
        ProjetoFormDialog dialogo = new ProjetoFormDialog(this, "Novo Projeto", null);
        dialogo.setVisible(true);
        if (dialogo.isConfirmado()) {
            dao.inserir(dialogo.getProjeto());
            carregarDados(campoBusca.getText());
            mostrarMensagem("Projeto cadastrado com sucesso!", false);
        }
    }

    private void editarSelecionado() {
        Projeto selecionado = obterProjetoSelecionado();
        if (selecionado == null) {
            mostrarMensagem("Selecione um projeto na lista para editar.", true);
            return;
        }
        ProjetoFormDialog dialogo = new ProjetoFormDialog(this, "Editar Projeto", selecionado);
        dialogo.setVisible(true);
        if (dialogo.isConfirmado()) {
            dao.atualizar(dialogo.getProjeto());
            carregarDados(campoBusca.getText());
            mostrarMensagem("Projeto atualizado com sucesso!", false);
        }
    }

    private void excluirSelecionado() {
        Projeto selecionado = obterProjetoSelecionado();
        if (selecionado == null) {
            mostrarMensagem("Selecione um projeto na lista para excluir.", true);
            return;
        }
        int opcao = JOptionPane.showConfirmDialog(
                this,
                "Tem certeza que deseja excluir o projeto \"" + selecionado.getNome() + "\"?",
                "Confirmar exclusao",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE
        );
        if (opcao == JOptionPane.YES_OPTION) {
            dao.excluir(selecionado.getId());
            carregarDados(campoBusca.getText());
            mostrarMensagem("Projeto excluido com sucesso!", false);
        }
    }

    private Projeto obterProjetoSelecionado() {
        int linha = tabela.getSelectedRow();
        if (linha == -1) {
            return null;
        }
        return tableModel.getProjetoNaLinha(linha);
    }

    private void mostrarMensagem(String texto, boolean aviso) {
        JOptionPane.showMessageDialog(
                this,
                texto,
                aviso ? "Aviso" : "Sucesso",
                aviso ? JOptionPane.WARNING_MESSAGE : JOptionPane.INFORMATION_MESSAGE
        );
    }
}
