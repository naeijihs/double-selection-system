package com.example.doubleselectionsystem;

import com.example.doubleselectionsystem.repository.impl.BaseRepositoryImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.encrypt.TextEncryptor;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
@EnableJpaRepositories(repositoryBaseClass = BaseRepositoryImpl.class)
public class DoubleSelectionSystemApplication {
    @Value("R28K42ZEJ8LWRHU5")
    private String secretKey;
    @Value("636eac2534bcfcb0")
    private String salt;
    public static void main(String[] args) {
        SpringApplication.run(DoubleSelectionSystemApplication.class, args);
    }
    @Bean
    public PasswordEncoder getPasswordEncoder(){return new BCryptPasswordEncoder();}
    @Bean
    public TextEncryptor getTextEncryptor(){        //单列 避免反复创建
        return Encryptors.text(secretKey,salt);
    }
}
