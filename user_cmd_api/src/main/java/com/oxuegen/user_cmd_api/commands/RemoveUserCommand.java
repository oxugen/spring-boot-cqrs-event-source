package com.oxuegen.user_cmd_api.commands;

import com.oxuegen.user_core.models.User;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
public class RemoveUserCommand {
    @TargetAggregateIdentifier
    private String id;
}
