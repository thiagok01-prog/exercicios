package exercicio9;

public enum Frequencia {

    DUAS_VEZES("2x por semana", 0.0),
    TRES_VEZES("3x por semana", 0.20),
    CINCO_VEZES("5x por semana", 0.50);

    private final String descricao;
    private final double percentualAcrescimo;

    Frequencia(String descricao, double percentualAcrescimo) {
        this.descricao = descricao;
        this.percentualAcrescimo = percentualAcrescimo;
    }

    public double getPercentualAcrescimo() {
        return percentualAcrescimo;
    }

    @Override
    public String toString() {
        return descricao;
    }
}
