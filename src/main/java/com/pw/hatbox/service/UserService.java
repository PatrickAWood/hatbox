package com.pw.hatbox.service;

import com.pw.hatbox.domain.entity.User;
import reactor.core.publisher.Flux;

public interface UserService {

    Flux<User> get();
    void create(User user);
}
