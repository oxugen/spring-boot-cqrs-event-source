package com.oxuegen.user_query_api.handlers;

import com.oxuegen.user_query_api.dto.UserLookupResponse;
import com.oxuegen.user_query_api.queries.FindAllUsersQuery;
import com.oxuegen.user_query_api.queries.FindUserByIdQuery;
import com.oxuegen.user_query_api.queries.SearchUsersQuery;
import com.oxuegen.user_query_api.repositories.UserRepository;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserQueryHandlerImpl implements UserQueryHandler{

    private final UserRepository userRepository;

    @Autowired
    public UserQueryHandlerImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @QueryHandler
    @Override
    public UserLookupResponse getUserById(FindUserByIdQuery query) {
        var user = userRepository.findById(query.getId());
        return user.isPresent() ? new UserLookupResponse(user.get()) : null;
    }

    @QueryHandler
    @Override
    public UserLookupResponse searchUser(SearchUsersQuery query) {
        var users = new ArrayList(userRepository.findByFilterRegex(query.getFilter()));

        return new UserLookupResponse(users);
    }

    @QueryHandler
    @Override
    public UserLookupResponse getAllUsers(FindAllUsersQuery query) {
        var users = new ArrayList(userRepository.findAll());

        return new UserLookupResponse(users);
    }
}
