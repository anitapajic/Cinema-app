package com.example.ports.output.user;

import com.example.model.User;

public interface FindByUsernamePort {
    User findByUsername(String username);
}
