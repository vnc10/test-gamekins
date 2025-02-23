package br.com.representacaoexternadedados.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;

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
}