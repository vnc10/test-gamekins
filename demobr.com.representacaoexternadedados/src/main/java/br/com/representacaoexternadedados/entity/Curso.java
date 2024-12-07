package br.com.representacaoexternadedados.entity;

import br.com.representacaoexternadedados.dto.CursoDTO;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "curso")
public class Curso implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codigo")
    private Long codigo;

    @Column(name = "nome")
    private String nome;

    public Curso(CursoDTO cursoDTO){
        this.nome = cursoDTO.getNome();
    }


    public Curso() {

    }

    public Long getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
    }
}
