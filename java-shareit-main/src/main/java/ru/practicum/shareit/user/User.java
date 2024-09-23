package ru.practicum.shareit.user;

import jakarta.validation.constraints.NotNull;
import lombok.*;

/**
 * TODO Sprint add-controllers.
 */

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @NotNull
    private Long id;
}
