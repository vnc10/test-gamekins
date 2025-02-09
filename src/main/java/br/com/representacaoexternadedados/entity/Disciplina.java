package br.com.representacaoexternadedados.entity;

import br.com.representacaoexternadedados.dto.DisciplinaDTO;

import javax.persistence.*;

@Entity
@Table(name = "disciplina")
public class Disciplina {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codigo")
    private Long codigo;

    @Column(name = "nome")
    private String nome;

    @Column(name = "professor")
    private String professor;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "codigo_curso")
    private Curso curso;



    public Disciplina(DisciplinaDTO disciplinaDTO) {
        this.nome = disciplinaDTO.getNome();
        this.professor = disciplinaDTO.getProfessor();
        this.curso = disciplinaDTO.getCurso();
    }

    public Disciplina() {

    }

    public Long getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
    }

    public String getProfessor() {
        return professor;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setProfessor(String professor) {
        this.professor = professor;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }
}
