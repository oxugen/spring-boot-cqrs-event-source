package com.oxuegen.user_cmd_api.security;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class PasswordEncoderImpl implements PasswordEncoder{
    @Override
    public String hashPassowrd(String password) {
        var encoder = new BCryptPasswordEncoder(12);
        var hashPassword = encoder.encode(password);

        return hashPassword;
    }
}
