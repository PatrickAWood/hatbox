package com.pw.hatbox.repository;

import com.pw.hatbox.domain.entity.User;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface UserRepository extends ReactiveCrudRepository<User, Long> {

}
