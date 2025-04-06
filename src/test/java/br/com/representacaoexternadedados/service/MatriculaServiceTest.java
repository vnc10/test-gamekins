package br.com.representacaoexternadedados.service;

import br.com.representacaoexternadedados.entity.Matricula;
import br.com.representacaoexternadedados.repository.MatriculaRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
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

        when(matriculaRepository.findById(1L)).thenReturn(Optional.empty());

        Assertions.assertThrows(
                ResponseStatusException.class,
                () -> matriculaService.getMatricula(1L)
        );


        verify(matriculaRepository, times(1)).findById(1L);
    }
}