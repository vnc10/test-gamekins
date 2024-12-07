package br.com.representacaoexternadedados.entity;

import br.com.representacaoexternadedados.dto.MatriculaDTO;
import javax.persistence.*;

@Entity
@Table(name = "matricula")
public class Matricula {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "ano")
    private int ano;

    @Column(name = "semestre")
    private int semestre;

    @Column(name = "nota")
    private float nota;

    @Column(name = "faltas")
    private int faltas;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "codigo_disciplina")
    private Disciplina disciplina;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ra_aluno")
    private Aluno aluno;

    public Matricula(MatriculaDTO matriculaDTO) {
        this.ano = matriculaDTO.getAno();
        this.semestre = matriculaDTO.getSemestre();
        this.nota = matriculaDTO.getNota();
        this.faltas = matriculaDTO.getFaltas();
        this.disciplina = matriculaDTO.getDisciplina();
        this.aluno = matriculaDTO.getAluno();
    }

    public Matricula() {

    }

    public Long getId() {
        return id;
    }

    public int getAno() {
        return ano;
    }

    public int getSemestre() {
        return semestre;
    }

    public float getNota() {
        return nota;
    }

    public int getFaltas() {
        return faltas;
    }

    public Disciplina getDisciplina() {
        return disciplina;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public void setSemestre(int semestre) {
        this.semestre = semestre;
    }

    public void setNota(float nota) {
        this.nota = nota;
    }

    public void setFaltas(int faltas) {
        this.faltas = faltas;
    }

    public void setDisciplina(Disciplina disciplina) {
        this.disciplina = disciplina;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }
}
