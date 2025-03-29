package br.com.representacaoexternadedados.service;

import br.com.representacaoexternadedados.dto.DisciplinaDTO;
import br.com.representacaoexternadedados.entity.Curso;
import br.com.representacaoexternadedados.entity.Disciplina;
import br.com.representacaoexternadedados.repository.CursoRepository;
import br.com.representacaoexternadedados.repository.DisciplinaRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class DisciplinaServiceTest {

    static {
        MySQLTestContainer.getInstance();
    }

    @Autowired
    private DisciplinaRepository disciplinaRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private DisciplinaService disciplinaService;

    @BeforeEach
    void setup() {
        cursoRepository.deleteAll();
        disciplinaRepository.deleteAll();
    }

    @Test
    void shouldSaveDisciplina() {

        Curso curso = new Curso();
        curso.setNome("Engenharia de Software");
        curso = cursoRepository.save(curso);

        DisciplinaDTO disciplinaDTO = new DisciplinaDTO();
        disciplinaDTO.setNome("Matemática");
        disciplinaDTO.setProfessor("Vini");
        disciplinaDTO.setCurso(curso);

        Disciplina resultado = disciplinaService.createDisciplina(disciplinaDTO);

        Assertions.assertNotNull(resultado);
        Assertions.assertEquals("Matemática", resultado.getNome());
        Assertions.assertEquals("Vini", resultado.getProfessor());
    }
}
