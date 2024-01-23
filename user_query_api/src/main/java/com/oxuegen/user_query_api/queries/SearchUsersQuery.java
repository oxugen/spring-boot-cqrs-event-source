package com.oxuegen.user_query_api.queries;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SearchUsersQuery {
    private String filter;
}
