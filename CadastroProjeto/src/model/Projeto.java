package model;

import java.io.Serializable;
import java.time.LocalDate;

public class Projeto implements Serializable {

    private static final long serialVersionUID = 1L;

    public enum StatusProjeto {
        PLANEJAMENTO("Planejamento"),
        EM_ANDAMENTO("Em andamento"),
        PAUSADO("Pausado"),
        CONCLUIDO("Concluido"),
        CANCELADO("Cancelado");

        private final String descricao;

        StatusProjeto(String descricao) {
            this.descricao = descricao;
        }

        @Override
        public String toString() {
            return descricao;
        }
    }

    private int id;
    private String nome;
    private String descricao;
    private LocalDate dataInicio;
    private double orcamento;
    private StatusProjeto status;
    private boolean concluido;

    public Projeto() {
    }

    public Projeto(int id, String nome, String descricao, LocalDate dataInicio,
                    double orcamento, StatusProjeto status, boolean concluido) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.dataInicio = dataInicio;
        this.orcamento = orcamento;
        this.status = status;
        this.concluido = concluido;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }

    public double getOrcamento() {
        return orcamento;
    }

    public void setOrcamento(double orcamento) {
        this.orcamento = orcamento;
    }

    public StatusProjeto getStatus() {
        return status;
    }

    public void setStatus(StatusProjeto status) {
        this.status = status;
    }

    public boolean isConcluido() {
        return concluido;
    }

    public void setConcluido(boolean concluido) {
        this.concluido = concluido;
    }

    @Override
    public String toString() {
        return "Projeto #" + id + " - " + nome;
    }
}
