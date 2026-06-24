package view;

import model.Projeto;
import util.Validador;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.time.format.DateTimeFormatter;

public class ProjetoFormDialog extends JDialog {

    private JTextField campoNome;
    private JTextArea campoDescricao;
    private JTextField campoData;
    private JTextField campoOrcamento;
    private JComboBox<Projeto.StatusProjeto> campoStatus;
    private JCheckBox campoConcluido;

    private JLabel erroNome;
    private JLabel erroDescricao;
    private JLabel erroData;
    private JLabel erroOrcamento;
    private JLabel erroStatus;

    private boolean confirmado = false;
    private Projeto projetoEditado;

    public ProjetoFormDialog(Window owner, String titulo, Projeto projetoExistente) {
        super(owner, titulo, ModalityType.APPLICATION_MODAL);
        this.projetoEditado = projetoExistente;
        construirInterface();
        if (projetoExistente != null) {
            preencherCampos(projetoExistente);
        }
        pack();
        setMinimumSize(new Dimension(480, getHeight()));
        setLocationRelativeTo(owner);
        setResizable(false);
    }

    private void construirInterface() {
        getContentPane().setBackground(Tema.FUNDO_PRINCIPAL);
        setLayout(new BorderLayout());

        JPanel painelForm = new JPanel();
        painelForm.setBackground(Tema.FUNDO_PRINCIPAL);
        painelForm.setBorder(new EmptyBorder(24, 28, 10, 28));
        painelForm.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.weightx = 1.0;
        gbc.insets = new Insets(6, 0, 0, 0);
        int linha = 0;

        gbc.gridy = linha++;
        painelForm.add(Tema.criarLabel("Nome do projeto *"), gbc);
        gbc.gridy = linha++;
        campoNome = Tema.criarCampoTexto();
        painelForm.add(campoNome, gbc);
        gbc.gridy = linha++;
        erroNome = Tema.criarLabelErro();
        painelForm.add(erroNome, gbc);

        gbc.gridy = linha++;
        gbc.insets = new Insets(14, 0, 0, 0);
        painelForm.add(Tema.criarLabel("Descricao *"), gbc);
        gbc.gridy = linha++;
        gbc.insets = new Insets(6, 0, 0, 0);
        campoDescricao = Tema.criarAreaTexto(3);
        JScrollPane scrollDescricao = new JScrollPane(campoDescricao);
        scrollDescricao.setBorder(BorderFactory.createLineBorder(Tema.BORDA, 1, true));
        painelForm.add(scrollDescricao, gbc);
        gbc.gridy = linha++;
        erroDescricao = Tema.criarLabelErro();
        painelForm.add(erroDescricao, gbc);

        gbc.gridy = linha++;
        gbc.insets = new Insets(14, 0, 0, 0);
        JPanel linhaDataOrcamento = new JPanel(new GridLayout(1, 2, 16, 0));
        linhaDataOrcamento.setOpaque(false);

        JPanel colData = new JPanel(new BorderLayout(0, 6));
        colData.setOpaque(false);
        colData.add(Tema.criarLabel("Data de inicio * (dd/mm/aaaa)"), BorderLayout.NORTH);
        campoData = Tema.criarCampoTexto();
        colData.add(campoData, BorderLayout.CENTER);
        erroData = Tema.criarLabelErro();
        colData.add(erroData, BorderLayout.SOUTH);

        JPanel colOrcamento = new JPanel(new BorderLayout(0, 6));
        colOrcamento.setOpaque(false);
        colOrcamento.add(Tema.criarLabel("Orcamento (R$) *"), BorderLayout.NORTH);
        campoOrcamento = Tema.criarCampoTexto();
        colOrcamento.add(campoOrcamento, BorderLayout.CENTER);
        erroOrcamento = Tema.criarLabelErro();
        colOrcamento.add(erroOrcamento, BorderLayout.SOUTH);

        linhaDataOrcamento.add(colData);
        linhaDataOrcamento.add(colOrcamento);
        painelForm.add(linhaDataOrcamento, gbc);

        gbc.gridy = linha++;
        gbc.insets = new Insets(14, 0, 0, 0);
        painelForm.add(Tema.criarLabel("Status *"), gbc);
        gbc.gridy = linha++;
        gbc.insets = new Insets(6, 0, 0, 0);
        campoStatus = new JComboBox<>(Projeto.StatusProjeto.values());
        Tema.estilizarComboBox(campoStatus);
        painelForm.add(campoStatus, gbc);
        gbc.gridy = linha++;
        erroStatus = Tema.criarLabelErro();
        painelForm.add(erroStatus, gbc);

        gbc.gridy = linha++;
        gbc.insets = new Insets(10, 0, 0, 0);
        campoConcluido = new JCheckBox("Projeto concluido");
        campoConcluido.setOpaque(false);
        campoConcluido.setForeground(Tema.TEXTO_CLARO);
        campoConcluido.setFont(Tema.FONTE_LABEL);
        campoConcluido.setFocusPainted(false);
        campoConcluido.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        painelForm.add(campoConcluido, gbc);

        JScrollPane scrollPrincipal = new JScrollPane(painelForm);
        scrollPrincipal.setBorder(BorderFactory.createEmptyBorder());
        scrollPrincipal.getViewport().setBackground(Tema.FUNDO_PRINCIPAL);
        scrollPrincipal.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        add(scrollPrincipal, BorderLayout.CENTER);

        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 14));
        painelBotoes.setBackground(Tema.FUNDO_PRINCIPAL);
        painelBotoes.setBorder(new EmptyBorder(0, 0, 6, 22));

        JButton botaoCancelar = Tema.criarBotao("Cancelar", new Color(0x55, 0x60, 0x6B), new Color(0x66, 0x72, 0x7D));
        botaoCancelar.addActionListener(e -> dispose());

        JButton botaoSalvar = Tema.criarBotao("Salvar", Tema.DESTAQUE, Tema.DESTAQUE_ESCURO);
        botaoSalvar.addActionListener(e -> salvar());

        painelBotoes.add(botaoCancelar);
        painelBotoes.add(botaoSalvar);
        add(painelBotoes, BorderLayout.SOUTH);

        getRootPane().setDefaultButton(botaoSalvar);
    }

    private void preencherCampos(Projeto p) {
        campoNome.setText(p.getNome());
        campoDescricao.setText(p.getDescricao());
        if (p.getDataInicio() != null) {
            campoData.setText(p.getDataInicio().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        }
        campoOrcamento.setText(String.valueOf(p.getOrcamento()).replace(".", ","));
        campoStatus.setSelectedItem(p.getStatus());
        campoConcluido.setSelected(p.isConcluido());
    }

    private boolean validarFormulario() {
        boolean valido = true;

        limparErros();

        String erro;

        erro = Validador.validarNome(campoNome.getText());
        if (erro != null) {
            erroNome.setText(erro);
            Tema.marcarErro(campoNome);
            valido = false;
        }

        erro = Validador.validarDescricao(campoDescricao.getText());
        if (erro != null) {
            erroDescricao.setText(erro);
            campoDescricao.setBorder(BorderFactory.createLineBorder(Tema.PERIGO, 2, true));
            valido = false;
        }

        erro = Validador.validarData(campoData.getText());
        if (erro != null) {
            erroData.setText(erro);
            Tema.marcarErro(campoData);
            valido = false;
        }

        erro = Validador.validarOrcamento(campoOrcamento.getText());
        if (erro != null) {
            erroOrcamento.setText(erro);
            Tema.marcarErro(campoOrcamento);
            valido = false;
        }

        erro = Validador.validarStatus(campoStatus.getSelectedItem());
        if (erro != null) {
            erroStatus.setText(erro);
            valido = false;
        }

        return valido;
    }

    private void limparErros() {
        erroNome.setText(" ");
        erroDescricao.setText(" ");
        erroData.setText(" ");
        erroOrcamento.setText(" ");
        erroStatus.setText(" ");
        Tema.limparErro(campoNome);
        Tema.limparErro(campoData);
        Tema.limparErro(campoOrcamento);
        campoDescricao.setBorder(BorderFactory.createLineBorder(Tema.BORDA, 1, true));
    }

    private void salvar() {
        if (!validarFormulario()) {
            return;
        }

        if (projetoEditado == null) {
            projetoEditado = new Projeto();
        }

        projetoEditado.setNome(campoNome.getText().trim());
        projetoEditado.setDescricao(campoDescricao.getText().trim());
        projetoEditado.setDataInicio(Validador.converterData(campoData.getText()));
        projetoEditado.setOrcamento(Validador.converterOrcamento(campoOrcamento.getText()));
        projetoEditado.setStatus((Projeto.StatusProjeto) campoStatus.getSelectedItem());
        projetoEditado.setConcluido(campoConcluido.isSelected());

        confirmado = true;
        dispose();
    }

    public boolean isConfirmado() {
        return confirmado;
    }

    public Projeto getProjeto() {
        return projetoEditado;
    }
}
