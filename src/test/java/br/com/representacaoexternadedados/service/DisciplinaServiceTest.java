package br.com.representacaoexternadedados.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import br.com.representacaoexternadedados.dto.CursoDTO;
import br.com.representacaoexternadedados.dto.DisciplinaDTO;
import br.com.representacaoexternadedados.entity.Curso;
import static org.mockito.ArgumentMatchers.any;
import br.com.representacaoexternadedados.entity.Disciplina;
import br.com.representacaoexternadedados.repository.DisciplinaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class DisciplinaServiceTest {

    @Mock
    private DisciplinaRepository disciplinaRepository;

    private DisciplinaService disciplinaService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); // Inicializa os mocks
        disciplinaService = new DisciplinaService(disciplinaRepository); // Constrói o serviço
    }

    @Test
    void testDisciplinaServiceConstructor() {
        assertNotNull(disciplinaService, "O serviço não deveria ser nulo após a construção.");
    }

    @Test
    void testCreateDisciplina() {
        DisciplinaDTO disciplinaDTO = new DisciplinaDTO();
        disciplinaDTO.setNome("asdadsads");
        Disciplina disciplinaEsperada = new Disciplina(disciplinaDTO);

        when(disciplinaRepository.save(any(Disciplina.class))).thenReturn(disciplinaEsperada);

        Disciplina disciplinaRetornada = disciplinaService.createDisciplina(disciplinaDTO);

        assertEquals(disciplinaEsperada, disciplinaRetornada, "A disciplina retornada deve ser igual à esperada.");
        verify(disciplinaRepository).save(disciplinaEsperada);
    }
}