package model;

import java.util.List;

public class Equipe {
    private int id;
    private String nome;

    // Dados de colaboradores para exibição
    private String colaboradorNome;
    private String colaboradorEmail;
    private String colaboradorNivel;

    // IDs dos colaboradores para relacionamento
    private List<Integer> colaboradoresIds;

    // Getters e Setters básicos
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

    // Dados do colaborador (para exibição)
    public String getColaboradorNome() {
        return colaboradorNome;
    }
    public void setColaboradorNome(String colaboradorNome) {
        this.colaboradorNome = colaboradorNome;
    }

    public String getColaboradorEmail() {
        return colaboradorEmail;
    }
    public void setColaboradorEmail(String colaboradorEmail) {
        this.colaboradorEmail = colaboradorEmail;
    }

    public String getColaboradorNivel() {
        return colaboradorNivel;
    }
    public void setColaboradorNivel(String colaboradorNivel) {
        this.colaboradorNivel = colaboradorNivel;
    }

    // Lista de IDs dos colaboradores (para salvar no banco)
    public List<Integer> getColaboradoresIds() {
        return colaboradoresIds;
    }
    public void setColaboradoresIds(List<Integer> colaboradoresIds) {
        this.colaboradoresIds = colaboradoresIds;
    }
}
