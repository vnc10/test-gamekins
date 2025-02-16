package br.com.representacaoexternadedados.service;

import br.com.representacaoexternadedados.dto.MatriculaDTO;
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
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Testcontainers
public class MatriculaControllerTest {

    @Container
    static MySQLContainer<?> mysql = new MySQLContainer<>("mysql:8.0")
            .withDatabaseName("testdb")
            .withUsername("test")
            .withPassword("test");

    @Autowired
    private MatriculaRepository matriculaRepository;

    @Autowired
    private TestRestTemplate restTemplate;

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
        alunoRepository.deleteAll();
        disciplinaRepository.deleteAll();
        cursoRepository.deleteAll();
    }

    @Test
    void createMatricula() {

        Curso curso = new Curso();
        curso.setCodigo(1L);
        curso.setNome("Engenharia de Software");
        cursoRepository.save(curso);

        Disciplina disciplina = new Disciplina();
        disciplina.setCodigo(1L);
        disciplina.setNome("Matemática");
        disciplina.setProfessor("Vini");
        disciplina.setCurso(curso);
        disciplinaRepository.save(disciplina);

        Aluno aluno = new Aluno();
        aluno.setRa(1L);
        aluno.setNome("João Silva");
        aluno.setPeriodo(2);
        aluno.setCurso(curso);
        alunoRepository.save(aluno);

        MatriculaDTO matriculaDTO = new MatriculaDTO();
        matriculaDTO.setAno(2023);
        matriculaDTO.setSemestre(1);
        matriculaDTO.setNota(8.5f);
        matriculaDTO.setFaltas(2);
        matriculaDTO.setDisciplina(disciplina);
        matriculaDTO.setAluno(aluno);

        ResponseEntity<Matricula> response = restTemplate.postForEntity(
                "/sd/matricula",
                matriculaDTO,
                Matricula.class
        );

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertNotNull(response.getBody());

        Matricula matriculaSalva = response.getBody();
        Assertions.assertEquals(2023, matriculaSalva.getAno());
        Assertions.assertEquals(1, matriculaSalva.getSemestre());
        Assertions.assertEquals(8.5f, matriculaSalva.getNota());
        Assertions.assertEquals(2, matriculaSalva.getFaltas());
        Assertions.assertEquals(disciplina.getCodigo(), matriculaSalva.getDisciplina().getCodigo());
        Assertions.assertEquals(aluno.getRa(), matriculaSalva.getAluno().getRa());

    }

}
