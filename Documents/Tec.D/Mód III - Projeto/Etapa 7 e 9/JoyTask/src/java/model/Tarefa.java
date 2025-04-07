package model;

import java.time.LocalDate;

public class Tarefa {
    private int id;
    private String titulo;
    private String descricao;
    private String dataInicio;
    private String dataFim;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setDataInicio(String dataInicio) {
        this.dataInicio = dataInicio.toString();

    }

    public String getDataInicio() {
        return dataInicio;
    }

    public void setDataFim(String dataFim) {
        this.dataFim = dataFim.toString();
    }

    public String getDataFim() {
        return dataFim;
    }
}

