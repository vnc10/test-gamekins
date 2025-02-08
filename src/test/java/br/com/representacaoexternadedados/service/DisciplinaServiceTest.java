package br.com.representacaoexternadedados.service;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import br.com.representacaoexternadedados.dto.DisciplinaDTO;
import br.com.representacaoexternadedados.entity.Disciplina;
import br.com.representacaoexternadedados.repository.DisciplinaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class DisciplinaServiceTest {

    @Mock
    private DisciplinaRepository disciplinaRepository;

    @InjectMocks
    private DisciplinaService disciplinaService;

    private DisciplinaDTO disciplinaDTO;
    private Disciplina disciplina;

    @BeforeEach
    void setUp() {
        disciplinaDTO = new DisciplinaDTO();
        disciplina = new Disciplina(disciplinaDTO);
    }

    @Test
    void testCreateDisciplina() {
        when(disciplinaRepository.save(any(Disciplina.class))).thenReturn(disciplina);

        Disciplina result = disciplinaService.createDisciplina(disciplinaDTO);

        assertNotNull(result);
        assertEquals(disciplina.getNome(), result.getNome());
        verify(disciplinaRepository, times(1)).save(any(Disciplina.class));
    }
}