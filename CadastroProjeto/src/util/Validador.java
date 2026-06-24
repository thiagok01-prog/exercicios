package util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Validador {

    public static final DateTimeFormatter FORMATO_DATA = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private Validador() {
    }

    public static String validarNome(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            return "O campo 'Nome' e obrigatorio.";
        }
        if (nome.trim().length() < 3) {
            return "O campo 'Nome' deve ter no minimo 3 caracteres.";
        }
        if (nome.trim().length() > 100) {
            return "O campo 'Nome' deve ter no maximo 100 caracteres.";
        }
        return null;
    }

    public static String validarDescricao(String descricao) {
        if (descricao == null || descricao.trim().isEmpty()) {
            return "O campo 'Descricao' e obrigatorio.";
        }
        if (descricao.trim().length() > 500) {
            return "O campo 'Descricao' deve ter no maximo 500 caracteres.";
        }
        return null;
    }

    public static String validarData(String dataTexto) {
        if (dataTexto == null || dataTexto.trim().isEmpty()) {
            return "O campo 'Data de inicio' e obrigatorio.";
        }
        try {
            LocalDate.parse(dataTexto.trim(), FORMATO_DATA);
        } catch (DateTimeParseException e) {
            return "Data invalida. Use o formato dd/MM/aaaa (ex: 25/12/2026).";
        }
        return null;
    }

    public static LocalDate converterData(String dataTexto) {
        return LocalDate.parse(dataTexto.trim(), FORMATO_DATA);
    }

    public static String validarOrcamento(String orcamentoTexto) {
        if (orcamentoTexto == null || orcamentoTexto.trim().isEmpty()) {
            return "O campo 'Orcamento' e obrigatorio.";
        }
        double valor;
        try {
            valor = converterOrcamento(orcamentoTexto);
        } catch (NumberFormatException e) {
            return "Orcamento invalido. Digite um numero, ex: 1500.50";
        }
        if (valor < 0) {
            return "O orcamento nao pode ser negativo.";
        }
        if (valor > 999_999_999.99) {
            return "O orcamento informado e muito alto.";
        }
        return null;
    }

    public static double converterOrcamento(String orcamentoTexto) {
        String texto = orcamentoTexto.trim();

        if (texto.contains(",")) {
            texto = texto.replace(".", "").replace(",", ".");
        }

        return Double.parseDouble(texto);
    }

    public static String validarStatus(Object statusSelecionado) {
        if (statusSelecionado == null) {
            return "Selecione um 'Status' para o projeto.";
        }
        return null;
    }
}
