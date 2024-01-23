package com.oxuegen.user_cmd_api.commands;

import com.oxuegen.user_core.models.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
@AllArgsConstructor
public class RemoveUserCommand {
    @TargetAggregateIdentifier
    private String id;
}
