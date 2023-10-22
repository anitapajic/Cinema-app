package com.example.ports.output.user;

import com.example.model.User;

public interface BlockConsumerPort {
    User block(Long id);
}
