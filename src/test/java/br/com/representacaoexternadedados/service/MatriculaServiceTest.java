package br.com.representacaoexternadedados.service;

import br.com.representacaoexternadedados.entity.Aluno;
import br.com.representacaoexternadedados.entity.Curso;
import br.com.representacaoexternadedados.entity.Disciplina;
import br.com.representacaoexternadedados.entity.Matricula;
import br.com.representacaoexternadedados.repository.AlunoRepository;
import br.com.representacaoexternadedados.repository.CursoRepository;
import br.com.representacaoexternadedados.repository.DisciplinaRepository;
import br.com.representacaoexternadedados.repository.MatriculaRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.server.ResponseStatusException;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.junit.Assert.assertThrows;


@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Testcontainers
class MatriculaServiceTest {

    @Container
    static MySQLContainer<?> mysql = new MySQLContainer<>("mysql:8.0")
            .withDatabaseName("testdb")
            .withUsername("test")
            .withPassword("test");

    @Autowired
    private MatriculaRepository matriculaRepository;

    @Autowired
    private MatriculaService matriculaService;

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private DisciplinaRepository disciplinaRepository;

    @Autowired
    private AlunoRepository alunoRepository;

    static {
        mysql.start();
        System.setProperty("spring.datasource.url", mysql.getJdbcUrl());
        System.setProperty("spring.datasource.username", mysql.getUsername());
        System.setProperty("spring.datasource.password", mysql.getPassword());
        System.setProperty("spring.datasource.driver-class-name", "com.mysql.cj.jdbc.Driver");
    }
    @BeforeEach
    void setup() {
        matriculaRepository.deleteAll();
    }

    @Test
    void deveRetornarMatriculaQuandoIdExistir() {

        Curso curso = new Curso();
        curso.setNome("Engenharia de Software");
        cursoRepository.save(curso);

        Disciplina disciplina = new Disciplina();
        disciplina.setNome("Matemática");
        disciplina.setProfessor("Vini");
        disciplina.setCurso(curso);
        disciplinaRepository.save(disciplina);

        Aluno aluno = new Aluno();
        aluno.setNome("João Silva");
        aluno.setPeriodo(2);
        aluno.setCurso(curso);
        alunoRepository.save(aluno);

        Matricula matricula = new Matricula();
        matricula.setAno(2023);
        matricula.setSemestre(1);
        matricula.setNota(8.5f);
        matricula.setFaltas(2);
        matricula.setDisciplina(disciplina);
        matricula.setAluno(aluno);

        matriculaRepository.save(matricula);

        Matricula resultado = matriculaService.getMatricula(1L);

        Assertions.assertNotNull(resultado);
        Assertions.assertEquals(1L, resultado.getId());
    }

    @Test
    void deveLancarExcecaoQuandoIdNaoExistir() {
        assertThrows(ResponseStatusException.class, () -> matriculaService.getMatricula(999L));
    }

}