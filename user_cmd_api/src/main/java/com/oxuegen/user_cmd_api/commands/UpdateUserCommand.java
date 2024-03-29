package com.oxuegen.user_cmd_api.commands;

import com.oxuegen.user_core.models.User;
import lombok.Builder;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Data
@Builder
public class UpdateUserCommand {
    @TargetAggregateIdentifier
    private String id;
    @Valid
    @NotNull(message = "no user details were supplied")
    private User user;
}
