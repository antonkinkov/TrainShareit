package ru.practicum.shareit.user.model;

import jakarta.validation.constraints.Email;
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
public class User {

    @NotNull
    private Long id;

    @NotNull
    @NotBlank
    @Size(min = 2, max = 20)
    private String name;

    @Email
    private String email;
}
