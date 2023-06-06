package com.pratti.pesquisa.service;

import com.pratti.pesquisa.model.SuperiorModel;
import com.pratti.pesquisa.repository.SuperiorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SuperiorServiceTest {

    @Mock
    private SuperiorRepository superiorRepository;

    private SuperiorService superiorService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        superiorService = new SuperiorService(superiorRepository);
    }

    @Test
    void testFindAll() {
        // Criar uma lista de modelos de superiores esperados
        List<SuperiorModel> expectedSuperiors = Arrays.asList(
                new SuperiorModel(/* dados do superior 1 */),
                new SuperiorModel(/* dados do superior 2 */)
        );

        // Configurar o comportamento do mock superiorRepository
        when(superiorRepository.findAll()).thenReturn(expectedSuperiors);

        // Chamar o método do serviço a ser testado
        List<SuperiorModel> result = superiorService.findAll();

        // Verificar se a lista retornada é igual à lista esperada
        assertEquals(expectedSuperiors, result);

        // Verificar se o método findAll foi chamado no mock superiorRepository
        verify(superiorRepository, times(1)).findAll();
    }

    @Test
    void testFindById_ExistingId() {
        // Criar um UUID para simular o ID do superior
        UUID superiorId = UUID.randomUUID();

        // Criar um modelo de superior esperado
        SuperiorModel expectedSuperior = new SuperiorModel(/* dados do superior */);

        // Configurar o comportamento do mock superiorRepository
        when(superiorRepository.findById(superiorId)).thenReturn(Optional.of(expectedSuperior));

        // Chamar o método do serviço a ser testado
        Optional<SuperiorModel> result = superiorService.findById(superiorId);

        // Verificar se o Optional retornado contém o modelo de superior esperado
        assertTrue(result.isPresent());
        assertEquals(expectedSuperior, result.get());

        // Verificar se o método findById foi chamado no mock superiorRepository
        verify(superiorRepository, times(1)).findById(superiorId);
    }

    @Test
    void testFindById_NonExistingId() {
        // Criar um UUID para simular um ID de superior inexistente
        UUID superiorId = UUID.randomUUID();

        // Configurar o comportamento do mock superiorRepository
        when(superiorRepository.findById(superiorId)).thenReturn(Optional.empty());

        // Chamar o método do serviço a ser testado
        Optional<SuperiorModel> result = superiorService.findById(superiorId);

        // Verificar se o Optional retornado está vazio
        assertFalse(result.isPresent());

        // Verificar se o método findById foi chamado no mock superiorRepository
        verify(superiorRepository, times(1)).findById(superiorId);
    }

    @Test
    void testExistsByCracha_ExistingCracha() {
        // Criar um número de crachá existente
        String cracha = "123456";

        // Configurar o comportamento do mock superiorRepository
        when(superiorRepository.existsByCracha(cracha)).thenReturn(true);

        // Chamar o método do serviço a ser testado
        boolean result = superiorService.existsByCracha(cracha);

        // Verificar se o resultado é true
        assertTrue(result);

        // Verificar se o método existsByCracha foi chamado no mock superiorRepository
        verify(superiorRepository, times(1)).existsByCracha(cracha);
    }

    @Test
    void testExistsByCracha_NonExistingCracha() {
        // Criar um número de crachá inexistente
        String cracha = "654321";

        // Configurar o comportamento do mock superiorRepository
        when(superiorRepository.existsByCracha(cracha)).thenReturn(false);

        // Chamar o método do serviço a ser testado
        boolean result = superiorService.existsByCracha(cracha);

        // Verificar se o resultado é false
        assertFalse(result);

        // Verificar se o método existsByCracha foi chamado no mock superiorRepository
        verify(superiorRepository, times(1)).existsByCracha(cracha);
    }
}
