package com.oxuegen.user_query_api.controllers;

import com.oxuegen.user_query_api.dto.UserLookupResponse;
import com.oxuegen.user_query_api.queries.FindAllUsersQuery;
import com.oxuegen.user_query_api.queries.FindUserByIdQuery;
import com.oxuegen.user_query_api.queries.SearchUsersQuery;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.messaging.responsetypes.ResponseType;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(path = "api/v1/userLookup")
public class UserLookupController {
    private final QueryGateway queryGateway;

    @Autowired
    public UserLookupController(QueryGateway queryGateway){
        this.queryGateway = queryGateway;
    }

    @GetMapping("/")
    public ResponseEntity<UserLookupResponse> getAllUsers(){
        try{
            var query = new FindAllUsersQuery();
            var response = queryGateway.query(query, ResponseTypes.instanceOf(UserLookupResponse.class)).join();

            if(response == null || response.getUsers() == null){
                return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        catch (Exception ex){
            var getUserErrorMessage = "Failed to get all users request";
            log.error(ex.toString());

            return new ResponseEntity<>(new UserLookupResponse(getUserErrorMessage),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/byId/{id}")
    public ResponseEntity<UserLookupResponse> getUserById(@PathVariable(value = "id") String id){
        try{
            var query = new FindUserByIdQuery(id);
            var response = queryGateway.query(query, ResponseTypes.instanceOf(UserLookupResponse.class)).join();

            if(response == null || response.getUsers() == null){
                return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        catch (Exception ex){
            var getUserErrorMessage = "Failed to get user by ID";
            log.error(ex.toString());

            return new ResponseEntity<>(new UserLookupResponse(getUserErrorMessage),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/byFilter/{filter}")
    public ResponseEntity<UserLookupResponse> searchUserByFilter(@PathVariable(value = "filter") String filter){
        try{
            var query = new SearchUsersQuery(filter);
            var response = queryGateway.query(query,
                    ResponseTypes.instanceOf(UserLookupResponse.class))
                    .join();

            if(response == null || response.getUsers() == null){
                return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        catch (Exception ex){
            var getUserErrorMessage = "Failed to complete user search request";
            log.error(ex.toString());

            return new ResponseEntity<>(new UserLookupResponse(getUserErrorMessage),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
