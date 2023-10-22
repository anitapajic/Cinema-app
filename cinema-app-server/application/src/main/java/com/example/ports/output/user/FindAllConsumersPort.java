package com.example.ports.output.user;

import com.example.model.User;

import java.util.List;

public interface FindAllConsumersPort {
    List<User> findAllUsers();
}
