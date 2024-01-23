package com.oxuegen.user_query_api.dto;

import com.oxuegen.user_core.models.User;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserLookupResponse {
    private User user;
}
