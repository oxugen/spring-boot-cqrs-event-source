package com.oxuegen.user_query_api.handlers;

import com.oxuegen.user_query_api.dto.UserLookupResponse;
import com.oxuegen.user_query_api.queries.FindAllUsersQuery;
import com.oxuegen.user_query_api.queries.FindUserByIdQuery;
import com.oxuegen.user_query_api.queries.SearchUsersQuery;
import com.oxuegen.user_query_api.repositories.UserRepository;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        return null;
    }

    @QueryHandler
    @Override
    public UserLookupResponse searchUser(SearchUsersQuery query) {
        return null;
    }

    @QueryHandler
    @Override
    public UserLookupResponse getAllUsers(FindAllUsersQuery query) {
        return null;
    }
}
