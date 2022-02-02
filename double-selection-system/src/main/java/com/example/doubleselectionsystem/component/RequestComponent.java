package com.example.doubleselectionsystem.component;

import com.example.doubleselectionsystem.entity.User;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

@Component
public class RequestComponent {
    public int getUid(){
        return (int)RequestContextHolder.currentRequestAttributes().getAttribute(MyToken.UID, RequestAttributes.SCOPE_REQUEST);
    }
    public User.Role getRole(){
        return (User.Role)RequestContextHolder.currentRequestAttributes().getAttribute(MyToken.ROLE, RequestAttributes.SCOPE_REQUEST);
    }
}
