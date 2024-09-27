package com.example.demo.interfaces;

import com.example.demo.entity.User;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.data.rest.core.config.Projection;

@Projection(name = "userSummary", types = { User.class })
//@RestResource(path = "users", rel = "users")
public interface UserSummary {
    String getName();
}