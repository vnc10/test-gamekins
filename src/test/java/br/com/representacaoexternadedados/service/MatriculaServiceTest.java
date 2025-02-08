package br.com.representacaoexternadedados.service;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import br.com.representacaoexternadedados.dto.MatriculaDTO;
import br.com.representacaoexternadedados.entity.Matricula;
import br.com.representacaoexternadedados.repository.MatriculaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

class MatriculaServiceTest {

    @Mock
    private MatriculaRepository matriculaRepository;

    @InjectMocks
    private MatriculaService matriculaService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void testUpdateNota() {
        Long id = 1L;
        MatriculaDTO matriculaDTO = new MatriculaDTO(); // Defina os valores necessários
        matriculaDTO.setNota(9.5F);
        Matricula matricula = new Matricula();

        when(matriculaRepository.findById(id)).thenReturn(Optional.of(matricula));
        when(matriculaRepository.save(any(Matricula.class))).thenReturn(matricula);

        Matricula result = matriculaService.updateNota(id, matriculaDTO);

        assertNotNull(result);
        assertEquals(matriculaDTO.getNota(), result.getNota());
        verify(matriculaRepository, times(1)).save(matricula);
    }
}

