package com.oxuegen.user_query_api.repositories;

import com.oxuegen.user_core.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
}
