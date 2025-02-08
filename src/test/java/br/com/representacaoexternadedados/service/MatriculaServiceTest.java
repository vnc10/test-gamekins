package br.com.representacaoexternadedados.service;

import br.com.representacaoexternadedados.dto.AlunoDTO;
import br.com.representacaoexternadedados.dto.CursoDTO;
import br.com.representacaoexternadedados.dto.MatriculaDTO;
import br.com.representacaoexternadedados.entity.Aluno;
import br.com.representacaoexternadedados.entity.Curso;
import br.com.representacaoexternadedados.entity.Matricula;
import br.com.representacaoexternadedados.repository.AlunoRepository;
import br.com.representacaoexternadedados.repository.MatriculaRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class MatriculaServiceTest {

    @Mock
    private MatriculaRepository matriculaRepository;

    @InjectMocks
    private MatriculaService matriculaService;

    public MatriculaServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void teste() {
        Long id = 1L;
        when(matriculaRepository.findById(id)).thenReturn(Optional.empty());

        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            matriculaService.updateNota(id, new MatriculaDTO());
        });

        assert(exception.getStatus().equals(HttpStatus.NO_CONTENT));
    }

    @Test
    void teste2() {
        MatriculaDTO matriculaDTO = new MatriculaDTO(); // Suponha que tenha valores necessários
        Matricula matricula = new Matricula(matriculaDTO);

        when(matriculaRepository.save(any(Matricula.class))).thenReturn(matricula);

        Matricula result = matriculaService.createMatricula(matriculaDTO);

        assertNotNull(result);
        verify(matriculaRepository, times(1)).save(any(Matricula.class));
    }
}