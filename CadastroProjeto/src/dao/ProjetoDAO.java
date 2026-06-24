package dao;

import model.Projeto;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
public class ProjetoDAO {

    private static final String DIRETORIO = "data";
    private static final String ARQUIVO = DIRETORIO + File.separator + "projetos.dat";

    private List<Projeto> projetos;
    private int proximoId;

    public ProjetoDAO() {
        this.projetos = new ArrayList<>();
        this.proximoId = 1;
        carregar();
    }

    public Projeto inserir(Projeto projeto) {
        projeto.setId(proximoId++);
        projetos.add(projeto);
        salvar();
        return projeto;
    }

    public List<Projeto> listarTodos() {
        return new ArrayList<>(projetos);
    }

    public Projeto buscarPorId(int id) {
        for (Projeto p : projetos) {
            if (p.getId() == id) {
                return p;
            }
        }
        return null;
    }

    public List<Projeto> buscarPorNome(String termo) {
        List<Projeto> resultado = new ArrayList<>();
        if (termo == null || termo.isBlank()) {
            return listarTodos();
        }
        String termoLower = termo.toLowerCase();
        for (Projeto p : projetos) {
            if (p.getNome() != null && p.getNome().toLowerCase().contains(termoLower)) {
                resultado.add(p);
            }
        }
        return resultado;
    }

    public boolean atualizar(Projeto projetoAtualizado) {
        for (int i = 0; i < projetos.size(); i++) {
            if (projetos.get(i).getId() == projetoAtualizado.getId()) {
                projetos.set(i, projetoAtualizado);
                salvar();
                return true;
            }
        }
        return false;
    }

    public boolean excluir(int id) {
        boolean removido = projetos.removeIf(p -> p.getId() == id);
        if (removido) {
            salvar();
        }
        return removido;
    }

    @SuppressWarnings("unchecked")
    private void salvar() {
        File dir = new File(DIRETORIO);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(ARQUIVO))) {
            out.writeInt(proximoId);
            out.writeObject(projetos);
        } catch (IOException e) {
            throw new RuntimeException("Erro ao salvar dados no arquivo: " + e.getMessage(), e);
        }
    }

    @SuppressWarnings("unchecked")
    private void carregar() {
        File arquivo = new File(ARQUIVO);
        if (!arquivo.exists()) {
            return;
        }
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(arquivo))) {
            proximoId = in.readInt();
            projetos = (List<Projeto>) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException("Erro ao carregar dados do arquivo: " + e.getMessage(), e);
        }
    }
}
