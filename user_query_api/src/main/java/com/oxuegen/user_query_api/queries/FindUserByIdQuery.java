package com.oxuegen.user_query_api.queries;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FindUserByIdQuery {
    private String id;
}
