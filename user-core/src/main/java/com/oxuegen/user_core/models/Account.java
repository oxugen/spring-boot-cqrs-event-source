package com.oxuegen.user_core.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Account {
    @Size(min = 2, message = "userName must be at least of 2 characters")
    private String username;
    @Size(min = 7, message = "password must be at least of 7 characters")
    private String password;
    @NotNull(message = "specify at least 1 role")
    private List<Role> roles;
}
