package com.pratti.pesquisa.service;
import com.pratti.pesquisa.model.SectorModel;
import com.pratti.pesquisa.repository.SectorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class SectorServiceTest {
    
    @Mock
    private SectorRepository sectorRepository;
    
    private SectorService sectorService;
    
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        sectorService = new SectorService(sectorRepository);
    }
    
    @Test
    public void testFindAll() {
        // Criar uma lista de objetos SectorModel esperados
        List<SectorModel> expectedSectors = new ArrayList<>();
        expectedSectors.add(new SectorModel(/* dados do setor 1 */));
        expectedSectors.add(new SectorModel(/* dados do setor 2 */));
        
        // Configurar o comportamento do mock sectorRepository
        when(sectorRepository.findAll()).thenReturn(expectedSectors);
        
        // Chamar o método do serviço a ser testado
        List<SectorModel> actualSectors = sectorService.findAll();
        
        // Verificar se a lista retornada corresponde à lista esperada
        assertEquals(expectedSectors.size(), actualSectors.size());
        assertEquals(expectedSectors, actualSectors);
        
        // Verificar se o método findAll foi chamado no mock sectorRepository
        verify(sectorRepository, times(1)).findAll();
    }
    
    @Test
    public void testFindById_ExistingId() {
        // Criar um objeto SectorModel esperado
        UUID id = UUID.randomUUID(); // ID existente
        SectorModel expectedSector = new SectorModel(/* dados do setor */);
        
        // Configurar o comportamento do mock sectorRepository
        when(sectorRepository.findById(id)).thenReturn(Optional.of(expectedSector));
        
        // Chamar o método do serviço a ser testado
        Optional<SectorModel> actualSectorOptional = sectorService.findById(id);
        
        // Verificar se o objeto retornado corresponde ao objeto esperado
        assertTrue(actualSectorOptional.isPresent());
        assertEquals(expectedSector, actualSectorOptional.get());
        
        // Verificar se o método findById foi chamado no mock sectorRepository
        verify(sectorRepository, times(1)).findById(id);
    }
    
    @Test
    public void testFindById_NonExistingId() {
        // Criar um ID que não existe no repositório
        UUID id = UUID.randomUUID(); // ID não existente
        
        // Configurar o comportamento do mock sectorRepository
        when(sectorRepository.findById(id)).thenReturn(Optional.empty());
        
        // Chamar o método do serviço a ser testado
        Optional<SectorModel> actualSectorOptional = sectorService.findById(id);
        
        // Verificar se o objeto retornado está vazio
        assertFalse(actualSectorOptional.isPresent());
        
        // Verificar se o método findById foi chamado no mock sectorRepository
        verify(sectorRepository, times(1)).findById(id);
    }
    
    // ... Outros testes para os demais métodos do serviço ...
    
}
