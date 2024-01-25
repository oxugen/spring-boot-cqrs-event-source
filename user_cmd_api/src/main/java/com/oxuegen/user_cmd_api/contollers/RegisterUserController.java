package com.oxuegen.user_cmd_api.contollers;

import com.oxuegen.user_cmd_api.commands.RegisterUserCommand;
import com.oxuegen.user_cmd_api.dto.RegisterUserResponse;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping(path = "/api/v1/registerUser")
@Slf4j
public class RegisterUserController {
    private final CommandGateway commandGateway;

    @Autowired
    public RegisterUserController(CommandGateway commandGateway){
        this.commandGateway = commandGateway;
    }

    @PostMapping
    //@PreAuthorize("hasAuthority('WRITE_PRIVILEGE')")
    public ResponseEntity<RegisterUserResponse> registerUser(@Valid @RequestBody RegisterUserCommand command){
        var id = UUID.randomUUID().toString();
        command.setId(id);

        try{
            commandGateway.sendAndWait(command);

            return new ResponseEntity<>(new RegisterUserResponse(id,"User successfully registered"), HttpStatus.CREATED);
        }
        catch (Exception ex){
            var safeErrorMessage = "Error while processing register user request for id " + command.getId();
            log.error(ex.toString());

            return new ResponseEntity<>(new RegisterUserResponse(id, safeErrorMessage), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
