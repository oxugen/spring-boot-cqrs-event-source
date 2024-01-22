package com.oxuegen.user_query_api.handlers;

import com.oxuegen.user_core.events.UserRegisteredEvent;
import com.oxuegen.user_core.events.UserRemovedEvent;
import com.oxuegen.user_core.events.UserUpdatedEvent;

public interface UserEventHandler {
    void on(UserRegisteredEvent event);
    void on(UserUpdatedEvent event);
    void on(UserRemovedEvent event);
}
