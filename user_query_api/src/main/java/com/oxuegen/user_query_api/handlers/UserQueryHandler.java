package com.oxuegen.user_query_api.handlers;

import com.oxuegen.user_query_api.dto.UserLookupResponse;
import com.oxuegen.user_query_api.queries.FindAllUsersQuery;
import com.oxuegen.user_query_api.queries.FindUserByIdQuery;
import com.oxuegen.user_query_api.queries.SearchUsersQuery;

public interface UserQueryHandler {
    UserLookupResponse getUserById(FindUserByIdQuery query);
    UserLookupResponse searchUser(SearchUsersQuery query);
    UserLookupResponse getAllUsers(FindAllUsersQuery query);
}
