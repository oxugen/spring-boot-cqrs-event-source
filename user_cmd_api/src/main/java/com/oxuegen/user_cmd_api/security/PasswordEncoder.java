package com.oxuegen.user_cmd_api.security;

public interface PasswordEncoder{
    String hashPassowrd(String password);
}
