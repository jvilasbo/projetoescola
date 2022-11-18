package com.nttdata.projeto.escola.controller;

import com.nttdata.projeto.escola.model.UserEntity;
import com.nttdata.projeto.escola.repository.IUserRepository;
import com.nttdata.projeto.escola.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.ui.Model;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
@SpringBootTest
class LoginControllerTest {
    @MockBean
    Model model;
    @MockBean
    UserService userService;
    @InjectMocks
    LoginController loginController;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void showLoginPage() {
        //Arrange
        Model modelDouble = mock(Model.class);
        //Act
        String result = loginController.showLoginPage(modelDouble);
        //Assert
        assertEquals("login/index", result);
    }

    @Test
    void showHomePage() {
        //Arrange
        Model modelDouble = mock(Model.class);
        //Act
        String result = loginController.showHomePage(modelDouble);
        //Assert
        assertEquals("login/home", result);
    }

    @Test
    void shouldShowHomePageIfUserExists() {
        //Arrange
        Model modelDouble = mock(Model.class);
        UserEntity userDouble = mock(UserEntity.class);
        UserEntity userVefifiedDouble = mock(UserEntity.class);

        String name = "Nome";
        when(userDouble.getUsername()).thenReturn(name);
        when(userDouble.getPassword()).thenReturn("password");
        when(userService.getUser(name)).thenReturn(userVefifiedDouble);

        when(userVefifiedDouble.getUsername()).thenReturn(name);
        when(userVefifiedDouble.getPassword()).thenReturn("password");

        //Act
        String result = loginController.loginPage(userDouble, modelDouble);
        //Assert
        assertEquals("login/home", result);
    }

    @Test
    void shouldShowErrorMessageOnLoginPageIfUserDoesNotExist() {
        //Arrange
        Model modelDouble = mock(Model.class);
        UserEntity userDouble = mock(UserEntity.class);

        String name = "Nome";
        when(userService.getUser(name)).thenReturn(null);
        //Act
        String result = loginController.loginPage(userDouble, modelDouble);
        //Assert
        assertEquals("login/index", result);
    }
}