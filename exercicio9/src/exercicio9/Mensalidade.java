package exercicio9;

public class Mensalidade {

    public double calcularValor(Plano plano, Duracao duracao, Frequencia frequencia) {
        double valorBase = plano.getValorBase();
        double valorComFrequencia = valorBase * (1 + frequencia.getPercentualAcrescimo());
        double valorFinal = valorComFrequencia * (1 - duracao.getPercentualDesconto());
        return valorFinal;
    }
}
