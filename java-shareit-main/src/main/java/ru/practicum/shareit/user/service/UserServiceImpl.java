package ru.practicum.shareit.user.service;

import jakarta.validation.ValidationException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.practicum.shareit.exception.NotFoundException;
import ru.practicum.shareit.user.dto.UserDto;
import ru.practicum.shareit.user.dto.UserMapper;
import ru.practicum.shareit.user.model.User;
import ru.practicum.shareit.user.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static ru.practicum.shareit.user.dto.UserMapper.toUser;
import static ru.practicum.shareit.user.dto.UserMapper.toUserDto;

@Slf4j
@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public UserDto create(UserDto userDto) {
        validateByUser(userDto);
        User user = toUser(userDto);
        return toUserDto(userRepository.save(user));
    }

    @Override
    public UserDto update(UserDto userDto, Long userId) {
        User user = findUserById(userId);
        validateByUser(userDto);

        user.setName(Objects.requireNonNullElse(userDto.getName(), user.getName()));
        user.setEmail(Objects.requireNonNullElse(userDto.getEmail(), user.getEmail()));

        userRepository.save(user);
        return UserMapper.toUserDto(user);
    }

    @Override
    public UserDto getById(Long userId) {
        User user = findUserById(userId);
        return toUserDto(user);
    }

    @Override
    public List<UserDto> getAll() {
        List<UserDto> users = new ArrayList<>();

        for (User user : userRepository.findAll()) {
            users.add(toUserDto(user));
        }
        return users;
    }

    @Override
    public void delete(Long userId) {
        User user = findUserById(userId);
        userRepository.deleteById(user.getId());
    }

    public void validateByUser(UserDto userDto) {
        if (userDto.getName().isBlank()) {
            log.info("There is no name field");
            throw new ValidationException("There is no name field");
        }
        if (userDto.getEmail().isBlank() || userDto.getEmail() == null) {
            log.info("There is no email field");
            throw new ValidationException("There is no email field");
        }
        if (!userDto.getEmail().contains("@")) {
            log.info("Invalid format email");
            throw new ValidationException("Invalid format email");
        }

    }

    public User findUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("User not found with id:" + userId));
    }
}
