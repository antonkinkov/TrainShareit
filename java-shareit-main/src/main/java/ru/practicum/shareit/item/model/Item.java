package ru.practicum.shareit.item.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import ru.practicum.shareit.user.User;

/**
 * TODO Sprint add-controllers.
 */
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Item {

    @NotNull
    private Long id;

    @NotBlank
    @Size(min = 2, max = 20)
    private String name;

    @NotBlank
    private String description;

    private Boolean available; // Статус, доступна вещь или нет

    private User owner; // Владелец вещи

    private String request; // Ссылка на запрос пользователя
}