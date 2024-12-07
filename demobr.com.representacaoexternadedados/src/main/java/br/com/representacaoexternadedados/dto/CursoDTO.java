package br.com.representacaoexternadedados.dto;

public class CursoDTO {

    private String nome;


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public CursoDTO(String nome) {
        this.nome = nome;
    }
}
