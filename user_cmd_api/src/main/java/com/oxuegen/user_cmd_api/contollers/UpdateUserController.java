package com.oxuegen.user_cmd_api.contollers;

import com.oxuegen.user_cmd_api.commands.UpdateUserCommand;
import com.oxuegen.user_cmd_api.dto.BaseResponse;
import com.oxuegen.user_cmd_api.dto.RegisterUserResponse;
import com.oxuegen.user_cmd_api.dto.UpdateUserResponse;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@Slf4j
@RequestMapping(path = "api/v1/updateUser")
public class UpdateUserController {
    private final CommandGateway commandGateway;

    @Autowired
    public UpdateUserController(CommandGateway commandGateway){
        this.commandGateway = commandGateway;
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<BaseResponse> updateUser(@PathVariable(value = "id") String id,
                                                   @Valid @RequestBody UpdateUserCommand command){
        try{
            command.setId(id);
            commandGateway.sendAndWait(command);

            return new ResponseEntity<>(new BaseResponse("User successfully updated!"), HttpStatus.OK);
        }
        catch (Exception ex){
            var safeErrorMessage = "Error while processing update user request for id " + command.getId();
            log.error(safeErrorMessage);

            return new ResponseEntity<>(new BaseResponse(safeErrorMessage), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
