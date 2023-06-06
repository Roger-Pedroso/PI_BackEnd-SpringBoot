package com.pratti.pesquisa.service;

import com.pratti.pesquisa.controller.UserController;
import com.pratti.pesquisa.dtos.LoginDto;
import com.pratti.pesquisa.dtos.UserDto;
import com.pratti.pesquisa.model.UserModel;
import com.pratti.pesquisa.service.LoginMessage;
import com.pratti.pesquisa.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UserServiceTest {

    @Mock
    private UserService userService;

    private UserController userController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        userController = new UserController(userService);
    }

    @Test
    public void testSaveUser_Success() {
        // Criar um objeto UserDto para simular a requisição
        UserDto userDto = new UserDto(/* dados do usuário */);

        // Configurar o comportamento do mock userService
        when(userService.existsByCracha(userDto.getCracha())).thenReturn(false);

        UserModel savedUser = new UserModel(/* dados do usuário salvo */);
        when(userService.save(any(UserModel.class))).thenReturn(savedUser);

        // Chamar o método do controlador a ser testado
        ResponseEntity<Object> response = userController.saveUser(userDto);

        // Verificar se o código de status e o objeto retornado estão corretos
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(savedUser, response.getBody());

        // Verificar se o método existsByCracha foi chamado no mock userService
        verify(userService, times(1)).existsByCracha(userDto.getCracha());
        
        // Verificar se o método save foi chamado no mock userService
        verify(userService, times(1)).save(any(UserModel.class));
    }

    @Test
    public void testSaveUser_Conflict() {
        // Criar um objeto UserDto para simular a requisição
        UserDto userDto = new UserDto(/* dados do usuário */);

        // Configurar o comportamento do mock userService
        when(userService.existsByCracha(userDto.getCracha())).thenReturn(true);

        // Chamar o método do controlador a ser testado
        ResponseEntity<Object> response = userController.saveUser(userDto);

        // Verificar se o código de status e a mensagem de erro estão corretos
        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
        assertEquals("Conflict: Cracha is already in use", response.getBody());

        // Verificar se o método existsByCracha foi chamado no mock userService
        verify(userService, times(1)).existsByCracha(userDto.getCracha());
        
        // Verificar que o método save não foi chamado no mock userService
        verify(userService, never()).save(any(UserModel.class));
    }

    @Test
    public void testGetAllUsers() {
        // Criar uma lista de objetos UserModel esperados
        List<UserModel> expectedUsers = new ArrayList<>();
        expectedUsers.add(new UserModel(/* dados do usuário 1 */));
        expectedUsers.add(new UserModel(/* dados do usuário 2 */));

        // Configurar o comportamento do mock userService
        when(userService.findAll()).thenReturn(expectedUsers);

        // Chamar o método do controlador a ser testado
        ResponseEntity<List<UserModel>> response = userController.getAllUsers();

        // Verificar se o código de status e a lista de usuários estão corretos
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedUsers, response.getBody());

        // Verificar se o método findAll foi chamado no mock userService
        verify(userService, times(1)).findAll();
    }

    @Test
    public void testGetOneUser_ExistingId() {
        // Criar um UUID para simular o ID do usuário
        UUID userId = UUID.randomUUID();

        // Criar um objeto UserModel esperado
        UserModel expectedUser = new UserModel(/* dados do usuário */);

        // Configurar o comportamento do mock userService
        when(userService.findById(userId)).thenReturn(Optional.of(expectedUser));

        // Chamar o método do controlador a ser testado
        ResponseEntity<Object> response = userController.getOneUser(userId);

        // Verificar se o código de status e o usuário retornado estão corretos
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedUser, response.getBody());

        // Verificar se o método findById foi chamado no mock userService
        verify(userService, times(1)).findById(userId);
    }

    @Test
    public void testGetOneUser_NonExistingId() {
        // Criar um UUID para simular um ID de usuário inexistente
        UUID userId = UUID.randomUUID();

        // Configurar o comportamento do mock userService
        when(userService.findById(userId)).thenReturn(Optional.empty());

        // Chamar o método do controlador a ser testado
        ResponseEntity<Object> response = userController.getOneUser(userId);

        // Verificar se o código de status e a mensagem de erro estão corretos
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("User not found", response.getBody());

        // Verificar se o método findById foi chamado no mock userService
        verify(userService, times(1)).findById(userId);
    }

    @Test
    public void testUpdateUser_ExistingId() {
        // Criar um UUID para simular o ID do usuário
        UUID userId = UUID.randomUUID();

        // Criar um objeto UserDto para simular a requisição
        UserDto userDto = new UserDto(/* dados do usuário a serem atualizados */);

        // Criar um objeto UserModel esperado
        UserModel expectedUser = new UserModel(/* dados do usuário atualizados */);

        // Configurar o comportamento do mock userService
        when(userService.findById(userId)).thenReturn(Optional.of(expectedUser));
        when(userService.update(any(UserModel.class))).thenReturn(expectedUser);

        // Chamar o método do controlador a ser testado
        ResponseEntity<Object> response = userController.updateUser(userId, userDto);

        // Verificar se o código de status e o usuário retornado estão corretos
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedUser, response.getBody());

        // Verificar se o método findById foi chamado no mock userService
        verify(userService, times(1)).findById(userId);
        
        // Verificar se o método update foi chamado no mock userService
        verify(userService, times(1)).update(any(UserModel.class));
    }

    @Test
    public void testUpdateUser_NonExistingId() {
        // Criar um UUID para simular um ID de usuário inexistente
        UUID userId = UUID.randomUUID();

        // Criar um objeto UserDto para simular a requisição
        UserDto userDto = new UserDto(/* dados do usuário a serem atualizados */);

        // Configurar o comportamento do mock userService
        when(userService.findById(userId)).thenReturn(Optional.empty());

        // Chamar o método do controlador a ser testado
        ResponseEntity<Object> response = userController.updateUser(userId, userDto);

        // Verificar se o código de status e a mensagem de erro estão corretos
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("User not found", response.getBody());

        // Verificar se o método findById foi chamado no mock userService
        verify(userService, times(1)).findById(userId);
        
        // Verificar que o método update não foi chamado no mock userService
        verify(userService, never()).update(any(UserModel.class));
    }


    
}
