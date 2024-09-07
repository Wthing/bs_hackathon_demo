package com.kstu.hackathon.constants;

import java.util.regex.Pattern;

public class regex {
    public static final String EMAIL_REGEX = "^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,}$";

    public static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);
}
