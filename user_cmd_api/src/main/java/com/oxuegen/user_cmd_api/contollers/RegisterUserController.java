package com.oxuegen.user_cmd_api.contollers;

import com.oxuegen.user_cmd_api.commands.RegisterUserCommand;
import com.oxuegen.user_cmd_api.dto.RegisterUserResponse;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping(path = "/api/v1/register")
@Slf4j
public class RegisterUserController {
    private final CommandGateway commandGateway;

    @Autowired
    public RegisterUserController(CommandGateway commandGateway){
        this.commandGateway = commandGateway;
    }

    @PostMapping
    public ResponseEntity<RegisterUserResponse> registerUser(@Valid @RequestBody RegisterUserCommand command){
        command.setId(UUID.randomUUID().toString());

        try{
            commandGateway.sendAndWait(command);

            return new ResponseEntity<>(new RegisterUserResponse("User successfully registered"), HttpStatus.CREATED);
        }
        catch (Exception ex){
            var safeErrorMessage = "Error while processing register user request for id " + command.getId();
            log.error(safeErrorMessage);

            return new ResponseEntity<>(new RegisterUserResponse(safeErrorMessage), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
