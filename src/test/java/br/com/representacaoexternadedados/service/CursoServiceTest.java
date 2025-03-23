package br.com.representacaoexternadedados.service;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import br.com.representacaoexternadedados.dto.CursoDTO;
import br.com.representacaoexternadedados.entity.Curso;
import br.com.representacaoexternadedados.repository.CursoRepository;
import org.junit.jupiter.api.Assertions;
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
        CursoDTO cursoDTO = new CursoDTO("teste");
        Curso curso = new Curso(cursoDTO);

        when(cursoRepository.save(any(Curso.class))).thenReturn(curso);

        Curso result = cursoService.createCurso(cursoDTO);

        assertNotNull(result);
        verify(cursoRepository, times(1)).save(any(Curso.class));
    }

    @Test
    void testCreateCursoWithNewParameters() {
        CursoDTO cursoDTO = new CursoDTO("BCC");
        Curso curso = new Curso(cursoDTO);

        when(cursoRepository.save(any(Curso.class))).thenReturn(curso);

        Curso result = cursoService.createCurso(cursoDTO);

        assertNotNull(result);
        verify(cursoRepository, times(1)).save(any(Curso.class));
    }


    @Test
    void shouldReturnAllCursos() {
        Curso curso1 = new Curso();
        curso1.setNome("Engenharia de Software");

        Curso curso2 = new Curso();
        curso2.setNome("Ciência da Computação");

        List<Curso> cursosMock = Arrays.asList(curso1, curso2);

        when(cursoRepository.findAll()).thenReturn(cursosMock);

        List<Curso> resultado = cursoService.findAll();

        Assertions.assertNotNull(resultado);
        Assertions.assertEquals(2, resultado.size());
        Assertions.assertEquals("Engenharia de Software", resultado.get(0).getNome());
        Assertions.assertEquals("Ciência da Computação", resultado.get(1).getNome());

        verify(cursoRepository, times(1)).findAll();
    }

}