package com.oxuegen.user_cmd_api.aggregates;

import com.oxuegen.user_cmd_api.commands.RegisterUserCommand;
import com.oxuegen.user_cmd_api.commands.RemoveUserCommand;
import com.oxuegen.user_cmd_api.commands.UpdateUserCommand;
import com.oxuegen.user_cmd_api.security.PasswordEncoder;
import com.oxuegen.user_cmd_api.security.PasswordEncoderImpl;
import com.oxuegen.user_core.events.UserRegisteredEvent;
import com.oxuegen.user_core.events.UserRemovedEvent;
import com.oxuegen.user_core.events.UserUpdatedEvent;
import com.oxuegen.user_core.models.User;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

@Aggregate
public class UserAggregate {
    @AggregateIdentifier
    private String id;
    private User user;

    private final PasswordEncoder passwordEncoder;

    public UserAggregate(){
        passwordEncoder = new PasswordEncoderImpl();
    }

    @CommandHandler
    public UserAggregate(RegisterUserCommand command){
        var newUser = command.getUser();
        newUser.setId(command.getId());
        var password = newUser.getAccount().getPassword();
        passwordEncoder = new PasswordEncoderImpl();
        var hashedPassword = passwordEncoder.hashPassowrd(password);
        newUser.getAccount().setPassword(hashedPassword);

        var event = UserRegisteredEvent
                .builder()
                .id(command.getId())
                .user(newUser)
                .build();

        AggregateLifecycle.apply(event);
    }

    @CommandHandler
    public void handle(UpdateUserCommand command){
        var updatedUser = command.getUser();
        updatedUser.setId(command.getId());
        var password = updatedUser.getAccount().getPassword();
        var hashedPassword = passwordEncoder.hashPassowrd(password);
        updatedUser.getAccount().setPassword(hashedPassword);

        var event = UserUpdatedEvent
                .builder()
                .id(UUID.randomUUID().toString())
                .user(updatedUser)
                .build();

        AggregateLifecycle.apply(event);
    }

    @CommandHandler
    public void handle(RemoveUserCommand command){
        var event = new UserRemovedEvent();
        event.setId(command.getId());

        AggregateLifecycle.apply(event);
    }

    @EventSourcingHandler
    public void on(UserRegisteredEvent event){
        this.id = event.getId();
        this.user = event.getUser();
    }

    @EventSourcingHandler
    public void on(UserUpdatedEvent event){
        this.user = event.getUser();
    }

    @EventSourcingHandler
    public void on(UserRemovedEvent event){
        AggregateLifecycle.markDeleted();
    }
}
