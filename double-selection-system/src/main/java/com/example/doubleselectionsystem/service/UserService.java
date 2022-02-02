package com.example.doubleselectionsystem.service;

import com.example.doubleselectionsystem.component.RequestComponent;
import com.example.doubleselectionsystem.entity.User;
import com.example.doubleselectionsystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RequestComponent requestComponent;
    @Autowired
    private PasswordEncoder encoder;
    public User getUser(int num){
        return userRepository.find(num);
    }
    public void modifyPassword(String password){
        User user=userRepository.findById(requestComponent.getUid()).orElse(null);
        user.setPassword(encoder.encode(password));
        userRepository.save(user);
    }
}
