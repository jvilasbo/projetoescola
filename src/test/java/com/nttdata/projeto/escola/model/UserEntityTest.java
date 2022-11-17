package com.nttdata.projeto.escola.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserEntityTest {

    @Test
    void shouldSetCorrectAtributesToUserEntity(){
        UserEntity user = new UserEntity();

        user.setId(1);
        user.setUsername("jvilasbo");
        user.setPassword("qwerty");

        assertEquals(user.getId(),1);
        assertEquals("jvilasbo", user.getUsername());
        assertEquals("qwerty", user.getPassword());
    }

    @Test
    void shouldCreateUserWithCorrectAttributes() {
        UserEntity userTeste = new UserEntity("admin", "1234");

        assertEquals("admin", userTeste.getUsername());
        assertEquals("1234", userTeste.getPassword());
    }
}