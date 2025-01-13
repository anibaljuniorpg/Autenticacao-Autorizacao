package com.Kipper.First_spring_app.domain.user;

public record UserRequestDTO(String login, String password, UserRole userRole) {
}
