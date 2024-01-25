package com.oxuegen.user_cmd_api.contollers;

import com.oxuegen.user_cmd_api.commands.RemoveUserCommand;
import com.oxuegen.user_cmd_api.dto.BaseResponse;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping(path = "api/v1/deleteUser")
public class DeleteUserController {
    private final CommandGateway commandGateway;

    @Autowired
    public DeleteUserController(CommandGateway commandGateway){
        this.commandGateway = commandGateway;
    }

    @DeleteMapping(path = "/{id}")
    //@PreAuthorize("hasAuthority('WRITE_PRIVILEGE')")
    public ResponseEntity<BaseResponse> deleteUser(@PathVariable(value = "id") String id){
        try{
            commandGateway.sendAndWait(new RemoveUserCommand(id));

            return new ResponseEntity<>(new BaseResponse("user successfully deleted"), HttpStatus.OK);
        }
        catch (Exception ex){
            var removeErrorMessage = "Error while processing delete user";
            log.error(ex.toString());

            return new ResponseEntity<>(new BaseResponse(removeErrorMessage), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
