package exercicio9;

public enum Plano {

    BASICO("Básico", 80.00),
    INTERMEDIARIO("Intermediário", 120.00),
    PREMIUM("Premium", 180.00);

    private final String descricao;
    private final double valorBase;

    Plano(String descricao, double valorBase) {
        this.descricao = descricao;
        this.valorBase = valorBase;
    }

    public double getValorBase() {
        return valorBase;
    }

    @Override
    public String toString() {
        return descricao;
    }
}
