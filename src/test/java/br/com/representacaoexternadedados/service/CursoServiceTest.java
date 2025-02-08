package br.com.representacaoexternadedados.service;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import br.com.representacaoexternadedados.dto.CursoDTO;
import br.com.representacaoexternadedados.entity.Curso;
import br.com.representacaoexternadedados.repository.CursoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

class CursoServiceTest {


    @Mock
    private CursoRepository cursoRepository;


    @InjectMocks
    private CursoServiceImplem cursoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateCurso() {
        CursoDTO cursoDTO = new CursoDTO("teste"); // Suponha que tenha valores necessários
        Curso curso = new Curso(cursoDTO);

        when(cursoRepository.save(any(Curso.class))).thenReturn(curso);

        Curso result = cursoService.createCurso(cursoDTO);

        assertNotNull(result);
        verify(cursoRepository, times(1)).save(any(Curso.class));
    }

    @Test
    void testFindAll() {
        Curso curso1 = new Curso();
        Curso curso2 = new Curso();
        List<Curso> cursos = Arrays.asList(curso1, curso2);

        when(cursoRepository.findAll()).thenReturn(cursos);

        List<Curso> result = cursoService.findAll();

        assertNotNull(result);
        assertEquals(2, result.size());
        verify(cursoRepository, times(1)).findAll();
    }
}
