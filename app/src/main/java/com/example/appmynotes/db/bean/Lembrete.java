package com.example.appmynotes.db.bean;

public class Lembrete {

    private int id;
    private String titulo;
    private String texto;
    private Prioridade prioridade;

    public Lembrete(int id, String titulo, String texto) {
        this.id = id;
        this.titulo = titulo;
        this.texto = texto;

    }

    public Lembrete(String titulo, String texto) {
        this.titulo = titulo;
        this.texto = texto;
    }

    public Lembrete(int id, String titulo, String texto, Prioridade prioridade) {
        this.id = id;
        this.titulo = titulo;
        this.texto = texto;
        this.prioridade = prioridade;
    }
    public Lembrete(String titulo, String texto, Prioridade prioridade) {
        this.titulo = titulo;
        this.texto = texto;
        this.prioridade = prioridade;
    }

    public Lembrete() {
        this.setId(0);
        this.setTitulo("");
        this.setTexto("");
    }

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

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public Prioridade getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(Prioridade prioridade) {
        this.prioridade = prioridade;
    }

    @Override
    public String toString() {
        return "Lembrete{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", texto='" + texto + '\'' +
                "" +this.getPrioridade().toString() +
                '}';
    }
}
