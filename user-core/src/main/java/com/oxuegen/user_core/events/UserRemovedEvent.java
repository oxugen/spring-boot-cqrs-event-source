package com.oxuegen.user_core.events;

import com.oxuegen.user_core.models.User;
import lombok.Data;

@Data
public class UserRemovedEvent {
    private String id;
}
