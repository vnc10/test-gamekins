package br.com.representacaoexternadedados.service;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import br.com.representacaoexternadedados.dto.AlunoDTO;
import br.com.representacaoexternadedados.dto.CursoDTO;
import br.com.representacaoexternadedados.entity.Aluno;
import br.com.representacaoexternadedados.entity.Curso;
import br.com.representacaoexternadedados.repository.AlunoRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class AlunoServiceTest {

    @Mock
    private AlunoRepository alunoRepository;

    @InjectMocks
    private AlunoServiceImplem alunoServiceImplem;

    public AlunoServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateAluno() {
        Curso curso = new Curso(new CursoDTO("Engenharia de Software"));
        AlunoDTO alunoDTO = new AlunoDTO("João", 2, curso);

        Aluno expectedAluno = new Aluno(alunoDTO);

        when(alunoRepository.save(any(Aluno.class))).thenReturn(expectedAluno);

        Aluno createdAluno = alunoServiceImplem.createAluno(alunoDTO);

        assertNotNull(createdAluno);
        assertEquals("João", createdAluno.getNome());
        assertEquals(2, createdAluno.getPeriodo());
        assertEquals(curso, createdAluno.getCurso());

        verify(alunoRepository, times(1)).save(any(Aluno.class));
    }
}