package com.example.ports.output.user;

import com.example.model.Token;
import com.example.model.User;

public interface LoginPort {
     Token login(User user);
}
