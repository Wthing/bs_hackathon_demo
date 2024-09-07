package com.kstu.hackathon.service.interfaces;

import static com.kstu.hackathon.constants.regex.EMAIL_PATTERN;

public interface UserService {

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
