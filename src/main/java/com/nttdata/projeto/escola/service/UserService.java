package com.nttdata.projeto.escola.service;

import com.nttdata.projeto.escola.model.UserEntity;
import com.nttdata.projeto.escola.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private IUserRepository userRepository;

    public UserEntity getUser(String username) {
        return userRepository.getReferenceByUsername(username);
    }
}
