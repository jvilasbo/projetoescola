package com.nttdata.projeto.escola.service;

import com.nttdata.projeto.escola.model.UserEntity;
import com.nttdata.projeto.escola.repository.IUserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
@SpringBootTest
class UserServiceTest {
    @MockBean
    IUserRepository userRepository;
    @InjectMocks
    UserService userService;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldGetUserByUsernameWithCorrectAttributes() {
        //Arrange
        UserEntity userDouble = mock(UserEntity.class);
        String generatedUsername = new Random().toString();

        when(userRepository.getReferenceByUsername(generatedUsername)).thenReturn(userDouble);
        //Act
        UserEntity userResult = userService.getUser(generatedUsername);
        //Assert
        assertEquals(userDouble, userResult);
    }
}