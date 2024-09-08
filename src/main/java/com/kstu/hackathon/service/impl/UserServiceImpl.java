package com.kstu.hackathon.service.impl;

import com.kstu.hackathon.constants.Roles;
import com.kstu.hackathon.dto.SignUpRequest;
import com.kstu.hackathon.model.user.Role;
import com.kstu.hackathon.model.user.User;
import com.kstu.hackathon.repo.RoleRepo;
import com.kstu.hackathon.repo.UserRepo;
import com.kstu.hackathon.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {
    private final UserRepo userRepo;
    private final PasswordEncoder encoder;
    private final RoleRepo roleRepo;

    @Autowired
    public UserServiceImpl(UserRepo userRepo, PasswordEncoder encoder, RoleRepo roleRepo) {
        this.userRepo = userRepo;
        this.encoder = encoder;
        this.roleRepo = roleRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Поиск пользователя по номеру телефона
        Optional<User> user = userRepo.findByPhoneNumber(username);

        // Если пользователь не найден, выбрасываем исключение
        if (user.isEmpty()) {
            throw new UsernameNotFoundException("User not found with phone number: " + username);
        }

        // Возвращаем пользователя как объект UserDetails
        return user.get();
    }

    @Override
    public User save(User user) {
        return userRepo.save(user);
    }

    @Override
    public void registerNewUser(SignUpRequest request) {
        Role role = roleRepo.findRoleByName(Roles.READER);

        User user = User.builder()
                .email(request.getEmail())
                .password(encoder.encode(request.getPassword()))  // Хешируем пароль перед сохранением
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .phoneNumber(request.getPhoneNumber())
                .role(role)
                .build();
        userRepo.save(user);
    }

    @Override
    public User getByPhoneNumber(String username) {
        return userRepo.findByPhoneNumber(username)
                .orElseThrow(() -> new UsernameNotFoundException("User with phone number " + username + " not found"));
    }

    @Override
    public User getCurrentUser() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new IllegalStateException("No authenticated user found");
        }

        var phoneNumber = authentication.getName();
        return getByPhoneNumber(phoneNumber);
    }

    @Override
    public void deleteById(Long userId) {
        userRepo.deleteById(userId);
    }
}
