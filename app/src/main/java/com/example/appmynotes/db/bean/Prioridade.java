package com.example.appmynotes.db.bean;

public class Prioridade {

    private long id;
    private String descricao;

    public Prioridade(long id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }

    public Prioridade(String descricao) {
        this.descricao = descricao;
    }

    public Prioridade() {
        this.setId(0);
        this.setDescricao("");
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return "Prioridade{" +
                "id=" + id +
                ", descricao='" + descricao + '\'' +
                '}';
    }
}
