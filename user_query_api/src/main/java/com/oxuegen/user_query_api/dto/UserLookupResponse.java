package com.oxuegen.user_query_api.dto;

import com.oxuegen.user_core.models.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserLookupResponse extends BaseResponse{
    private List<User> users;

    public UserLookupResponse(List<User> users){
        super(null);
        this.users = users;
    }

    public UserLookupResponse(String message){
        super(message);
    }

    public UserLookupResponse(String message, User user){
        super(message);
        this.users = new ArrayList<>();
        this.users.add(user);
    }

    public UserLookupResponse(User user){
        super(null);
        this.users = new ArrayList<>();
        this.users.add(user);
    }

    public List<User> getUsers(){
        return this.users;
    }

    public void setUsers(List<User> users){
        this.users = users;
    }
}
