package com.kstu.hackathon.service;

import com.kstu.hackathon.repo.UserRepo;

import java.util.regex.Pattern;

public class UserService {
    private final UserRepo userRepo;

    private static final String EMAIL_REGEX = "^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,}$";

    private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);

    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    /**
     * Validates if the provided email address is valid.
     *
     * @param email the email address to validate
     * @return true if the email is valid, false otherwise
     */
    public static boolean isValidEmail(String email) {
        if (email == null || email.isEmpty()) {
            return false;
        }
        // Check if the email matches the regex pattern
        return EMAIL_PATTERN.matcher(email).matches();
    }
}
