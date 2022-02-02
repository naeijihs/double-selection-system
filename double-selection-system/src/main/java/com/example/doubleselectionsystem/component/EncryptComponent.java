package com.example.doubleselectionsystem.component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.encrypt.TextEncryptor;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

@Component
public class EncryptComponent {
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private TextEncryptor encryptor;
    public String encryptToken(MyToken token){      //序列化 加密
        try {
            String json=objectMapper.writeValueAsString(token);
            return encryptor.encrypt(json);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,"服务器端错误");
        }
    }
    public MyToken decryptToken(String auth){       //解密 反序列化
        try {
            String json=encryptor.decrypt(auth);
            return objectMapper.readValue(json,MyToken.class);
        } catch (JsonProcessingException e) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN,"无权限");
        }
    }
}
