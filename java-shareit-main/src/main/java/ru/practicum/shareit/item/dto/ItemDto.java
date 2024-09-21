package ru.practicum.shareit.item.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

/**
 * TODO Sprint add-controllers.
 */

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ItemDto {

    @NotNull
    private Long id;

    @NotBlank
    @Size(min = 2, max = 20)
    private String name;

    @NotBlank
    @Size(min = 10, max = 2000)
    private String description;

    @NotNull
    private Boolean available; // Статус, доступна вещь или нет

    private String request; // Ссылка на запрос пользователя
}
