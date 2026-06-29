package exercicio8;

public class Mensalidade {

    public double calcularValor(String plano, String duracao, String frequencia) {
        double valorBase = obterValorBase(plano);
        double valorComFrequencia = aplicarFrequencia(valorBase, frequencia);
        double valorFinal = aplicarDesconto(valorComFrequencia, duracao);
        return valorFinal;
    }

    private double obterValorBase(String plano) {
        switch (plano) {
            case "Básico":
                return 80.00;
            case "Intermediário":
                return 120.00;
            case "Premium":
                return 180.00;
            default:
                throw new IllegalArgumentException("Plano inválido: " + plano);
        }
    }

    private double aplicarFrequencia(double valorBase, String frequencia) {
        switch (frequencia) {
            case "2x":
                return valorBase;
            case "3x":
                return valorBase * 1.20;
            case "5x":
                return valorBase * 1.50;
            default:
                throw new IllegalArgumentException("Frequência inválida: " + frequencia);
        }
    }

    private double aplicarDesconto(double valor, String duracao) {
        switch (duracao) {
            case "Mensal":
                return valor;
            case "Semestral":
                return valor * 0.90;
            case "Anual":
                return valor * 0.80;
            default:
                throw new IllegalArgumentException("Duração inválida: " + duracao);
        }
    }
}
