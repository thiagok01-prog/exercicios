package exercicio9;

public enum Duracao {

    MENSAL("Mensal", 0.0),
    SEMESTRAL("Semestral", 0.10),
    ANUAL("Anual", 0.20);

    private final String descricao;
    private final double percentualDesconto;

    Duracao(String descricao, double percentualDesconto) {
        this.descricao = descricao;
        this.percentualDesconto = percentualDesconto;
    }

    public double getPercentualDesconto() {
        return percentualDesconto;
    }

    @Override
    public String toString() {
        return descricao;
    }
}
