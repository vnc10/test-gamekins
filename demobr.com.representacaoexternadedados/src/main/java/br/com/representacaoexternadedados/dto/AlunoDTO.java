package br.com.representacaoexternadedados.dto;

import br.com.representacaoexternadedados.entity.Curso;

public class AlunoDTO {

    private String nome;
    private int periodo;
    private Curso curso;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getPeriodo() {
        return periodo;
    }

    public void setPeriodo(int periodo) {
        this.periodo = periodo;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public AlunoDTO(String nome, int periodo, Curso curso) {
        this.nome = nome;
        this.periodo = periodo;
        this.curso = curso;
    }
}
