package dev.kakao5.eyestalkdb.service;

import dev.kakao5.eyestalkdb.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = false)
public class RoomServiceImpl {
    private final UserRepository userRepository;


}
