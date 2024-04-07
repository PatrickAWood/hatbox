package com.pw.hatbox.service.impl;

import com.pw.hatbox.domain.entity.User;
import com.pw.hatbox.repository.UserRepository;
import com.pw.hatbox.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final KafkaTemplate<String, String> kafkaTemplate;

    @Override
    public Flux<User> get() {
        return userRepository.findAll().delayElements(Duration.ofMillis(1000));
    }

    @Override
    public void create(User user) {
        userRepository.save(user)
//                .doOnNext(this::sendMessage)
                .doOnError(err -> log.error("Error saving user {}", user, err))
                .subscribe(savedUser -> log.info("Saved user {}", savedUser));
    }

    private void sendMessage(User user) {
        kafkaTemplate.send(
                "pw-topic-1",
                String.format("Created user %s %s with ID %s", user.getFirstName(), user.getSurname(), user.getId())
        ).whenComplete((result, err) -> {
            if (err == null) {
                log.info("Sent message {} with offset {}", result.getProducerRecord(), result.getRecordMetadata().offset());
            } else {
                log.error("Error sending message", err);
            }
        });
    }
}
