package com.example.order_system_backend.utils;

import org.springframework.stereotype.Component;

@Component
public class Utils {

    public boolean validateUsername (String username) {
        boolean valid = false;
        if (username != null) {
            if (username.contains("@")) {
                valid = true;
            }
        }
        return valid;
    }

    public boolean validatePassword (String password) {
        boolean valid = false;
        if (password != null) {
            if (password.length() >= 8) {
                valid = true;
            }
        }
        return valid;
    }

}
