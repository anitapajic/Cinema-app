package com.example.ports.output.user;

import com.example.model.User;

public interface VerifyAccountPort {
    User verify(Long id);
}
