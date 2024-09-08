package com.kstu.hackathon.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Запрос на регистрацию")
public class SignUpRequest {
    @Schema(description = "Имя пользователя", example = "John")
    @Size(min = 1, max = 50, message = "Имя должно содержать от 1 до 50 символов")
    @NotBlank(message = "Имя не может быть пустым")
    private String firstName;

    @Schema(description = "Фамилия пользователя", example = "Doe")
    @Size(min = 1, max = 50, message = "Фамилия должна содержать от 1 до 50 символов")
    @NotBlank(message = "Фамилия не может быть пустой")
    private String lastName;

    @Schema(description = "Номер телефона", example = "+1234567890")
    @Size(min = 10, max = 15, message = "Номер телефона должен содержать от 10 до 15 символов")
    @NotBlank(message = "Номер телефона не может быть пустым")
    private String phoneNumber;

    @Schema(description = "Адрес электронной почты", example = "johndoe@gmail.com")
    @Size(min = 5, max = 255, message = "Адрес электронной почты должен содержать от 5 до 255 символов")
    @NotBlank(message = "Адрес электронной почты не может быть пустым")
    @Email(message = "Email адрес должен быть в формате user@example.com")
    private String email;

    @Schema(description = "Пароль", example = "my_1secret1_password")
    @Size(min = 6, max = 255, message = "Пароль должен содержать от 6 до 255 символов")
    @NotBlank(message = "Пароль не может быть пустым")
    private String password;
}
