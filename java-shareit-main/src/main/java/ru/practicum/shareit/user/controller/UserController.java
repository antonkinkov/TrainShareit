package ru.practicum.shareit.user.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.shareit.user.dto.UserDto;
import ru.practicum.shareit.user.service.UserService;

import java.util.List;

/**
 * TODO Sprint add-controllers.
 */
@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/users")
public class UserController {

    private final UserService userService;

    @PostMapping
    public UserDto addUser(@RequestBody UserDto userDto) {
        log.info("Get a request to add user = {}", userDto);
        return userService.create(userDto);
    }

    @PatchMapping("/{userId}")
    public UserDto updateUser(@RequestBody UserDto userDto,
                              @PathVariable Long userId) {
        log.info("Get a request to update user with id = {}", userId);
        return userService.update(userDto, userId);
    }

    @GetMapping("/{userId}")
    public UserDto getUserById(@PathVariable Long userId) {
        log.info("Get a request to get user with id = {}", userId);
        return userService.getById(userId);
    }

    @GetMapping
    public List<UserDto> getAllUser() {
        log.info("Get a request to gets all users");
        return userService.getAll();
    }

    @DeleteMapping("/delete")
    public void deleteUser(@PathVariable Long userId) {
        log.info("Get a request to delete  user with id = {}", userId);
        userService.delete(userId);
    }
}
