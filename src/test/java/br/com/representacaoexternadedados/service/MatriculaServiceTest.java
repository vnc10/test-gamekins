package br.com.representacaoexternadedados.service;

import br.com.representacaoexternadedados.entity.Matricula;
import br.com.representacaoexternadedados.repository.MatriculaRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MatriculaServiceTest {

    @Mock
    private MatriculaRepository matriculaRepository;

    @InjectMocks
    private MatriculaService matriculaService;

    @Test
    void shouldThrowExceptionWhenMatriculaNotFound() {
        Long matriculaId = 1L;

        when(matriculaRepository.findById(matriculaId)).thenReturn(Optional.empty());

        Assertions.assertThrows(
                ResponseStatusException.class,
                () -> matriculaService.getMatricula(matriculaId)
        );


        verify(matriculaRepository, times(1)).findById(matriculaId);
    }
}